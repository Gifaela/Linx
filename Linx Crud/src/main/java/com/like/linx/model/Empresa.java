package com.like.linx.model;

import javax.persistence.Table;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_empresa")
public class Empresa{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O Nome da Empresa é Obrigatório!")
	@Size(min = 5)
	private String nome;
	
	@NotBlank(message = "O Segmento da Empresa é Obrigatório!")
	@Size(min = 5)
	private String segmento;
	
//	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("empresa")
//    private List<Situacao> situacao;
	
	@ManyToOne
	@JsonIgnoreProperties("situacao")
	private Situacao situacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	
}
