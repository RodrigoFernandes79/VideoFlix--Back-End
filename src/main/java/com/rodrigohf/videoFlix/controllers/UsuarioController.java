package com.rodrigohf.videoFlix.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigohf.videoFlix.domains.Usuario;
import com.rodrigohf.videoFlix.services.security.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	
	
	@PostMapping
	public ResponseEntity<Usuario> salvarSenha(@Valid @RequestBody Usuario usuario){
		
		
		Usuario obj = usuarioService.salvarUsuario(usuario);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
		
	}
	
	

}
