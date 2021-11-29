package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import dao.IDAOAttraction;
import model.Attraction;
import util.Context;

public class DAOAttraction implements IDAOAttraction{ 
	static EntityManagerFactory emf = Context.getInstance().getEmf();
	@Override
	public Attraction findById(Integer id) {
		EntityManagerFactory emf = Context.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();
		Attraction objet = em.find(Attraction.class, id);
		em.close();
		return objet;
	}

	@Override
	public List<Attraction> findAll() {

		EntityManagerFactory emf = Context.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();

		List<Attraction> objets = em.createQuery("from Attraction").getResultList();
		em.close();
		return objets;

	}

	@Override
	public Attraction save(Attraction objet) {
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
		Attraction objet = em.find(Attraction.class, id);

		em.getTransaction().begin();

		em.remove(objet);

		em.getTransaction().commit();
		em.close();

	}
}
