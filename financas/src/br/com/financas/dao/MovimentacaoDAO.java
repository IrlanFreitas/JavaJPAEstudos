package br.com.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.financas.enums.TipoMovimentacao;
import br.com.financas.modelo.Conta;
import br.com.financas.modelo.Movimentacao;
import br.com.financas.util.JPAUtil;

public class MovimentacaoDAO {

	private EntityManager em ;
	
	public MovimentacaoDAO() {
		 this.em = new JPAUtil().getEntityManager();
	}

	public void inserir(Movimentacao movimentacao) {
		em.persist(movimentacao);
	}

	public void remover(Movimentacao movimentacao) {
		movimentacao = em.merge(movimentacao);
		em.remove(movimentacao);
	}

	public Movimentacao encontrarPorId(Integer id) {
		return em.find(Movimentacao.class, id);
	}

	public List<Double> getMediasPorDiaTipo(TipoMovimentacao tipo, Conta conta) {
		
		em.getTransaction().begin(); 
		// Named Parameter, indicado pelos dois pontos antes da variável, como
		// :pConta, o p é para indicar que é um paramêtro
		String jpqlMove = " SELECT avg(m.valor) from Movimentacao m " + "WHERE m.conta = :pConta "
				+ " AND m.tipo = :pTipo " 
				+ " GROUP BY m.data ";

		//Usando TypedQuery
		TypedQuery<Double> queryMove = em.createQuery(jpqlMove, Double.class);
		
		// Colocando os valores nos campos indicados.
		queryMove.setParameter("pConta", conta);
		queryMove.setParameter("pTipo", tipo);
		
		// Aviso resolvido com o TypedQuery
		List<Double> medias = queryMove.getResultList();
		
		em.getTransaction().commit();
		em.close();

		return medias;		
	}

}
