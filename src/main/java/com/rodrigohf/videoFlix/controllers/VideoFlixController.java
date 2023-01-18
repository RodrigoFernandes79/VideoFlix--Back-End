package com.rodrigohf.videoFlix.controllers;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rodrigohf.videoFlix.DTOs.VideoDTO;
import com.rodrigohf.videoFlix.domains.Video;
import com.rodrigohf.videoFlix.services.VideoFlixService;


@CrossOrigin("*")
@RestController
@RequestMapping("/videos")
public class VideoFlixController {
	
	
	@Autowired
	private VideoFlixService videoFlixService;
	
	@GetMapping
	public ResponseEntity<Page<VideoDTO>> buscarVideos(
			
			@RequestParam(value="page",defaultValue= "0")Integer page,
			@RequestParam(value="linesPerPage",defaultValue = "6") Integer linesPerPage,
			@RequestParam(value="orderBy",defaultValue = "titulo") String orderBy,
			@RequestParam(value="direction",defaultValue = "ASC") String direction){
		Page<VideoDTO> obj = videoFlixService.buscarVideos(page,linesPerPage,orderBy,direction);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Video> buscarVideoPorId(@PathVariable Long id){
		Video obj = videoFlixService.buscarVideoPorId(id);
				
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Video> inserirVideo( @Valid @RequestBody Video video){
		Video obj = videoFlixService.inserirVideo(video);
		return ResponseEntity.status(HttpStatus.CREATED).body(obj);
		
	}
	@Transactional
	@PatchMapping("/{id}")
	public ResponseEntity<Video> atualizarVideo(@PathVariable Long id,
			@Valid  @RequestBody VideoDTO videoDto){
		
		Video obj = videoFlixService.atualizarVideo(id,videoDto);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(obj);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarVideo(@PathVariable Long id) {
		
		videoFlixService.deletarVideo(id);
	}
	
	@GetMapping("/titulos") //http://localhost:8080/videos/titulos/?search=titulo
	public ResponseEntity<Page<VideoDTO>> buscarVideosPorTitulo(
								@RequestParam (value="search",required=false) String titulo,
								@RequestParam(value="page",defaultValue= "0")Integer page,
								@RequestParam(value="linesPerPage",defaultValue = "24") Integer linesPerPage,
								@RequestParam(value="orderBy",defaultValue = "titulo") String orderBy,
								@RequestParam(value="direction",defaultValue = "ASC") String direction){
	Page<VideoDTO> objDto = videoFlixService.buscarVideosPorTitulo(titulo,
																   page,
																   linesPerPage,
																   orderBy,
																   direction);
		
		return ResponseEntity.ok().body(objDto);
	}
	
}


