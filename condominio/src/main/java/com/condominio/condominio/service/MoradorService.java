package com.condominio.condominio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.condominio.model.Morador;
import com.condominio.condominio.repository.MoradorRepository;

@Service
public class MoradorService {

	@Autowired
	private MoradorRepository moradorRepository;

	public List<Morador> listarTodos() {
		return moradorRepository.findAll();
	}

	public Optional<Morador> buscarPorId(Long id) {
		return moradorRepository.findById(id);
	}

	public List<Morador> buscarPorNome(String nome) {
		return moradorRepository.findAllByNomeContainingIgnoreCase(nome);
	}

	public Morador cadastrar(Morador morador) {
		return moradorRepository.save(morador);
	}

	public Optional<Morador> atualizar(Long id, Morador moradorAtualizado) {
		return moradorRepository.findById(id).map(morador -> {
			morador.setNome(moradorAtualizado.getNome());
			morador.setApartamento(moradorAtualizado.getApartamento());
			morador.setTelefone(moradorAtualizado.getTelefone());
			return moradorRepository.save(morador);
		});
	}

	public boolean deletar(Long id) {
		return moradorRepository.findById(id).map(morador -> {
			moradorRepository.delete(morador);
			return true;
		}).orElse(false);
	}
}
