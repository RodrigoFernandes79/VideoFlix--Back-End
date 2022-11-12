package com.rodrigohf.videoFlix.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Video {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message ="Campo TÍTULO não pode ser vazio")
	@Column(length = 50)
	private String titulo;
	
	@NotEmpty(message = "O campo DESCRIÇÃO não pode ser vazio.")
	@Column(length = 150)
	private String descricao;
	
	@NotEmpty(message = "O campo URL não pode ser vazio.")
	@URL(message = "Informe uma URL válida.")
	private String url;
	
	
	@ManyToOne
	@JoinColumn(name ="categoria_id")
	private Categoria categoria;

}
