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
		
		Usuario user1 = new Usuario(null,"usuarioFlix@email.com",passwordEncoder.encode("Ab0brinh4@"), false);
		
		
		usuarioRepository.saveAll(Arrays.asList(user1));
		
		Categoria cat1 = new Categoria(null, "CATEGORIA_LIVRE", "Orange");
		Categoria cat2 = new Categoria(null, "Música e Arte", "Black");
		Categoria cat3 = new Categoria(null, "Esporte e Entretenimento", "Red");
		Categoria cat4 = new Categoria(null, "Infantil", "Blue");
		Categoria cat5 = new Categoria(null, "Política, Jornalismo e Podcasts", "Green");
		catRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));

		Video vid1 = new Video(null, "Guns N Roses Nightrain Legendado", "Rock n Roll anos 90",
				"https://www.youtube.com/watch?v=7EPXGZLSmUs", cat2);

		Video vid2 = new Video(null, "Counting Crows - Mr. Jones (Official Music Video)", "Rock n Roll anos 90",
				"https://www.youtube.com/watch?v=-oqAU5VxFWs", cat2);

		Video vid3 = new Video(null, "O que Lula disse sobre mensalão e petrolão", "BBC News Brasil",
				"https://www.youtube.com/watch?v=RjebOJ13Sf8", cat5);

		Video vid4 = new Video(null,
				"CONVOCAÇÃO DA SELEÇÃO AO VIVO! VEJA A LISTA DE TITE PARA A COPA DO MUNDO NO CATAR",
				"TNT Sports Brasil", "https://www.youtube.com/watch?v=JYJj7sIrvS4", cat3);

		videoFlixRepository.saveAll(Arrays.asList(vid1, vid2, vid3, vid4));

	}
}
