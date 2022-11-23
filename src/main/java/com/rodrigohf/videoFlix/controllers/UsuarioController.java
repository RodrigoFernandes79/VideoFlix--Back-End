package com.rodrigohf.videoFlix.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rodrigohf.videoFlix.DTOs.CredenciaisDTO;
import com.rodrigohf.videoFlix.DTOs.TokenDTO;
import com.rodrigohf.videoFlix.DTOs.UsuarioDTO;
import com.rodrigohf.videoFlix.domains.Usuario;
import com.rodrigohf.videoFlix.services.exceptions.SenhaInvalidaException;
import com.rodrigohf.videoFlix.services.exceptions.UsernameNotFoundException;
import com.rodrigohf.videoFlix.services.security.UsuarioService;
import com.rodrigohf.videoFlix.services.security.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	private final JwtService jwtService;
	
	
	
	@PostMapping
	public ResponseEntity<Usuario> salvarSenha(@Valid @RequestBody UsuarioDTO usuario){
		
		
		Usuario obj = usuarioService.salvarUsuario(usuario);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
		
	}
	
	@PostMapping("/auth")
	public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
		try {
			
			Usuario usuario = Usuario.builder()
					.email(credenciais.getEmail())
					.senha(credenciais.getSenha())
					.build();
					
			
			UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
			
			String token = jwtService.gerarTokenJwt(usuario);
			return new TokenDTO(usuario.getEmail(), token);
					
		}catch(UsernameNotFoundException | SenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
		
		
		
	}
	


}
