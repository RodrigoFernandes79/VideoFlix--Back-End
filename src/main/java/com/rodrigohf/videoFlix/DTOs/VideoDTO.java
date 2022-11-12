package com.rodrigohf.videoFlix.DTOs;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import com.rodrigohf.videoFlix.domains.Video;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VideoDTO {
	
	@NotEmpty(message ="Campo TÍTULO não pode ser vazio")
	@Column(length = 50)
	private String titulo;
	
	@NotEmpty(message = "O campo DESCRIÇÃO não pode ser vazio.")
	@Column(length = 150)
	private String descricao;
	
	
	public VideoDTO(Video video) {
		
		this.titulo = video.getTitulo();
		this.descricao = video.getDescricao();
	}
	
	
}
