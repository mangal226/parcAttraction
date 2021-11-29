package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.IDAOFamille;
import model.Famille;
import util.Context;

public class DAOFamille implements IDAOFamille{

	static EntityManagerFactory emf = Context.getInstance().getEmf();
	@Override
	public Famille findById(Integer id) {
		EntityManager em = emf.createEntityManager();
		Famille objet = em.find(Famille.class, id);
		em.close();
		return objet;
	}

	@Override
	public List<Famille> findAll() {


		EntityManager em = emf.createEntityManager();
	
		List<Famille> objets = em.createQuery("from Famille").getResultList();
		em.close();
		return objets;
		
	}

	@Override
	public Famille save(Famille objet) {
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
		Famille objet = em.find(Famille.class, id);

		em.getTransaction().begin();
		
		em.remove(objet);
		
		em.getTransaction().commit();
		em.close();

	}

}
