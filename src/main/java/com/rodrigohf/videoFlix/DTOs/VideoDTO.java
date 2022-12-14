package com.rodrigohf.videoFlix.DTOs;

import com.rodrigohf.videoFlix.domains.Video;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class VideoDTO {
	
	
	private String titulo;
	
	
	private String descricao;
	
	
	private String categoria;
	
	private String url;
	
	public VideoDTO(Video video) {
		
		this.titulo = video.getTitulo();
		this.descricao = video.getDescricao();
		this.categoria = video.getCategoria().getTitulo();
		this.url = video.getUrl();
	}

	

	
	
	
	
}
