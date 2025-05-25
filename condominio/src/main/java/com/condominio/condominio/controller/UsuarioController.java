package com.condominio.condominio.controller;


import com.condominio.condominio.dto.LoginDTO;
import com.condominio.condominio.model.Usuario;
import com.condominio.condominio.service.CustomUserDetailsService;
import com.condominio.condominio.service.JwtService;
import com.condominio.condominio.service.UsuarioService;


import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private JwtService jwtService;

	@Autowired
	private CustomUserDetailsService userDetailsService;


	@GetMapping
	public ResponseEntity<List<Usuario>> listarTodos() {
		return ResponseEntity.ok(usuarioService.listarTodos());
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		return usuarioService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Usuario>> buscarPorNome(@PathVariable String nome) {
		List<Usuario> usuarios = usuarioService.buscarPorNome(nome);
		if (usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}

	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
	    Usuario novoUsuario = usuarioService.cadastrar(usuario);
	    return ResponseEntity.status(201).body(novoUsuario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
		return usuarioService.atualizar(id, usuarioAtualizado).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	 @PostMapping("/login")
	 public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
	        Optional<Usuario> usuarioOpt = usuarioService.autenticar(loginDTO.getEmail(), loginDTO.getSenha());

	        if (usuarioOpt.isPresent()) {
	            String token = jwtService.generateToken(usuarioOpt.get());
	            return ResponseEntity.ok(new TokenResponse(token));
	        } else {
	            return ResponseEntity.status(401).body("Email ou senha inválidos");
	        }
	    }

	    // Classe interna ou externa para responder com o token
	    public static class TokenResponse {
	        private String token;
	        public TokenResponse(String token) {
	            this.token = token;
	        }
	        public String getToken() {
	            return token;
	        }
	        public void setToken(String token) {
	            this.token = token;
	        }
	    }

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		boolean deletado = usuarioService.deletar(id);
		if (deletado) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
