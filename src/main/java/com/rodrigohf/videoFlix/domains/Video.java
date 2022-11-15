package com.rodrigohf.videoFlix.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Video {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message ="Campo TÍTULO não pode ser vazio")
	@Column(length = 100)
	private String titulo;
	
	@NotEmpty(message = "O campo DESCRIÇÃO não pode ser vazio.")
	@Column(length = 150)
	private String descricao;
	
	@NotEmpty(message = "O campo URL não pode ser vazio.")
	@URL(message = "Informe uma URL válida.")
	private String url;
	
	
	@ManyToOne
	@JoinColumn(name ="categoria_id")
	@NotNull(message = "O campo CATEGORIA não pode ser vazio.")
	private Categoria categoria;

}
