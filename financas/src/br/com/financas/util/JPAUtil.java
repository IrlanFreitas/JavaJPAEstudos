package br.com.financas.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	
	//Classe Persistence representa o arquivo Persistence.xml
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("financas-mysql");
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
}
