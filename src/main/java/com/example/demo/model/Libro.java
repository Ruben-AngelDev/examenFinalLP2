package com.example.demo.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="tbl_libro")
public class Libro {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_libro")
	private Long idLibro;
	
	@Column(name = "name_libro", nullable = false, length = 60) 
	private String nomLibro;
	
	@Column(name = "name_autor", nullable = false, length = 60) 
	private String autor;
	
	@Temporal(TemporalType.DATE)
	private Date fechaPubli;
	
	@ManyToOne
	@JoinColumn(name = "id_genero", nullable = false)
	private Genero idGenero;

	public Long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public String getNomLibro() {
		return nomLibro;
	}

	public void setNomLibro(String nomLibro) {
		this.nomLibro = nomLibro;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Date getFechaPubli() {
		return fechaPubli;
	}

	public void setFechaPubli(Date fechaPubli) {
		this.fechaPubli = fechaPubli;
	}

	public Genero getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(Genero idGenero) {
		this.idGenero = idGenero;
	}
	
}
