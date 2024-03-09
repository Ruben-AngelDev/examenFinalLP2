package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Genero;
import com.example.demo.repository.GeneroRepository;
import com.example.demo.service.IGeneroService;

@Service
public class GeneroService implements IGeneroService {

	@Autowired
	private GeneroRepository geneRepository;
	
	@Override
	public List<Genero> getAllGenero() {
		return this.geneRepository.findAll();
	}

	@Override
	public Genero getGeneroById(Long id) {
		
		return this.geneRepository.findById(id).orElse(null);
	}

}
