package com.rodrigohf.videoFlix.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rodrigohf.videoFlix.services.security.UsuarioService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Lazy
	@Autowired
	private UsuarioService usuarioService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}			
		
		

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//configurando a autenticação em memória:
		auth
		.userDetailsService(usuarioService)
		.passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//autorização para uma determinada pagina.Quem tem acesso ao quê:
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/videos/**")
		.hasAnyRole("USER","ADMIN")
		.antMatchers(HttpMethod.GET, "/categorias/**")
		.hasAnyRole("USER","ADMIN")
		.antMatchers(HttpMethod.POST,"/categorias/**")
		.hasRole("ADMIN")
		.antMatchers(HttpMethod.PUT,"/categorias/**")
		.hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/categorias/**")
		.hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/usuarios/**")
		.permitAll()
		.anyRequest().authenticated()
		.and()
		.httpBasic();
	
	}
	
	

}
