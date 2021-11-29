package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.IDAOMarchandise;
import model.Marchandise;
import util.Context;

public class DAOMarchandise implements IDAOMarchandise{

	static EntityManagerFactory emf = Context.getInstance().getEmf();
	@Override
	public Marchandise findById(Integer id) {
		EntityManager em = emf.createEntityManager();
		Marchandise objet = em.find(Marchandise.class, id);
		em.close();
		return objet;
	}

	@Override
	public List<Marchandise> findAll() {


		EntityManager em = emf.createEntityManager();
	
		List<Marchandise> objets = em.createQuery("from Marchandise").getResultList();
		em.close();
		return objets;
		
	}

	@Override
	public Marchandise save(Marchandise objet) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		objet=em.merge(objet);

		em.getTransaction().commit();
		em.close();

		return objet;
	}

	@Override
	public void delete(Integer id) {
		EntityManager em = emf.createEntityManager();
		Marchandise objet = em.find(Marchandise.class, id);

		em.getTransaction().begin();
		
		em.remove(objet);
		
		em.getTransaction().commit();
		em.close();

	}

}

