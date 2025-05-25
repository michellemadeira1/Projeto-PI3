package com.condominio.condominio.controller;

import com.condominio.condominio.model.Morador;
import com.condominio.condominio.model.Usuario;
import com.condominio.condominio.service.MoradorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/moradores")
@CrossOrigin("*")
public class MoradorController {

    @Autowired
    private MoradorService moradorService;

    @GetMapping
    public ResponseEntity<List<Morador>> listarTodos() {
        return ResponseEntity.ok(moradorService.listarTodos());
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Morador> buscarPorId(@PathVariable Long id) {
        return moradorService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<Morador>> buscarPorNome(@RequestParam String nome) {
        List<Morador> moradores = moradorService.buscarPorNome(nome);
        return ResponseEntity.ok(moradores);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Morador> cadastrar(@RequestBody Morador morador) {
        Morador novoMorador = moradorService.cadastrar(morador);
        return ResponseEntity.status(201).body(novoMorador);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Morador> atualizar(@PathVariable Long id, @RequestBody Morador moradorAtualizado) {
        return moradorService.atualizar(id, moradorAtualizado).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = moradorService.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
