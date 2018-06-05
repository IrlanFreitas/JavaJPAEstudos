package br.com.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.financas.modelo.Categoria;
import br.com.financas.modelo.Movimentacao;
import br.com.financas.util.JPAUtil;

public class MovimentacaoPorCategoriaTest {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Categoria categoria = new Categoria();
		categoria.setId(1);
		
		String jpql = "SELECT m FROM Movimentacao m JOIN m.categoria c "
				+ " where c = :pCategoria " ;
		
		Query query = em.createQuery(jpql);
		query.setParameter("pCategoria", categoria);
		
		@SuppressWarnings("unchecked")
		List<Movimentacao> resultList = query.getResultList();
		//Não funfa, só se dentro o toString tivesse um sysout
//		resultList.forEach(Movimentacao::toString);
		
		em.getTransaction().commit();
		em.close();
		
		for (Movimentacao result : resultList) {
			System.out.println(result.getDescricao());
			System.out.println(result.getCategoria().toString());
		}

	}

}
