package com.condominio.condominio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.condominio.condominio.model.Notificacao;



@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    List<Notificacao> findByStatusEnvio(Boolean statusEnvio);
    List<Notificacao> findByEncomenda_Morador_NomeContainingIgnoreCase(String nomeMorador);
}

