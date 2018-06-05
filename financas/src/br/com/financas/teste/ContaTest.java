package br.com.financas.teste;

import javax.persistence.EntityManager;

import br.com.financas.modelo.Conta;
import br.com.financas.util.JPAUtil;

public class ContaTest {
	
	public static void main(String[] args) {
		
		Conta conta = new Conta(); //Estado Trasient
		conta.setTitular("Danilo");
		conta.setAgencia("123");
		conta.setBanco("Banco do Brasil");
		conta.setNumero("456");
		
/*		EntityManager em = Persistence.createEntityManagerFactory("financas-mysql").createEntityManager();
		EntityManager em2 = Persistence.createEntityManagerFactory("financas-postgres").createEntityManager();*/
		

//		Um item muito importante da JPA é a interface EntityManager, onde por meio dela 
//		conseguimos abstrair o mundo relacional e focar apenas em objetos. Para conseguir 
//		uma instância de EntityManager precisamos configurar propriedades no arquivo persistence.xml 
//		e obter a instância através da classe Persistence, como mostrado no código abaixo:
		EntityManager em = new JPAUtil().getEntityManager();
		
		//Abrir uma transação 
		em.getTransaction().begin();
		
		//Persistir/Inserir o dado
		em.persist(conta); //Transformar uma instancia que está no Estado Transient e colocar no Estado Managed
		
		//Modificação por conta da sincronização automática
		conta.setBanco("Bradesco");
		
		//Comitar
		em.getTransaction().commit();
		
		//Fechar as conexões
		em.close(); //Conta deixou de ser Managed e foi para Detached
		
		EntityManager em2 = new JPAUtil().getEntityManager();
		em2.getTransaction().begin();
		
		conta.setTitular("Leonardo"); //Estado Detached, pois o EntityManaged que buscou a entidade foi fechado
		em2.merge(conta); //Serve para fazer updates das entidade e tirar do Estado Detached para o Estado Managed
		
		em2.getTransaction().commit();
		
		em2.close();
		
	}
}
