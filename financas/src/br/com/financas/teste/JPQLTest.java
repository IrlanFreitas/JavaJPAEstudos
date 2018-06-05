package br.com.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.financas.enums.TipoMovimentacao;
import br.com.financas.modelo.Conta;
import br.com.financas.modelo.Movimentacao;
import br.com.financas.util.JPAUtil;

public class JPQLTest {
	
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
//		String jpqlConta = "SELECT c FROM Conta c WHERE c.id = 2" ;
//		Query queryConta = em.createQuery(jpqlConta);
//		queryConta.getResultList().stream().forEach(System.out::println);
		
		
		Conta conta = new Conta();
		conta.setId(2);
		
//		Named Parameter, indicado pelos dois pontos antes da variável, como 
//		:pConta, o p é para indicar que é um paramêtro
		String jpqlMove = " SELECT m from Movimentacao m WHERE m.conta = :pConta "
				+ " AND m.tipo = :pTipo "
				+ " ORDER BY m.valor DESC" ;
		Query queryMove = em.createQuery(jpqlMove);
		//Colocando os valores nos campos indicados.
		queryMove.setParameter("pConta", conta);
		queryMove.setParameter("pTipo", TipoMovimentacao.ENTRADA);
		
		@SuppressWarnings("unchecked")
		List<Movimentacao> list = queryMove.getResultList();
		list.stream().forEach(System.out::println);
		
		em.getTransaction().commit();
		em.close();
		
		
	}

}
