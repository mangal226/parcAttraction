package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import dao.IDAORestauration;
import model.Restauration;
import util.Context;

public class DAORestauration implements IDAORestauration{
	@Override
	public Restauration findById(Integer id) {
		EntityManagerFactory emf = Context.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();
		Restauration objet = em.find(Restauration.class, id);
		em.close();
		return objet;
	}

	@Override
	public List<Restauration> findAll() {

		EntityManagerFactory emf = Context.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();

		List<Restauration> objets = em.createQuery("from resturation").getResultList();
		em.close();
		return objets;

	}

	@Override
	public Restauration save(Restauration objet) {
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
		Restauration objet = em.find(Restauration.class, id);

		em.getTransaction().begin();

		em.remove(objet);

		em.getTransaction().commit();
		em.close();

	}
}
