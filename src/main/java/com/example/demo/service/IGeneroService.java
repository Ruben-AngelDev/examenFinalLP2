package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Genero;

public interface IGeneroService {
	
public List<Genero> getAllGenero();
	
	public Genero getGeneroById(Long id);

}
