package com.rodrigohf.videoFlix.repositories;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rodrigohf.videoFlix.domains.Categoria;

@RunWith(SpringRunner.class)
@SpringBootTest // anotação para testar 
@ActiveProfiles("test") //configurando no ambiente de test H2
class CategoriaRepositoryTest {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Test
	public void deveriaRetornarUmaCategoriaIgnorandoCamelCaseAoBuscarPeloTitulo() {
		String titulo = "CATEGORIA_LIVRE";
		Categoria categoria = categoriaRepository.findByTituloIgnoreCaseContaining(titulo);
		
		Assert.assertNotNull(categoria); // verificando se a categoria existe
		Assert.assertEquals(titulo, categoria.getTitulo()); // verificando se o nome do
		//titulo que eu passei é igual ao titulo que veio do banco
	}
	@Test
	public void deveriaRetornarUmaCategoriaIgnorandoCamelCaseAoBuscarPorCor() {
		String cor = "Red";
		Categoria categoria = categoriaRepository.findByCorIgnoreCaseContaining(cor);
		
		Assert.assertNotNull(categoria); 
		Assert.assertEquals(cor, categoria.getCor()); 
	}

}
