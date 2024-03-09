package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Libro;

public interface ILibroService {
	
	public List<Libro> getAllLibro();
	public Libro createLibro(Libro lib);
	public void deleteLibro(Long id);
	public Libro getLibroById(Long id);

}
