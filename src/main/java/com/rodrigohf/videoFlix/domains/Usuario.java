package com.rodrigohf.videoFlix.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	
	@Email(message ="Email inválido! Favor inserir um email válido.")
	@NotEmpty(message = "O Campo EMAIL não pode ser vazio.")
	private String email;
	
	@NotEmpty(message = "O Campo Senha não pode ser vazio.")
	@Column(unique = true)
	private String senha;
	
	private boolean admin;

}
