package com.rodrigohf.videoFlix.services.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rodrigohf.videoFlix.domains.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
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

		return Jwts.builder().setSubject(usuario.getEmail()).setExpiration(data)
				.signWith(SignatureAlgorithm.HS512, chaveDeAssinatura).compact();
	}

	// método para decodificar o token
	Claims obterClaims(String token) throws ExpiredJwtException {
		return Jwts.parser().setSigningKey(chaveDeAssinatura).parseClaimsJws(token).getBody();

	}

	public boolean tokenIsValido(String token) {
		try {
			Claims claims = obterClaims(token);
			Date dataExpiracao = claims.getExpiration();
			LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

			// o token é valido quando a data-hora atual não é depois da data hora da
			// expiração do token
			return !LocalDateTime.now().isAfter(data);

		} catch (Exception e) {
			return false;
		}
	}

	// método para saber quem é o usuário que vai estar logado e que mandou o token
	public String obterLoginUsuario(String token) throws ExpiredJwtException {
		return obterClaims(token).getSubject();
	}

}
