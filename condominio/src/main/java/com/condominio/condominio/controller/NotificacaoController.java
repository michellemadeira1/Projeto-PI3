package com.condominio.condominio.controller;

import com.condominio.condominio.model.Notificacao;
import com.condominio.condominio.service.NotificacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacoes")
@CrossOrigin(origins = "http://localhost:4200")
public class NotificacaoController {

	@Autowired
	private NotificacaoService notificacaoService;

	// GET /notificacoes
	@GetMapping
	public ResponseEntity<List<Notificacao>> listarTodas() {
		return ResponseEntity.ok(notificacaoService.listarTodos());
	}

	// GET /notificacoes/{id}
	@GetMapping("/{id}")
	public ResponseEntity<Notificacao> buscarPorId(@PathVariable Long id) {
		return notificacaoService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// POST /notificacoes
	@PostMapping
	public ResponseEntity<Notificacao> cadastrar(@RequestBody Notificacao notificacao) {
		return ResponseEntity.ok(notificacaoService.cadastrar(notificacao));
	}

	// PUT /notificacoes/{id}
	@PutMapping("/{id}")
	public ResponseEntity<Notificacao> atualizar(@PathVariable Long id,
			@RequestBody Notificacao notificacaoAtualizada) {
		return notificacaoService.atualizar(id, notificacaoAtualizada).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// DELETE /notificacoes/{id}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		boolean deletado = notificacaoService.deletar(id);
		if (deletado) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
