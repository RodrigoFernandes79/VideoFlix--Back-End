package com.rodrigohf.videoFlix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigohf.videoFlix.domains.Categoria;
import com.rodrigohf.videoFlix.repositories.CategoriaRepository;
import com.rodrigohf.videoFlix.services.exceptions.EntityNotFoundException;

@Service 
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository catRepository;

	public Categoria listarCategoriaPorId(Long id) {
		Categoria obj = catRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("ID "+id+" não encontrado."));
		return obj;
	}
	
	

}
