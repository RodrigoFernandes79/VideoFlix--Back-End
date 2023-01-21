package com.rodrigohf.videoFlix.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
@RunWith(SpringRunner.class)
@SpringBootTest // anotação para testar  
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UsuarioControllerTest {
	
	@Autowired
	private MockMvc mockMvc; // mockando o metodo simulando um postman e irá disparar a requisiçao
	
	@Test
	void deveriaRetornarUmaExceptionUnauthorizedCasoDadosDeAutenticacaoEstejamIncorretos() throws Exception {
		
		URI uri = new URI("/usuarios/auth");
		
		String json = "{\"email\":\"invalido@email.com\", \"senha\":\"123456}\"";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
				 //verifica se o status é UNAUTORIZED
	}

}
