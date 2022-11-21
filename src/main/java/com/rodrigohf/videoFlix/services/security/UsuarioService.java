package com.rodrigohf.videoFlix.services.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rodrigohf.videoFlix.domains.Usuario;
import com.rodrigohf.videoFlix.repositories.UsuarioRepository;
import com.rodrigohf.videoFlix.services.exceptions.DataViolationException;
import com.rodrigohf.videoFlix.services.exceptions.SenhaInvalidaException;
import com.rodrigohf.videoFlix.services.exceptions.UsernameNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService{
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 Usuario usuario = usuarioRepository.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("Usuário "+email+" não encontrado"));
		
		 String[] roles = usuario.isAdmin() ? new String[] {"ADMIN","USER"} : new String[] {"USER"}; 
		 
		return User
				.builder()
				.username(usuario.getEmail())
				.password(usuario.getSenha())
				.roles(roles)
				.build();
	}
	
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		Usuario findEmail = usuarioRepository.encontrarEmail(usuario.getEmail());
		
		if(findEmail != null) {
		throw new DataViolationException("Email "+usuario.getEmail()+" já existe na base de dados");
	}
		
		String SenhaBcryptografada = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(SenhaBcryptografada);
		Usuario obj = usuarioRepository.save(usuario);
		
		return obj;
	}
	
	public UserDetails autenticar(Usuario usuario) {
		UserDetails user = loadUserByUsername(usuario.getEmail());
		boolean senhasEstaoBatendo = passwordEncoder.matches(usuario.getSenha(), user.getPassword());
		if(senhasEstaoBatendo) {
			return user;
		}else {
			throw new SenhaInvalidaException();
		}
	}
	
	

}
