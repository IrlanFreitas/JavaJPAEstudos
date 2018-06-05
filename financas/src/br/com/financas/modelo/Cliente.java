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
	
	/* Indicando que será uma ligação única 
	 * porém, só funciona no momento de CRIAÇÃO DO SCHEMA
	 * necessário saber se a modificação deve ser feita no banco
	 * caso já exista.
	 * Funciona como a constraint unique.
	 */
	@JoinColumn(unique = true) 
	//Indicando sempre a cardinalidade dos relacionamentos porem, não faz a restrição
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
