package com.rodrigohf.videoFlix.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rodrigohf.videoFlix.domains.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String email);
	
	@Query(value="SELECT u FROM Usuario u WHERE u.email =:email")
	Usuario encontrarEmail(@Param(value="email")String email);
	
	Usuario findBySenha(String senha);

}
