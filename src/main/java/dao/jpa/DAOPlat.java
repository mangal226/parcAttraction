package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import dao.IDAOPlat;
import model.Plat;
import util.Context;

public class DAOPlat implements IDAOPlat{
	static EntityManagerFactory emf = Context.getInstance().getEmf();
	@Override
	public Plat findById(Integer id) {
		EntityManager em = emf.createEntityManager();
		Plat objet = em.find(Plat.class, id);
		em.close();
		return objet;
	}

	@Override
	public List<Plat> findAll() {


		EntityManager em = emf.createEntityManager();
	
		List<Plat> objets = em.createQuery("from Plat").getResultList();
		em.close();
		return objets;
		
	}

	@Override
	public Plat save(Plat objet) {
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
		Plat objet = em.find(Plat.class, id);

		em.getTransaction().begin();
		
		em.remove(objet);
		
		em.getTransaction().commit();
		em.close();

	}

}
