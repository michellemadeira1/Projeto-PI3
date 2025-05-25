package com.condominio.condominio.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.condominio.condominio.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);

    List<Usuario> findAllByNomeContainingIgnoreCase(String nome);
}
