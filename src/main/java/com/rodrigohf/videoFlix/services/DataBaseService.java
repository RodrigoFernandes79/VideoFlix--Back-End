package com.rodrigohf.videoFlix.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rodrigohf.videoFlix.domains.Categoria;
import com.rodrigohf.videoFlix.domains.Usuario;
import com.rodrigohf.videoFlix.domains.Video;
import com.rodrigohf.videoFlix.repositories.CategoriaRepository;
import com.rodrigohf.videoFlix.repositories.UsuarioRepository;
import com.rodrigohf.videoFlix.repositories.VideoFlixRepository;

@Service
public class DataBaseService {

	@Autowired
	private VideoFlixRepository videoFlixRepository;

	@Autowired
	private CategoriaRepository catRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void instanciarDataBaseTestProfile() throws ParseException {
		
		Usuario user1 = new Usuario(null,"Rodrigo","usuarioFlix@email.com",passwordEncoder.encode("Ab0brinh4@"), true);
		
		
		usuarioRepository.saveAll(Arrays.asList(user1));
		
		Categoria cat1 = new Categoria(null, "CATEGORIA_LIVRE", "Orange");
		Categoria cat2 = new Categoria(null, "Música e Arte", "Black");
		Categoria cat3 = new Categoria(null, "Esporte e Entretenimento", "Red");
		Categoria cat4 = new Categoria(null, "Infantil", "Blue");
		Categoria cat5 = new Categoria(null, "Política, Jornalismo e Podcasts", "Green");
		catRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));

		Video vid1 = new Video(null, "Guns N Roses Nightrain Legendado", "Rock n Roll anos 90",
				"https://www.youtube.com/embed/7EPXGZLSmUs", cat2);

		Video vid2 = new Video(null, "Counting Crows - Mr. Jones (Official Music Video)", "Rock n Roll anos 90",
				"https://www.youtube.com/embed/-oqAU5VxFWs", cat2);

		Video vid3 = new Video(null, "O que Lula disse sobre mensalão e petrolão", "BBC News Brasil",
				"https://www.youtube.com/embed/RjebOJ13Sf8", cat5);

		Video vid4 = new Video(null,
				"CONVOCAÇÃO DA SELEÇÃO! VEJA A LISTA DE TITE PARA A COPA DO MUNDO NO CATAR",
				"TNT Sports Brasil", "https://www.youtube.com/embed/JYJj7sIrvS4", cat3);
		
		Video vid5 = new Video(null,
				"Curso Angular 9 #10 Renderizando dados no HTML",
				 "Michelli Brito", "https://www.youtube.com/embed/AeoxDGhsmQE",
				 cat5);
		Video vid6 = new Video(null,
				"LOJAS AMERICANAS FALIU? (AMER3) | R$20BI DE DÍVIDA",
				"O Primo Rico","https://www.youtube.com/embed/FsAj9uJC_u4",cat5);
		
		Video vid7 = new Video(null,
				"SOBREVIVENDO 103 ANOS COMO HOMEM-ARANHA NO MINECRAFT",
				"Athos","https://www.youtube.com/embed/2RfJnUZ0Tw4",cat1);
		
		Video vid8 = new Video(null,
				"Spring Boot 3.0 Security | Authentication and Authorization |",
				" javaTechie","https://www.youtube.com/embed/R76S0tfv36w",cat5);
		Video vid9 = new Video(null,
				"Cuidado con la Bomba Chita - Las Canciones del Zoo",
				"El Reino Infantil","https://www.youtube.com/embed/nBANlLaki_Y",cat4);
		Video vid10 = new Video(null,
				"Mundo Bita - Fazendinha [clipe infantil]",
				"Mundo Bita","https://www.youtube.com/embed/cjONzZPJONc",cat4);
		Video vid11 = new Video(null,
				"22 Momentos Mais Bonitos do Esporte",
				"#Refúgio Mental","https://www.youtube.com/embed/5BmVao8sZlQ",cat3);
		Video vid12 = new Video(null,
				"Pessoas que FALARAM BESTEIRAS AO VIVO",
				"Mundo Bizarro","https://www.youtube.com/embed/EZKCknFCbUU",cat1);
		Video vid13 = new Video(null,
				"Pearl Jam - Black | Live Pinkpop 1992 | Legendado",
				"Universo da Música","https://www.youtube.com/embed/9180UV0faHk",cat2);

		videoFlixRepository.saveAll(Arrays.asList(vid1, vid2, vid3, vid4, vid5, vid6,
				vid7, vid8, vid9,vid10,vid11, vid12, vid13));

	}
}
