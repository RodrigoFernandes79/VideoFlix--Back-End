package com.rodrigohf.videoFlix.domains;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

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
	
	@NotEmpty(message = "O Campo TÍTULO é obrigatório.")
	@Column(length = 50)
	private String titulo;
	@NotEmpty(message = "O Campo COR é obrigatório.")
	@Column(length = 20)
	private String cor;
	@JsonIgnore
	@OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
	private Set<Video> videos = new HashSet<>();

	public Categoria(Long id, String titulo, String cor) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.cor = cor;
	}
	
	

}
