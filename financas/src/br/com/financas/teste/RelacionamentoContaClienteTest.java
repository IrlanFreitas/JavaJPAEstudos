package br.com.financas.teste;

import javax.persistence.EntityManager;

import br.com.financas.modelo.Cliente;
import br.com.financas.util.JPAUtil;

public class RelacionamentoContaClienteTest {
	
	/*Tentando associar a mesma conta para cliente iguais sendo que
	já foi "configurado" que seria o relacionamento de 1:1 e
	único
	Depois de resetar o banco, deletar e criar novamente, o comando
	JoinColumn( unique = true ), passou a funcionar.
	*/
	
	public static void main(String[] args) {
		
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
//		Conta conta = em.find(Conta.class, 3);
		Cliente cliente1 = em.find(Cliente.class, 1);
		
//		Cliente cliente2 = new Cliente("Gandalf", "Mago Supremo", "Mordor");
//		cliente2.setConta(conta);
//		em.persist(cliente2);
		
//		Cliente cliente1 = new Cliente("Irlan Freitas", "Desenvolvedor", "Alto do Cabrito");
//		cliente1.setConta(conta);
		
		em.remove(cliente1);
		
		em.getTransaction().commit();
		
		em.close();
		
		
	}
	
}
