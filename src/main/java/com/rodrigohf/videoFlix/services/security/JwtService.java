package com.rodrigohf.videoFlix.services.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rodrigohf.videoFlix.domains.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	
	@Value("${security.jwt.expiracao}")
	private String expiracao;
	
	@Value("${security.jwt.chave-de-assinatura}")
	private String chaveDeAssinatura;
	
	public String gerarTokenJwt(Usuario usuario) {
		Long expiraToken = Long.valueOf(expiracao);
		
		LocalDateTime DataHoraExpiracao = LocalDateTime.now().plusMinutes(expiraToken);
		Instant instante = DataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instante);
		return Jwts
				.builder()
				.setSubject(usuario.getEmail())
				.setExpiration(data)
				.signWith(SignatureAlgorithm.HS512, chaveDeAssinatura)
				.compact();
	}
	

}
