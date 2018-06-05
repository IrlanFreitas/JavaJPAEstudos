package br.com.financas.modelo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.financas.enums.TipoMovimentacao;

@Entity
@NamedQuery(query = " SELECT avg(m.valor) from Movimentacao m " 
		+ "WHERE m.conta = :pConta "
		+ " AND m.tipo = :pTipo " 
		+ " GROUP BY m.data ", 
		name = "MediasPorDiaETipo")
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private BigDecimal valor; //Tipo com maior precisão
	
	private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP) //Passando a data para o banco
	private Calendar data;
	
	@Enumerated(EnumType.STRING) //Enum para String
	private TipoMovimentacao tipo;
	
	/*	O que queremos é que ambas as partes representem o mesmo relacionamento. 
	Para isto, precisaremos escolher "a parte forte", que cria o relacionamento, 
	a coluna de chave estrangeira (conta_id), que no nosso caso é @ManyToOne na classe Movimentacao.*/
	
	@ManyToOne //Muitas movimentações para uma conta  - Relacionamento Bidirecional, Esse é lado forte do relacionamento é quem cria.
	private Conta conta;
	
	//Foi criada uma tabela de muitos pra muitos chamada movimentacao_categoria
	@ManyToMany (fetch = FetchType.EAGER)
	//http://blog.caelum.com.br/entendendo-o-lazy-e-o-eager-load-da-jpa/
	//http://blog.caelum.com.br/enfrentando-a-lazyinitializationexception-no-hibernate/
	private List<Categoria> categoria;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public TipoMovimentacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Movimentacao [id=" + id + ", valor=" + valor + ", descricao=" + descricao + ", data=" + new SimpleDateFormat("dd/MM/yyyy").format(data.getTime()) + ", tipo="
				+ tipo + ", conta=" + conta + ", categoria=" + categoria + "]";
	}
	
	
}
