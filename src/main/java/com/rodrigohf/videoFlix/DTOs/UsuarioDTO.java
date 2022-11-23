package com.rodrigohf.videoFlix.DTOs;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.rodrigohf.videoFlix.domains.Usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor

public class UsuarioDTO {

	@Email(message ="Email inválido! Favor inserir um email válido.")
	@NotEmpty(message = "O Campo EMAIL não pode ser vazio.")
	private String email;
	//Senha deve possuir pelo menos 8 caracteres,
			//um dígito,uma letra maíscula e uma minúscula e um caractere especial
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,}$",
			message = "Senha deve possuir pelo menos 8 caracteres,um dígito,uma letra maíscula "
							+ "e uma minúscula e um caractere especial")
	@NotEmpty(message = "O Campo Senha não pode ser vazio.")
	private String senha;
	
	private boolean admin;

	public UsuarioDTO(Usuario usuario) {
		super();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.admin = usuario.isAdmin();
	}

	
	
	
}
