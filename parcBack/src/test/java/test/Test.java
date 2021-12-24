package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import util.Context;

public class Test {

	public static void main(String[] args) {
EntityManagerFactory emf = Context.getInstance().getEmf();
EntityManager em = emf.createEntityManager();

em.close();
emf.close();
	}

}
