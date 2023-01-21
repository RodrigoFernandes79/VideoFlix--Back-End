package com.rodrigohf.videoFlix.repositories;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.rodrigohf.videoFlix.DTOs.VideoDTO;
import com.rodrigohf.videoFlix.domains.Video;

@RunWith(SpringRunner.class)
@SpringBootTest // anotação para testar 
@ActiveProfiles("test") 
class VideoFlixRepositoryTest {
	
	@Autowired
	private VideoFlixRepository repository;
	

	@Test
	void deveriaDevolverUmVideoBuscandoPorTitulo() {
		String titulo = "Guns N Roses Nightrain Legendado";
		Video video = repository.findByTituloIgnoreCase(titulo);
		
		Assert.assertNotNull(video); // verificando se o video existe
		Assert.assertEquals(titulo, video.getTitulo());
		
	}
	@Test
	void deveriaDevolverUmVideoBuscandoPorUrl() {
		String url = "https://www.youtube.com/embed/7EPXGZLSmUs";
		Video video = repository.findByUrl(url);
		
		Assert.assertNotNull(video); // verificando se o video existe
		Assert.assertEquals(url, video.getUrl());
		
	}
	@Test
	void deveriaDevolverUmVideoPaginadoBuscandoPorTituloComParametro() {
		String titulo = "Guns N Roses Nightrain Legendado";
		
		Pageable page = PageRequest.of(0, 10);
		
		Page<VideoDTO> video = repository.findByTituloIgnoreCaseContaining(titulo,page);
		
		Assert.assertNotNull(video); // verificando se o video existe
		Assert.assertEquals(titulo, video.get().findFirst().get().getTitulo());
		
	}

}
