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
		
		//JOIN FETCH - Faz com que a consulta traga o dado e n�o tenha a necessidade de buscar novamente
		//comportamento igual com o (fetch = FetchType.EAGER)
		String jpql = "SELECT DISTINCT c FROM Conta c RIGHT JOIN FETCH c.movimentacoes";
		
		Query query = em.createQuery(jpql);
		
		List<Conta> todasAsContas = query.getResultList();
		
//		System.out.println(todasAsContas);
		//Entendendo o problema de N + 1, 
		//1 Conta e N Movimenta��es, consultadas dentro do la�o.
		
/*		Esta consequ�ncia se chama N + 1, por realizar 1 select para buscar as contas e, 
		para cada uma delas, no la�o, foi feito mais um select para buscar 
		as movimenta��es correspondentes.*/
		
		todasAsContas.forEach( conta -> {
			System.out.println("Titular:" + conta.getTitular());
			System.out.println("Movimenta��es: ");
			System.out.println(conta.getMovimentacoes());
		});
		
		//Nessa conta o resultado s�o tr�s Paulo Roberto Souza porque o inner join
		//gera um Produto Cartesiano. https://pt.wikipedia.org/wiki/Produto_cartesiano 
		//Esse problema � resolvido com o DISTINCT na jpql
		
		
		//LEFT JOIN - Tr�s as contas mesmo que n�o tenham movimenta��es.
		//RIGHT JOIN - Tr�s as movimenta��es mesmo que n�o tenham contas.
		//INNER JOIN - Tr�s somenete as contas que contenham movimenta��es.
		
		em.getTransaction().commit();
		em.close();
		
	}

}
