package br.com.financas.teste;

import javax.persistence.EntityManager;

import br.com.financas.modelo.Conta;
import br.com.financas.util.JPAUtil;

public class BuscaContaTest {

	public static void main(String[] args) {
		
		EntityManager manager = new JPAUtil().getEntityManager();
		
		manager.getTransaction().begin();
		
		//Fazendo uma pesquisa pelo Id - Primary Key da Entidade
		//Sendo que tem que ser do mesmo tipo
		Conta conta = manager.find(Conta.class, 1); //Estado Managed
		
		//Depois de obter(select), fizemos uma alteração(update)
		//onde também o Hibernate fez isso diretamente no banco
		//sincronismo feito pelo JPA.
		
		//Onde o objeto conta está no estado Managed, é um
		//estado da JPA que, automaticamente os dados
		//dessa entidade estão sincronizados com o registro do 
		//banco.
//		conta.setTitular("João");
//		conta.setAgencia("456324");
		
		System.out.println(conta);
		
		manager.getTransaction().commit();
		
		manager.close();
		
	}
	
}
