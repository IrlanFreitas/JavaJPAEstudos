package br.com.financas.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.financas.enums.TipoMovimentacao;
import br.com.financas.modelo.Conta;
import br.com.financas.modelo.Movimentacao;
import br.com.financas.util.JPAUtil;

public class JPARelacionamentoTest {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(3);
		
		Movimentacao move = new Movimentacao();
		move.setValor(new BigDecimal(280));
		move.setTipo(TipoMovimentacao.SAÍDA);
		move.setData(Calendar.getInstance());
		move.setDescricao("Gastou");
		move.setConta(conta);
		
//		move.setValor(new BigDecimal(4000));
//		move.setTipo(TipoMovimentacao.ENTRADA);
//		move.setData(Calendar.getInstance());
//		move.setDescricao("Prêmio");
//		move.setConta(conta);
		
		//Não tive que colocar o Full Qualified Name da classe no persistence.xml
		em.persist(move);
		
		em.getTransaction().commit();
		
		em.close();
		
		
		
	}
	
}
