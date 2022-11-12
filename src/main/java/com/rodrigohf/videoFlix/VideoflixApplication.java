package com.rodrigohf.videoFlix;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rodrigohf.videoFlix.domains.Categoria;
import com.rodrigohf.videoFlix.domains.Video;
import com.rodrigohf.videoFlix.repositories.CategoriaRepository;
import com.rodrigohf.videoFlix.repositories.VideoFlixRepository;

@SpringBootApplication
public class VideoflixApplication implements CommandLineRunner {

	@Autowired
	private VideoFlixRepository videoFlixRepository;
	
	@Autowired
	private CategoriaRepository catRepository;

	public static void main(String[] args) {
		SpringApplication.run(VideoflixApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Música e Arte","Black");
		Categoria cat2 = new Categoria(null, "Esporte e Entretenimento","Red");
		Categoria cat3 = new Categoria(null, "Infantil","Blue");
		Categoria cat4 = new Categoria(null, "Política, Jornalismo e Podcasts","Green");
			catRepository.saveAll(Arrays.asList(cat1, cat2,cat3,cat4));
			
		Video vid1 = new Video(null, "Guns N Roses Nightrain Legendado", "Rock n Roll anos 90",
				"https://www.youtube.com/watch?v=7EPXGZLSmUs",cat1);

		Video vid2 = new Video(null, "Counting Crows - Mr. Jones (Official Music Video)",
				"Rock n Roll anos 90",
				"https://www.youtube.com/watch?v=-oqAU5VxFWs",cat1);
		
		Video vid3 = new Video(null, "O que Lula disse sobre mensalão e petrolão",
				"BBC News Brasil",
				"https://www.youtube.com/watch?v=RjebOJ13Sf8",cat4);
		
		Video vid4 = new Video(null, "CONVOCAÇÃO DA SELEÇÃO AO VIVO! VEJA A LISTA DE TITE PARA A COPA DO MUNDO NO CATAR",
				"TNT Sports Brasil",
				"https://www.youtube.com/watch?v=JYJj7sIrvS4",cat2);

		videoFlixRepository.saveAll(Arrays.asList(vid1, vid2, vid3, vid4));

	}
}