package com.rodrigohf.videoFlix.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigohf.videoFlix.domains.Video;
import com.rodrigohf.videoFlix.repositories.VideoFlixRepository;
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
	
	

}
