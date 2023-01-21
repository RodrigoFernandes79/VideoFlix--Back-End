package com.rodrigohf.videoFlix.repositories;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rodrigohf.videoFlix.domains.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest // anotação para testar repository
@ActiveProfiles("test") //configurando no ambiente de test H2
class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository repository;
	
	
	@Test
	void obtendoRetornoDeUsuarioPassandoOEmailComoParametro() {
		
		String email = "usuarioFlix@email.com";
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		Assert.assertNotNull(usuario);
		Assert.assertEquals(email, usuario.get().getEmail());
	}
	
}
