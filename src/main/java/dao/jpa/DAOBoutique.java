package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import dao.IDAOBoutique;
import model.Boutique;
import util.Context;

public class DAOBoutique implements IDAOBoutique{
	@Override
	public Boutique findById(Integer id) {
		EntityManagerFactory emf = Context.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();
		Boutique objet = em.find(Boutique.class, id);
		em.close();
		return objet;
	}

	@Override
	public List<Boutique> findAll() {

		EntityManagerFactory emf = Context.getInstance().getEmf();
		EntityManager em = emf.createEntityManager();

		List<Boutique> objets = em.createQuery("from Boutique").getResultList();
		em.close();
		return objets;

	}

	@Override
	public Boutique save(Boutique objet) {
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
		Boutique objet = em.find(Boutique.class, id);

		em.getTransaction().begin();

		em.remove(objet);

		em.getTransaction().commit();
		em.close();

	}
	
	//-------------Comment recup une boutique avec ses marchandises (afficher carte)? ------------------/////
		public List<Boutique> inventaireBoissonBoutique() 
		{
			EntityManager em = Context.getInstance().getEmf().createEntityManager();
			Query q = em.createQuery("Select distinct p from Boutique p left join fetch p.marchandise");
			List<Boutique> boutique =  q.getResultList();
			em.close();
			return boutique;
		}


}
