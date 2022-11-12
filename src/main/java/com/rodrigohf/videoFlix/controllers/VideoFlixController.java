package com.rodrigohf.videoFlix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigohf.videoFlix.domains.Video;
import com.rodrigohf.videoFlix.repositories.VideoFlixRepository;
import com.rodrigohf.videoFlix.services.VideoFlixService;



@RestController
@RequestMapping("/videos")
public class VideoFlixController {
	
	@Autowired
	private VideoFlixRepository videoflixRepository;
	@Autowired
	private VideoFlixService videoFlixService;
	
	@GetMapping
	public ResponseEntity<List<Video>> buscarVideos(){
		List<Video> obj = videoflixRepository.findAll();
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Video> buscarVideoPorId(@PathVariable Long id){
		Video obj = videoFlixService.buscarVideoPorId(id);
				
		
		return ResponseEntity.ok().body(obj);
	}
	

	

}
