package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Boisson;
import model.Plat;
import model.Boutique;
import model.Restauration;
import util.Context;

public class TestLazy {




	/*-------------Comment recup un resto avec ses boissons ? ------------------/////
	public static List<Boisson> inventaireBoisson() 
	{
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query q = em.createQuery("Select distinct p from Boisson p left join fetch p.boutique");
		List<Boisson> boisson =  q.getResultList();
		em.close();
		return boisson;
	}*/
	
	//-------------Comment recup une boutique avec ses marchandises ? ------------------/////
	public static List<Boutique> inventaireBoissonBoutique() 
	{
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query q = em.createQuery("Select distinct p from Boutique p left join fetch p.marchandise");
		List<Boutique> boutique =  q.getResultList();
		em.close();
		return boutique;
	}


	//-------------Comment recup un resto avec ses boissons + ses plats ? ------------------/////
		public static List<Restauration> inventaireGeneral() 
		{
			EntityManager em = Context.getInstance().getEmf().createEntityManager();
			Query q = em.createQuery("Select distinct p from Restauration b left join fetch p.boisson join fetch p.plat");
			List<Restauration> restauration =  q.getResultList();
			em.close();
			List<Boisson> boisson;
			return boisson;
		}
}

	/*public static List<Personnage> fullJoinWorking() 
	{
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query q = em.createQuery("Select distinct p from Personnage p left join fetch p.inventaire");
		List<Personnage> persos =  q.getResultList();
		q = em.createQuery("Select distinct p from Personnage p left join fetch p.quetes");
		persos =  q.getResultList();

		em.close();
		return persos;
	} */


	