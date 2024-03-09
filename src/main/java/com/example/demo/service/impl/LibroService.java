package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Libro;
import com.example.demo.repository.LibroRepository;
import com.example.demo.service.ILibroService;

@Service
public class LibroService implements ILibroService {
	
	@Autowired
	private LibroRepository libroRepository;

	@Override
	public List<Libro> getAllLibro() {
		
		return this.libroRepository.findAll();
	}

	@Override
	public Libro createLibro(Libro lib) {
		return this.libroRepository.save(lib);
	}

	@Override
	public void deleteLibro(Long id) {
		this.libroRepository.deleteById(id);
		
	}

	@Override
	public Libro getLibroById(Long id) {
		return this.libroRepository.findById(id).orElse(null);
	}

}
