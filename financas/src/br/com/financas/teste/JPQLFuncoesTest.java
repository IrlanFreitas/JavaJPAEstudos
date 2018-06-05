package br.com.financas.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.financas.dao.MovimentacaoDAO;
import br.com.financas.enums.TipoMovimentacao;
import br.com.financas.modelo.Conta;
import br.com.financas.util.JPAUtil;

public class JPQLFuncoesTest {

	public static void main(String[] args) {
		mediaByDay();
	}

	private static void sum() {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		// String jpqlConta = "SELECT c FROM Conta c WHERE c.id = 2" ;
		// Query queryConta = em.createQuery(jpqlConta);
		// queryConta.getResultList().stream().forEach(System.out::println);

		Conta conta = new Conta();
		conta.setId(2);

		// Named Parameter, indicado pelos dois pontos antes da vari�vel, como
		// :pConta, o p � para indicar que � um param�tro
		String jpqlMove = " SELECT sum(m.valor) from Movimentacao m WHERE m.conta = :pConta " + " AND m.tipo = :pTipo "
				+ " ORDER BY m.valor DESC";
		Query queryMove = em.createQuery(jpqlMove);
		// Colocando os valores nos campos indicados.
		queryMove.setParameter("pConta", conta);
		queryMove.setParameter("pTipo", TipoMovimentacao.SA�DA);

		BigDecimal valor = (BigDecimal) queryMove.getSingleResult();
		System.out.println("Soma dos valores �: " + valor);

		em.getTransaction().commit();
		em.close();
	}

	private static void media() {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		// String jpqlConta = "SELECT c FROM Conta c WHERE c.id = 2" ;
		// Query queryConta = em.createQuery(jpqlConta);
		// queryConta.getResultList().stream().forEach(System.out::println);

		Conta conta = new Conta();
		conta.setId(2);

		// Named Parameter, indicado pelos dois pontos antes da vari�vel, como
		// :pConta, o p � para indicar que � um param�tro
		String jpqlMove = " SELECT avg(m.valor) from Movimentacao m WHERE m.conta = :pConta " + " AND m.tipo = :pTipo "
				+ " ORDER BY m.valor DESC";
		Query queryMove = em.createQuery(jpqlMove);
		// Colocando os valores nos campos indicados.
		queryMove.setParameter("pConta", conta);
		queryMove.setParameter("pTipo", TipoMovimentacao.SA�DA);

		Double valor = (Double) queryMove.getSingleResult();
		System.out.println("M�dia �: " + valor);

		em.getTransaction().commit();
		em.close();
	}

	private static void max() {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		// String jpqlConta = "SELECT c FROM Conta c WHERE c.id = 2" ;
		// Query queryConta = em.createQuery(jpqlConta);
		// queryConta.getResultList().stream().forEach(System.out::println);

		Conta conta = new Conta();
		conta.setId(2);

		// Named Parameter, indicado pelos dois pontos antes da vari�vel, como
		// :pConta, o p � para indicar que � um param�tro
		String jpqlMove = " SELECT max(m.valor) from Movimentacao m WHERE m.conta = :pConta " + " AND m.tipo = :pTipo "
				+ " ORDER BY m.valor DESC";
		Query queryMove = em.createQuery(jpqlMove);
		// Colocando os valores nos campos indicados.
		queryMove.setParameter("pConta", conta);
		queryMove.setParameter("pTipo", TipoMovimentacao.SA�DA);

		BigDecimal valor = (BigDecimal) queryMove.getSingleResult();
		System.out.println("O maior valor �: " + valor);

		em.getTransaction().commit();
		em.close();
	}

	private static void count() {
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
//		String jpqlConta = "SELECT c FROM Conta c WHERE c.id = 2" ;
//		Query queryConta = em.createQuery(jpqlConta);
//		queryConta.getResultList().stream().forEach(System.out::println);
		
		
		Conta conta = new Conta();
		conta.setId(2);
		
//		Named Parameter, indicado pelos dois pontos antes da vari�vel, como 
//		:pConta, o p � para indicar que � um param�tro
		String jpqlMove = " SELECT count(m) from Movimentacao m WHERE m.conta = :pConta "
				+ " AND m.tipo = :pTipo "
				+ " ORDER BY m.valor DESC" ;
		Query queryMove = em.createQuery(jpqlMove);
		//Colocando os valores nos campos indicados.
		queryMove.setParameter("pConta", conta);
		queryMove.setParameter("pTipo", TipoMovimentacao.SA�DA);
		
		Long valor = (Long)  queryMove.getSingleResult();
		System.out.println("Total de movimenta��es: "+ valor);
		
		em.getTransaction().commit();
		em.close();
	}
	

	private static void mediaByDay() {
		
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		// String jpqlConta = "SELECT c FROM Conta c WHERE c.id = 2" ;
		// Query queryConta = em.createQuery(jpqlConta);
		// queryConta.getResultList().stream().forEach(System.out::println);

		Conta conta = new Conta();
		conta.setId(2);
		
		em.getTransaction().begin();
		//Chama a Query criada por annotation acima da classe Movimenta��o
		TypedQuery<Double> namedQuery = em.createNamedQuery("MediasPorDiaETipo", Double.class);
		
		namedQuery.setParameter("pConta", conta);
		namedQuery.setParameter("pTipo", TipoMovimentacao.SA�DA);
		
		List<Double> medias = namedQuery.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		System.out.println("---------------------------------------");
		medias.stream().forEach(System.out::println);
		System.out.println("---------------------------------------");

	}
	
}
