package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dao.IDAOCompte;
import model.Compte;
import util.Context;

public class DAOCompte implements IDAOCompte{

	static EntityManagerFactory emf = Context.getInstance().getEmf();

	@Override
	public Compte findById(Integer id) {
		EntityManager em = emf.createEntityManager();
		Compte objet = em.find(Compte.class, id);
		em.close();
		return objet;
	}

	@Override
	public List<Compte> findAll() {


		EntityManager em = emf.createEntityManager();

		List<Compte> objets = em.createQuery("from Compte").getResultList();
		em.close();
		return objets;

	}

	@Override
	public Compte save(Compte objet) {
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
		Compte objet = em.find(Compte.class, id);

		em.getTransaction().begin();

		em.remove(objet);

		em.getTransaction().commit();
		em.close();

	}

	@Override
	public Compte connect(String login, String password) {
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
