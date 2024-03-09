package com.example.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Genero;
import com.example.demo.model.Libro;
import com.example.demo.model.LibroCount;
import com.example.demo.service.impl.GeneroService;
import com.example.demo.service.impl.LibroService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("/libros")
public class LibroController {
	
	 @Autowired
	  private LibroCount libroCount;

	@Autowired
	private LibroService libService;
	
	@Autowired
	private GeneroService genService;
	
	@GetMapping("/getAllLibros")
	public String getAllLibro(Model model) {
		List<Libro> listLibro = libService.getAllLibro();
		model.addAttribute("libros", listLibro);
		return "libroList";
	}
    
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("generos", genService.getAllGenero());
		return "libroRegister";
	}
	
	@PostMapping("/register")
	public String createLibro(@RequestParam("nombre") String nombre,
	        @RequestParam("autor") String autor, @RequestParam("fecha") Date fecha,
	        @RequestParam("idGenero") Long idGenero, Model model) {

	    Genero genero = genService.getGeneroById(idGenero);
	    Libro libro = new Libro();
	    libro.setNomLibro(nombre);
	    libro.setAutor(autor);
	    libro.setFechaPubli(fecha);
	    libro.setIdGenero(genero);
	    libService.createLibro(libro);
	    List<Libro> listLibros = libService.getAllLibro();
	    model.addAttribute("libros", listLibros);
	    return "libroList";
	}
	
	@GetMapping("/edit/{id}")
	public String getLibroByID(@PathVariable Long id, Model model){
		
	    Libro lib = libService.getLibroById(id);
		model.addAttribute("generos", genService.getAllGenero());
		model.addAttribute("libro",lib);
		return "libroEdit";
		
	}
	
	@PostMapping("/edit")
	public String editLibro(@RequestParam("idLibro") Long id, @RequestParam("nomLibro") String nombre,
			@RequestParam("autor") String autor, @RequestParam("fechaPubli") Date fecha,@RequestParam("idGenero") Long idGenero,Model model){
		Genero genero = genService.getGeneroById(idGenero);
		Libro libro = libService.getLibroById(id);
		libro.setIdGenero(genero);
		libro.setNomLibro(nombre);
	    libro.setAutor(autor);
		libro.setFechaPubli(fecha);

		libService.createLibro(libro);
		List<Libro> listLibros = libService.getAllLibro();
		model.addAttribute("libros", listLibros);
		return "redirect:/libros/getAllLibros";
	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteLibro(@PathVariable Long id, Model model) {
		libService.deleteLibro(id);
		List<Libro> listLibros = libService.getAllLibro();
		model.addAttribute("libros", listLibros);
		return "redirect:/libros/getAllLibros";
	}
	
	@GetMapping("/cantidadLibros")
    public String cantidadLibrosUltimos6Meses(Model model) {
        Long cantidadLibros = libroCount.contarLibrosUltimos6Meses();
        model.addAttribute("cantidadLibros", cantidadLibros);
        return "cantidadLibros";
    }
	
	@GetMapping("/report")
	public void generarReporte(HttpServletResponse response) throws JRException, IOException {
	    Long cantidadLibros = libroCount.contarLibrosUltimos6Meses();
	    InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/reporteExamenFinal.jasper");

	    Map<String, Object> params = new HashMap<>();
	    params.put("Usuario", "Ruben Ocaña");
	    params.put("cantidadLibros", cantidadLibros);

	    List<Object> data = new ArrayList<>();
	    data.add(params);

	    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);

	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "filename=reporte_ejemplo.pdf");

	    OutputStream outputStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}

}
