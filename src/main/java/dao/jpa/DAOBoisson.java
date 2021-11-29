package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import dao.IDAOBoisson;
import model.Boisson;
import util.Context;

public class DAOBoisson implements IDAOBoisson{

	@Override
	public Boisson findById(Integer id) {
		EntityManagerFactory emf = Context.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();
		Boisson objet = em.find(Boisson.class, id);
		em.close();
		return objet;
	}

	@Override
	public List<Boisson> findAll() {
		EntityManagerFactory emf = Context.getInstance().getEmf();


		EntityManager em = emf.createEntityManager();

		List<Boisson> objets = em.createQuery("from Boisson").getResultList();
		em.close();
		return objets;

	}

	@Override
	public Boisson save(Boisson objet) {
		EntityManagerFactory emf = Context.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		objet=em.merge(objet);

		em.getTransaction().commit();
		em.close();

		return objet;
	}

	@Override
	public void delete(Integer id) {
		EntityManagerFactory emf = Context.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();
		Boisson objet = em.find(Boisson.class, id);

		em.getTransaction().begin();

		em.remove(objet);

		em.getTransaction().commit();
		em.close();

	}


}
