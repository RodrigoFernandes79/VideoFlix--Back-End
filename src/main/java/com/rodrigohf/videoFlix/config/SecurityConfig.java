package com.rodrigohf.videoFlix.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.rodrigohf.videoFlix.services.security.UsuarioService;
import com.rodrigohf.videoFlix.services.security.jwt.JwtAuthFilter;
import com.rodrigohf.videoFlix.services.security.jwt.JwtService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Lazy
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private JwtService jwtService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}			
		
	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(jwtService, usuarioService);
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
			 .sessionManagement()
			 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			 .and()
			 .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
			 .cors(); //permissão para acesso do front end
		     
		      
		
	
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// permissão de acesso paginas web(swagger):
		web.ignoring().antMatchers(
				"/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/h2-console/**",
                "/webjars/**");
		
	}
	
	
	

}
