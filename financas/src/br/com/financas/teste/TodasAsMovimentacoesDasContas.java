package br.com.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.Query;

import br.com.financas.modelo.Conta;
import br.com.financas.util.JPAUtil;

public class TodasAsMovimentacoesDasContas {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		//JOIN FETCH - Faz com que a consulta traga o dado e não tenha a necessidade de buscar novamente
		//comportamento igual com o (fetch = FetchType.EAGER)
		String jpql = "SELECT DISTINCT c FROM Conta c RIGHT JOIN FETCH c.movimentacoes";
		
		Query query = em.createQuery(jpql);
		
		List<Conta> todasAsContas = query.getResultList();
		
//		System.out.println(todasAsContas);
		//Entendendo o problema de N + 1, 
		//1 Conta e N Movimentações, consultadas dentro do laço.
		
/*		Esta consequência se chama N + 1, por realizar 1 select para buscar as contas e, 
		para cada uma delas, no laço, foi feito mais um select para buscar 
		as movimentações correspondentes.*/
		
		todasAsContas.forEach( conta -> {
			System.out.println("Titular:" + conta.getTitular());
			System.out.println("Movimentações: ");
			System.out.println(conta.getMovimentacoes());
		});
		
		//Nessa conta o resultado são três Paulo Roberto Souza porque o inner join
		//gera um Produto Cartesiano. https://pt.wikipedia.org/wiki/Produto_cartesiano 
		//Esse problema é resolvido com o DISTINCT na jpql
		
		
		//LEFT JOIN - Trás as contas mesmo que não tenham movimentações.
		//RIGHT JOIN - Trás as movimentações mesmo que não tenham contas.
		//INNER JOIN - Trás somenete as contas que contenham movimentações.
		
		em.getTransaction().commit();
		em.close();
		
	}

}
