package br.com.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.financas.modelo.Conta;
import br.com.financas.modelo.Movimentacao;
import br.com.financas.util.JPAUtil;

public class InserirMovimentacaoTest {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		
//		Resolvendo o problema do LazyInitializationException
//		Conta conta = em.find(Conta.class, 2); 
//		List<Movimentacao> movimentacoes = conta.getMovimentacoes();

		String jpql = "SELECT c FROM Conta c JOIN FETCH c.movimentacoes WHERE c.id = :pId";
		
		Query query = em.createQuery(jpql);
		query.setParameter("pId", 2);
				
		Conta conta =  (Conta) query.getSingleResult();
		
		List<Movimentacao> movimentacoes = conta.getMovimentacoes();
		 
		em.close();

		for (Movimentacao movimentacao : movimentacoes) {
			System.out.println("Movimentação: " + movimentacao.getDescricao());
		}
		
/*		LazyInitializationException ocorre quando não há conexão aberta com o banco de dados no momento 
		da execução de um código lazy. As soluções seriam deixá-la aberta 
		um pouco mais ou trazer os relacionamentos de forma Eager.*/
		
	}
}
