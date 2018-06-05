package br.com.financas.teste;

import javax.persistence.EntityManager;

import br.com.financas.modelo.Conta;
import br.com.financas.util.JPAUtil;

public class RemoveContaTest {
	
	public static void main(String[] args) {
		
		Conta conta = new Conta(); //Estado Trasient
		conta.setId(1);
		conta.setTitular("Danilo");
		conta.setAgencia("123");
		conta.setBanco("Banco do Brasil");
		conta.setNumero("456");
		
/*		EntityManager em = Persistence.createEntityManagerFactory("financas-mysql").createEntityManager();
		EntityManager em2 = Persistence.createEntityManagerFactory("financas-postgres").createEntityManager();*/
		

//		Um item muito importante da JPA � a interface EntityManager, onde por meio dela 
//		conseguimos abstrair o mundo relacional e focar apenas em objetos. Para conseguir 
//		uma inst�ncia de EntityManager precisamos configurar propriedades no arquivo persistence.xml 
//		e obter a inst�ncia atrav�s da classe Persistence, como mostrado no c�digo abaixo:
		EntityManager em = new JPAUtil().getEntityManager();
		
		//Abrir uma transa��o 
		em.getTransaction().begin();
		
		//Necess�rio obter essa conta pois, sem estar no estado Managed
		//n�o h� como a JPA gerenciar a inst�ncia
		conta = em.find(Conta.class, 1); //Transformar uma instancia que est� no Estado Removed e colocar no Estado Managed
		em.remove(conta); 
		
		//Modifica��o por conta da sincroniza��o autom�tica
		conta.setBanco("Bradesco");
		
		//Comitar
		em.getTransaction().commit();
		
		//Fechar as conex�es
		em.close(); //Conta deixou de ser Managed e foi para Detached
		
	}
}
