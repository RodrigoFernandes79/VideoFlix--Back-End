package com.rodrigohf.videoFlix.services;



import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.rodrigohf.videoFlix.DTOs.VideoDTO;
import com.rodrigohf.videoFlix.domains.Categoria;
import com.rodrigohf.videoFlix.domains.Video;
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
		Categoria findTitulo = catRepository.findByTituloIgnoreCaseContaining(categoria.getTitulo());
		Categoria findCor = catRepository.findByCorIgnoreCaseContaining(categoria.getCor());
		
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
		Categoria findTitulo = catRepository.findByTituloIgnoreCaseContaining(categoria.getTitulo());
		Categoria findCor = catRepository.findByCorIgnoreCaseContaining(categoria.getCor());
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

	public void deletarCategoriaPorId(Long id) {
		 try {
			Categoria cat = catRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("Id "+id+" não encontrado."));
			 
			
				 catRepository.delete(cat);
			 
		 }catch(DataIntegrityViolationException e) {
			 
			 throw new DataIntegrityViolationException(
					 "Não foi possível deletar. "+id+" possui vídeos associados");
			 
		 }
		
	}

	public Set<VideoDTO> buscarVideoPorCategoriaId(Long id) {
		Categoria cat = catRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Id nao encontrado"));
		Set<Video> obj = cat.getVideos();
		if(obj.isEmpty()) {
			throw new DataViolationException("Não existe nenhum video associado");
		}
		Set<VideoDTO> objDto = ConvertToDTO(obj);
		return objDto;
	}

	private static Set<VideoDTO> ConvertToDTO(Set<Video> obj) {
Set<VideoDTO> videosDTO =  obj.stream().map(VideoDTO::new).collect(Collectors.toSet());
		
		return videosDTO;
	}
	
	

}
