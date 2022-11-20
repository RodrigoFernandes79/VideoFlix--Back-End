package com.rodrigohf.videoFlix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}			
		
		

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//configurando a autenticação em memória:
		auth.inMemoryAuthentication()
		.passwordEncoder(passwordEncoder())
		.withUser("Rodrigo")
		.password(passwordEncoder().encode("123"))
		.roles("USER");

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
		.and()
		.httpBasic();
	
	}
	
	

}
