package com.rodrigohf.videoFlix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigohf.videoFlix.domains.Video;

@Repository
public interface VideoFlixRepository extends JpaRepository<Video, Long>{
	
	Video findByTituloIgnoreCase(String titulo);
	
	Video findByUrlIgnoreCase(String url);
	
	

}
