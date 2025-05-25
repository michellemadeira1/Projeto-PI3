package com.condominio.condominio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.condominio.model.Notificacao;
import com.condominio.condominio.repository.NotificacaoRepository;

@Service
public class NotificacaoService {

	@Autowired
	private NotificacaoRepository notificacaoRepository;

	public List<Notificacao> listarTodos() {
		return notificacaoRepository.findAll();
	}

	public Optional<Notificacao> buscarPorId(Long id) {
		return notificacaoRepository.findById(id);
	}

	public Notificacao cadastrar(Notificacao notificacao) {
		return notificacaoRepository.save(notificacao);
	}

	public Optional<Notificacao> atualizar(Long id, Notificacao notificacaoAtualizada) {
		return notificacaoRepository.findById(id).map(notificacao -> {
			notificacao.setMensagem(notificacaoAtualizada.getMensagem());
			notificacao.setDataEnvio(notificacaoAtualizada.getDataEnvio());
			notificacao.setEncomenda(notificacaoAtualizada.getEncomenda());
			return notificacaoRepository.save(notificacao);
		});
	}

	public boolean deletar(Long id) {
		return notificacaoRepository.findById(id).map(notificacao -> {
			notificacaoRepository.delete(notificacao);
			return true;
		}).orElse(false);
	}
}
