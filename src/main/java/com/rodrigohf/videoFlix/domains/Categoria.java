package com.rodrigohf.videoFlix.domains;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Categoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	private String cor;
	@JsonIgnore
	@OneToMany(mappedBy = "categoria")
	private Set<Video> videos = new HashSet<>();

	public Categoria(Long id, String titulo, String cor) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.cor = cor;
	}
	
	

}
