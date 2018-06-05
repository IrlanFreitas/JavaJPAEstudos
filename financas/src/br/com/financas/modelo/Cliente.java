package br.com.financas.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String profissao;
	
	private String endereco;
	
	/* Indicando que ser� uma liga��o �nica 
	 * por�m, s� funciona no momento de CRIA��O DO SCHEMA
	 * necess�rio saber se a modifica��o deve ser feita no banco
	 * caso j� exista.
	 * Funciona como a constraint unique.
	 */
	@JoinColumn(unique = true) 
	//Indicando sempre a cardinalidade dos relacionamentos porem, n�o faz a restri��o
	@OneToOne
	private Conta conta;
	
	public Cliente() {
		super();
	}

	public Cliente(String nome, String profissao, String endereco) {
		super();
		this.nome = nome;
		this.profissao = profissao;
		this.endereco = endereco;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", profissao=" + profissao + ", endereco=" + endereco
				+ ", conta=" + conta + "]";
	}
	
	
	
}
