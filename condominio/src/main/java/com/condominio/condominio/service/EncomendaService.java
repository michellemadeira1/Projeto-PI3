package com.condominio.condominio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.condominio.condominio.model.Encomenda;
import com.condominio.condominio.model.StatusEncomenda;
import com.condominio.condominio.model.Usuario;
import com.condominio.condominio.repository.EncomendaRepository;

@Service
public class EncomendaService {

    @Autowired
    private EncomendaRepository encomendaRepository;

    public List<Encomenda> listarTodos() {
        return encomendaRepository.findAll();
    }

    public Optional<Encomenda> buscarPorId(Long id) {
        return encomendaRepository.findById(id);
    }

    public Encomenda cadastrar(Encomenda encomenda) {
        return encomendaRepository.save(encomenda);
    }

    public Optional<Encomenda> atualizar(Long id, Encomenda encomendaAtualizada) {
        return encomendaRepository.findById(id).map(encomenda -> {
            encomenda.setDescricao(encomendaAtualizada.getDescricao());
            encomenda.setDataRecebimento(encomendaAtualizada.getDataRecebimento());
            encomenda.setStatus(encomendaAtualizada.getStatus());
            encomenda.setMorador(encomendaAtualizada.getMorador());
            encomenda.setUsuario(encomendaAtualizada.getUsuario());
            return encomendaRepository.save(encomenda);
        });
    }

    public boolean deletar(Long id) {
        return encomendaRepository.findById(id).map(encomenda -> {
            encomendaRepository.delete(encomenda);
            return true;
        }).orElse(false);
    }

    public List<Encomenda> buscarPorNomeMorador(String nomeMorador) {
        return encomendaRepository.findAllByMoradorNomeContainingIgnoreCase(nomeMorador);
    }

    public List<Encomenda> buscarPorStatus(StatusEncomenda status) {
        return encomendaRepository.findByStatus(status);
    }
}
