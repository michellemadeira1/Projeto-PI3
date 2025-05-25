package com.condominio.condominio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.condominio.condominio.model.Encomenda;
import com.condominio.condominio.model.StatusEncomenda;
import com.condominio.condominio.service.EncomendaService;

@RestController
@RequestMapping("/encomendas")
@CrossOrigin("*")
public class EncomendaController {

    @Autowired
    private EncomendaService encomendaService;

    @GetMapping
    public ResponseEntity<List<Encomenda>> listarTodos() {
        return ResponseEntity.ok(encomendaService.listarTodos());
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Encomenda> buscarPorId(@PathVariable Long id) {
        return encomendaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/morador")
    public ResponseEntity<List<Encomenda>> buscarPorNomeMorador(@RequestParam String nome) {
        List<Encomenda> encomendas = encomendaService.buscarPorNomeMorador(nome);
        return ResponseEntity.ok(encomendas);
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<Encomenda> cadastrar(@RequestBody Encomenda encomenda) {
        return ResponseEntity.ok(encomendaService.cadastrar(encomenda));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Encomenda> atualizar(@PathVariable Long id, @RequestBody Encomenda encomendaAtualizada) {
        return encomendaService.atualizar(id, encomendaAtualizada)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}/descricao")
    public ResponseEntity<String> buscarDescricaoPorId(@PathVariable Long id) {
        return encomendaService.buscarPorId(id)
                .map(encomenda -> ResponseEntity.ok(encomenda.getDescricao()))
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar por status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Encomenda>> buscarPorStatus(@PathVariable StatusEncomenda status) {
        return ResponseEntity.ok(encomendaService.buscarPorStatus(status));
    }
    @GetMapping("/status")
    public ResponseEntity<StatusEncomenda[]> listarStatus() {
        return ResponseEntity.ok(StatusEncomenda.values());
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return encomendaService.deletar(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


}
