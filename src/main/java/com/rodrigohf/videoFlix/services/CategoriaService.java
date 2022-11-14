package com.rodrigohf.videoFlix.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rodrigohf.videoFlix.domains.Categoria;
import com.rodrigohf.videoFlix.repositories.CategoriaRepository;
import com.rodrigohf.videoFlix.services.exceptions.DataViolationException;
import com.rodrigohf.videoFlix.services.exceptions.EntityNotFoundException;

@Service 
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository catRepository;

	public Categoria listarCategoriaPorId(Long id) {
		Categoria obj = catRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("ID "+id+" não encontrado."));
		return obj;
	}

	public Categoria criarCategoria(Categoria categoria) {
		Categoria findTitulo = catRepository.findByTituloContainsIgnoreCase(categoria.getTitulo());
		Categoria findCor = catRepository.findByCorContainsIgnoreCase(categoria.getCor());
		
		if(findTitulo != null) {
			throw new DataViolationException(
					"O Campo "+categoria.getTitulo()+" já existe no Banco de Dados");
		}if(findCor != null) {
			throw new DataViolationException(
					"O Campo "+categoria.getCor()+" já existe no Banco de Dados");
		}
		
		Categoria obj = catRepository.save(categoria);
		
		return obj;
	}

	public Categoria atualizarCategoria( Categoria categoria, Long id) {
		Categoria findTitulo = catRepository.findByTituloContainsIgnoreCase(categoria.getTitulo());
		Categoria findCor = catRepository.findByCorContainsIgnoreCase(categoria.getCor());
		if(findTitulo != null && findTitulo.getId() != id) {
			throw new DataViolationException(
					"O Campo "+categoria.getTitulo()+" já existe no Banco de Dados");
		}if(findCor != null && findCor.getId() != id) {
			throw new DataViolationException(
					"O Campo "+categoria.getCor()+" já existe no Banco de Dados");
		}
		return catRepository.findById(id).map(objCat ->{
			objCat.getId();
			objCat.setTitulo(categoria.getTitulo());
			objCat.setCor(categoria.getCor());
			
			Categoria obj = catRepository.save(objCat);
			return obj;
		}).orElseThrow(() -> new EntityNotFoundException("Id "+id+" não encontrado."));
		
		
		
	}
	
	

}
