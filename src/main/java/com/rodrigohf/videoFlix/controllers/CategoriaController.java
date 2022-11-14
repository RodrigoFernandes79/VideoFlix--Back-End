package com.rodrigohf.videoFlix.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigohf.videoFlix.domains.Categoria;
import com.rodrigohf.videoFlix.repositories.CategoriaRepository;
import com.rodrigohf.videoFlix.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository catRepository;
	
	@Autowired
	private CategoriaService catService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategorias(){
		List<Categoria> obj = catRepository.findAll();
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> listarCategoriaPorId(@PathVariable Long id){
		Categoria obj = catService.listarCategoriaPorId(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody Categoria categoria){
		Categoria obj = catService.criarCategoria(categoria);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
	}

}
