package com.condominio.condominio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.condominio.condominio.model.Encomenda;
import com.condominio.condominio.model.Morador;
import com.condominio.condominio.model.StatusEncomenda;
import com.condominio.condominio.model.Usuario;



@Repository
public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {
	List<Encomenda> findAllByMoradorNomeContainingIgnoreCase(String nomeMorador);
	Optional<Encomenda> findByMoradorAndUsuarioAndStatus(Morador morador, Usuario usuario, StatusEncomenda status);
    List<Encomenda> findByStatus(StatusEncomenda status);
}
