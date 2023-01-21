package com.rodrigohf.videoFlix.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rodrigohf.videoFlix.DTOs.VideoDTO;
import com.rodrigohf.videoFlix.domains.Video;

@Repository
public interface VideoFlixRepository extends JpaRepository<Video, Long>{
	
	Video findByTituloIgnoreCase(String titulo);
	
	Video findByUrl(String url);
	
	Page<VideoDTO> findByTituloIgnoreCaseContaining(@Param(value="search") String titulo, Pageable paginacao); 
	


}
