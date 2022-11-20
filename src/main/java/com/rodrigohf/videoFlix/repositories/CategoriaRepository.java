package com.rodrigohf.videoFlix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rodrigohf.videoFlix.domains.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	Categoria findByTituloIgnoreCaseContaining(String titulo);
	
	Categoria findByCorIgnoreCaseContaining(String cor);

	
	


}
