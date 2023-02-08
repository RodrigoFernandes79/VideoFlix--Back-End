package com.rodrigohf.videoFlix.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rodrigohf.videoFlix.services.security.UsuarioService;
import com.rodrigohf.videoFlix.services.security.jwt.JwtAuthFilter;
import com.rodrigohf.videoFlix.services.security.jwt.JwtService;

@SuppressWarnings("deprecation")
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity  // configurando a anotação @PreAuthorize para permitir acesso somente para admins
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
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
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/videos/**")
		.hasAnyRole("USER","ADMIN")
		.antMatchers(HttpMethod.POST,"/videos/**")
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
		.and().httpBasic()
		.and()
			 .sessionManagement()
			 .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			 
			 http.cors().and().csrf().disable();	  //permissão para acesso do front end
			 
			 
			 http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
			 
		   
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
		
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