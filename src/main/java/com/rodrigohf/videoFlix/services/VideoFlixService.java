package com.rodrigohf.videoFlix.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigohf.videoFlix.domains.Video;
import com.rodrigohf.videoFlix.repositories.VideoFlixRepository;
import com.rodrigohf.videoFlix.services.exceptions.DataViolationException;
import com.rodrigohf.videoFlix.services.exceptions.EntityNotFoundException;



@Service
public class VideoFlixService {
	
	@Autowired
	private VideoFlixRepository videoFlixRepository;

	public Video buscarVideoPorId(Long id) {
		Video obj = videoFlixRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("ID "+id+" não encontrado."));
		return obj;
	}
	
	public  Video inserirVideo(Video video) {
		Video findUrl = videoFlixRepository.findByUrlIgnoreCase(video.getUrl());
		Video findTitulo = videoFlixRepository.findByTituloIgnoreCase(video.getTitulo());
		if(findUrl != null ) {
			throw new DataViolationException("Url "+video.getUrl()+" Já existe no Banco de dados.");
		}if(findTitulo != null) {
			throw new DataViolationException("Título "+video.getTitulo()+" Já existe no Banco de dados.");
		}
		Video obj = videoFlixRepository.save(video);
		return obj;
	}

	public Video atualizarVideo(Long id, Video video) {
		Video findTitulo = videoFlixRepository.findByTituloIgnoreCase(video.getTitulo());
		
		if(findTitulo != null && findTitulo.getId() != id) {
			throw new DataViolationException("Título "+video.getTitulo()+" já Existe no Banco de dados");
		}
		
		 return videoFlixRepository.findById(id).map(newVideo ->{
		newVideo.getId();
		newVideo.setTitulo(video.getTitulo());
		newVideo.setDescricao(video.getDescricao());
		
		Video videoObj = videoFlixRepository.save(newVideo);
		return videoObj;
				 
		 }).orElseThrow(()-> new EntityNotFoundException("ID "+id+" não encontrado."));
		
	}

	public void deletarVideo(Long id) {
		
		Video obj = videoFlixRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("ID "+id+" não encontrado."));
		
		 videoFlixRepository.delete(obj);
		
	}

	
	
	

}
