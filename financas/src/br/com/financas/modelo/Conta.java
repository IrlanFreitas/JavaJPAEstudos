package br.com.financas.modelo;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//Indica para o Hibernate que essa Entidade será uma Tabela, import do javax e não do Hibernate
@Entity 
public class Conta {
	
	//Indicando que o campo é Primary Key
	@Id 
	//Como será a forma de gerenciamento dos valores, aqui com o auto_increment do mysql
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	private String titular;
	private String numero;
	private String banco;
	private String agencia;
	
	@OneToMany(mappedBy = "conta") //Uma conta para muitas movimentações - Relacionamento Bidirecional
	//Esse é o lado fraco do relacionamento, o que reflete.
	private List<Movimentacao> movimentacoes;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	
	@Override
	public String toString() {
		return "Conta [id=" + id + ", titular=" + titular + ", numero=" + numero + ", banco=" + banco + ", agencia="
				+ agencia + "]";
	}
	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}	
	
}
