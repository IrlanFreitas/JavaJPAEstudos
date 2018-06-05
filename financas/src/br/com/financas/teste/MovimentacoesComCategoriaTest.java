package br.com.financas.teste;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.financas.enums.TipoMovimentacao;
import br.com.financas.modelo.Categoria;
import br.com.financas.modelo.Conta;
import br.com.financas.modelo.Movimentacao;
import br.com.financas.util.JPAUtil;

public class MovimentacoesComCategoriaTest {
	
	public static void main(String[] args) {
		
		Categoria categoria1 = new Categoria("Viagem");
		Categoria categoria2 = new Categoria("Negócios");

		
		Conta conta = new Conta();
		conta.setId(2);
		
		Movimentacao move1 = new Movimentacao();
		move1.setValor(new BigDecimal("100.00"));
		move1.setTipo(TipoMovimentacao.SAÍDA);
		move1.setData(Calendar.getInstance());
		move1.setDescricao("Viagem a Recife");
		move1.setConta(conta);
		
		Movimentacao move2 = new Movimentacao();
		move2.setValor(new BigDecimal("300.00"));
		move2.setTipo(TipoMovimentacao.SAÍDA);
		
		Calendar amanha = Calendar.getInstance();
		amanha.add(Calendar.DAY_OF_MONTH, 1);
		
		move2.setData(amanha);
		move2.setDescricao("Viagem a Salvador");
		move2.setConta(conta);
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		Categoria categoria1Managed = em.find(Categoria.class, 1);
		Categoria categoria2Managed = em.find(Categoria.class, 2);
		move1.setCategoria(Arrays.asList(categoria1Managed, categoria2Managed));
		move2.setCategoria(Arrays.asList(categoria1Managed, categoria2Managed));
		
		Movimentacao move3 = new Movimentacao();
		move3.setId(4);
		Movimentacao move4 = new Movimentacao();
		move4.setId(5);
		move3.setValor(new BigDecimal("425.00"));
		move3.setTipo(TipoMovimentacao.ENTRADA);
		move3.setData(Calendar.getInstance());
		move3.setDescricao("Viagem a Salvador");
		move3.setConta(conta);
		move3.setCategoria(Arrays.asList(categoria1Managed, categoria2Managed));
		
		
		em.persist(categoria1);
		em.persist(categoria2);
		em.persist(move1);
		em.persist(move2);
		move3 = em.merge(move3);
		move4 = em.merge(move4);
		em.remove(move3);
		em.remove(move4);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
}
