package com.condominio.condominio.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Notificacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dataEnvio;
	private Boolean statusEnvio;
	private String mensagem;

	@ManyToOne
	@JoinColumn(name = "encomenda_id", nullable = false)
	private Encomenda encomenda;

	public Notificacao() {
	}

	public Notificacao(Long id, LocalDateTime dataEnvio, Boolean statusEnvio, String mensagem, Encomenda encomenda) {
		super();
		this.id = id;
		this.dataEnvio = dataEnvio;
		this.statusEnvio = statusEnvio;
		this.mensagem = mensagem;
		this.encomenda = encomenda;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDateTime dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Boolean getStatusEnvio() {
		return statusEnvio;
	}

	public void setStatusEnvio(Boolean statusEnvio) {
		this.statusEnvio = statusEnvio;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Encomenda getEncomenda() {
		return encomenda;
	}

	public void setEncomenda(Encomenda encomenda) {
		this.encomenda = encomenda;
	}

	
	

}
