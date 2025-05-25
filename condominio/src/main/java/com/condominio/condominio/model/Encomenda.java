package com.condominio.condominio.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Encomenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private LocalDate dataRecebimento;

	@Enumerated(EnumType.STRING)
	private StatusEncomenda status;

	@ManyToOne
	@JoinColumn(name = "morador_id", nullable = false)
	@JsonBackReference
	private Morador morador;

	@ManyToOne
	@JoinColumn(name = "usuario", nullable = false)
	private Usuario usuario;

	@OneToMany(mappedBy = "encomenda", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Notificacao> notificacoes = new ArrayList<>();

	public Encomenda() {
	}

	

	public Encomenda(Long id, String descricao, LocalDate dataRecebimento, StatusEncomenda status, Morador morador,
			Usuario usuario, List<Notificacao> notificacoes) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataRecebimento = dataRecebimento;
		this.status = status;
		this.morador = morador;
		this.usuario = usuario;
		this.notificacoes = notificacoes;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(LocalDate dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public StatusEncomenda getStatus() {
		return status;
	}

	public void setStatus(StatusEncomenda status) {
		this.status = status;
	}

	public Morador getMorador() {
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Notificacao> getNotificacoes() {
		return notificacoes;
	}

	public void setNotificacoes(List<Notificacao> notificacoes) {
		this.notificacoes = notificacoes;
	}

}
