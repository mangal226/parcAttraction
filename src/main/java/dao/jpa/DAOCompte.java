package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import dao.IDAOCompte;
import model.Compte;
import util.Context;

public class DAOCompte implements IDAOCompte{


	@Override
	public Compte findById(Integer id) {
		EntityManagerFactory emf = Context.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();
		Compte objet = em.find(Compte.class, id);
		em.close();
		return objet;
	}

	@Override
	public List<Compte> findAll() {

		EntityManagerFactory emf = Context.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();

		List<Compte> objets = em.createQuery("from Compte").getResultList();
		em.close();
		return objets;

	}

	@Override
	public Compte save(Compte objet) {
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
		Compte objet = em.find(Compte.class, id);

		em.getTransaction().begin();

		em.remove(objet);

		em.getTransaction().commit();
		em.close();

	}

	@Override
	public Compte connect(String login, String password) {
		EntityManagerFactory emf = Context.getInstance().getEmf();
		Compte connected = null;
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("from Compte c where c.login=:log and c.password=:pass");
		query.setParameter("log", login);
		query.setParameter("pass", password);

		try 
		{
			connected=(Compte) query.getSingleResult();
		}
		catch(Exception e) {e.printStackTrace();}
		em.close();
		return connected;

	}

}
