package com.rodrigohf.videoFlix.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService{
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(!username.equals("Rodrigo")) {
			throw new UsernameNotFoundException("Usuário "+username+" não encontrado");
		}
		return User
				.builder()
				.username("Rodrigo")
				.password(passwordEncoder.encode("123"))
				.roles("USER","ADMIN")
				.build();
	}
	
	

}
