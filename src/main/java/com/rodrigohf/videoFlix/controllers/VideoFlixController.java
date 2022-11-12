package com.rodrigohf.videoFlix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@PostMapping
	public ResponseEntity<Video> inserirVideo(@RequestBody Video video){
		Video obj = videoFlixService.inserirVideo(video);
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
		
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Video> atualizarVideo(@PathVariable Long id, @RequestBody Video video){
		
		Video obj = videoFlixService.atualizarVideo(id,video);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(obj);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarVideo(@PathVariable Long id) {
		
		videoFlixService.deletarVideo(id);
	}
	

}
