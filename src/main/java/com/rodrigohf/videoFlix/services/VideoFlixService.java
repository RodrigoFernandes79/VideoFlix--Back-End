package com.rodrigohf.videoFlix.services;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigohf.videoFlix.DTOs.VideoDTO;
import com.rodrigohf.videoFlix.domains.Video;
import com.rodrigohf.videoFlix.repositories.VideoFlixRepository;
import com.rodrigohf.videoFlix.services.exceptions.DataViolationException;
import com.rodrigohf.videoFlix.services.exceptions.EntityNotFoundException;



@Service
public class VideoFlixService {
	
	@Autowired
	private VideoFlixRepository videoFlixRepository;
	
	public List<VideoDTO> buscarVideos() {
		 List<Video> obj = videoFlixRepository.findAll();
		 List<VideoDTO> objDto = ConvertToDTO(obj); 
		return objDto;
	}

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

	public Video atualizarVideo(Long id,VideoDTO videoDto) {
		Video findTitulo = videoFlixRepository.findByTituloIgnoreCase(videoDto.getTitulo());
		
		if(findTitulo != null && findTitulo.getId() != id) {
			throw new DataViolationException("Título "+videoDto.getTitulo()+" já Existe no Banco de dados");
		} 
		 return videoFlixRepository.findById(id).map(newVideo ->{
		newVideo.getId();	 
		newVideo.setTitulo(videoDto.getTitulo());
		newVideo.setDescricao(videoDto.getDescricao());
		newVideo.getUrl();
	
			
		Video videoObj = videoFlixRepository.save(newVideo);
		return videoObj;
			 
		 }).orElseThrow(()-> new EntityNotFoundException("ID "+id+" não encontrado."));
		
	}	
	

	public void deletarVideo(Long id) {
		
		Video obj = videoFlixRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("ID "+id+" não encontrado."));
		
		 videoFlixRepository.delete(obj);
		
	}

	public static List<VideoDTO> ConvertToDTO(List<Video> videos) {
		
		List<VideoDTO> videosDTO = videos.stream().map(VideoDTO::new).collect(Collectors.toList());
		
		return videosDTO;
		
	}

	
	
	

}
