package service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Attraction;
import model.Boisson;
import model.Boutique;
import model.Compte;
import model.Famille;
import model.Marchandise;
import model.Plat;
import model.Restauration;
import repository.AttractionRepository;
import repository.BoissonRepository;
import repository.BoutiqueRepository;
import repository.CompteRepository;
import repository.FamilleRepository;
import repository.MarchandiseRepository;
import repository.PlatRepository;
import repository.RestaurationRepository;

@Service
public class SimulationService {

	static double bilanFinancier = 0;

	static int nbrVisiteurTotal = 0;

	@Autowired
	private FamilleRepository familleRepo;
	@Autowired
	private AttractionRepository attractionRepo;
	@Autowired
	private BoutiqueRepository boutiqueRepo;
	@Autowired
	private CompteRepository compteRepo;
	@Autowired
	private MarchandiseRepository marchandiseRepo;
	@Autowired
	private PlatRepository platRepo;
	@Autowired
	private RestaurationRepository restaurationRepo;
	@Autowired
	private BoissonRepository boissonRepo;

	static List<Famille> famille = new ArrayList();
	static List<Boisson> boisson = new ArrayList();
	static List<Plat> plat = new ArrayList();
	static List<Attraction> attraction = new ArrayList();
	static List<Boutique> boutique = new ArrayList();
	static List<Restauration> restauration = new ArrayList();
	static Compte connected;
	static LinkedList<Famille> fileAttente = new LinkedList();
	/* static LinkedList<Famille> fileAttenteFP = new LinkedList(); */

	static List<Marchandise> marchandise = new ArrayList();
	static boolean fermeture = true;

	public void simulation(int nbJour, int nbFamille) {

		
		LinkedList<Double> total = new LinkedList();

		int i = 1;
		while (i <= nbJour) {
			creationFamille(nbFamille);
			List<Famille> listeFamille = familleRepo.findAll();
			choixAssignation(listeFamille); // Boutique ou attraction ?
			avancementJournee();
			// ajout du bilanFinancier dans une liste et r?initialisation du bilanFinancier
			// pour la journee suivante
			total.add(bilanFinancier);
			bilanFinancier = 0;
			

			i++;
		}
		System.out.println("-----------------------------------------");
		System.out.println("Nombre total de visiteurs : " + nbrVisiteurTotal);
		System.out.println("-----------------------------------------");
		System.out.println("Bilan financier total : "+total);
		System.out.println("-----------------------------------------");
		System.out.println("Voici l'?tat des stocks :");
		System.out.println("-----------------------------------------");
		System.out.println("Boissons :");
		for (Boisson b : boissonRepo.findAll()) {
			System.out.println("Nombre de "+b.getNom()+" vendus : "+b.getVente()+", pour "+b.getPrix()*b.getVente()+"? en tout. Il en reste : " + b.getStock() + " en stock.");
		}
		System.out.println("Plats :");
		for (Plat b : platRepo.findAll()) {
			System.out.println("Nombre de "+b.getNom()+" vendus : "+b.getVente()+", pour "+b.getPrix()*b.getVente()+"? en tout. Il en reste : " + b.getStock() + " en stock.");
		}
		System.out.println("Marchandises :");
		for (Marchandise b : marchandiseRepo.findAll()) {
			System.out.println("Nombre de "+b.getNom()+" vendus : "+b.getVente()+", pour "+b.getPrix()*b.getVente()+"? en tout. Il en reste : " + b.getStock() + " en stock.");
		}
		System.out.println("-----------------------------------------");
		System.out.println("Bilan visites Attractions :");
		System.out.println("-----------------------------------------");
		for (Attraction a : attractionRepo.findAll()) {
			System.out.println(a.getNom()+" a eu "+a.getNbrVisiteur()+" visiteurs");
		}
	}

	public void creationFamille(int nbFamille) {
		List<Famille> familles = new ArrayList();
		Random r = new Random();
		for (int i = 0; i < nbFamille; i++) {
			double depenses = 0;
			int tailleMin, tailleMax, nombre, dureeSejour = 40;
			Boolean handicap;
			nombre = r.nextInt(10) + 1;
			tailleMin = r.nextInt(140 - 120 + 1) + 120;
			tailleMax = r.nextInt(190 - 165 + 1) + 165;
			handicap = r.nextBoolean();
			depenses = 30 * nombre;

			Famille f = new Famille(nombre, tailleMin, tailleMax, dureeSejour, handicap, depenses);
			familleRepo.save(f);

			nbrVisiteurTotal += nombre;
		}
	}

	public void choixAssignation(List<Famille> listeFamille) {

		// System.out.println("voici la liste des familles dans le parc"+listeFamille);
		Random r = new Random();
		for (Famille f : listeFamille) {

			int alea = r.nextInt(10);
			if (alea <= 6) {// 70% de chance de rentrer dans une attraction

				assignementAttraction(f);
			} else {
				achatBoutiqueRestauration(f);
			}
		}
	}

	public void assignementAttraction(Famille f) // choisir l'attraction de la famille
	{
		Random r = new Random();
		List<Attraction> listeAttraction = attractionRepo.findAll();
		int alea = r.nextInt(listeAttraction.size());
		Attraction a = listeAttraction.get(alea);
		// System.out.println(i+"------"+alea);
		List<Famille> newQueue = a.getQueue();

		if (f.getTailleMin() > a.getTailleMin() && f.getTailleMax() < a.getTailleMax()) {
			newQueue.add(f);
			a.setQueue(newQueue);

			familleRepo.save(f);
			attractionRepo.save(a);
		} else {
			assignementAttraction(f);
		}
	}

	public void achatBoutiqueRestauration(Famille f) {

		// System.out.println("je rentre dans la boutique");
		Random r = new Random();
		int i = 1;

		List<Boisson> listeBoisson = boissonRepo.findAll();
		List<Plat> listePlat = platRepo.findAll();
		List<Marchandise> listeMarchandise = marchandiseRepo.findAll();
		// Revoir la fonction pour les achats peut ?tre affiner
		while (i <= f.getNombre()) {
			for (Boisson b : listeBoisson) {
				int alea = r.nextInt(10) + 1;
				if (alea <= 2) {
					double depensesActuelles = f.getDepenses();
					f.setDepenses(depensesActuelles + b.getPrix());
					bilanFinancier += b.getPrix();
					b.setStock(b.getStock() - 1);
					b.setVente(b.getVente()+1);
					boissonRepo.save(b);
				}
			}
			for (Plat p : listePlat) {
				int alea = r.nextInt(10) + 1;
				if (alea <= 2) {
					double depensesActuelles = f.getDepenses();
					f.setDepenses(depensesActuelles + p.getPrix());
					bilanFinancier += p.getPrix();
					p.setStock(p.getStock() - 1);
					p.setVente(p.getVente()+1);
					platRepo.save(p);
				}
			}
			for (Marchandise m : listeMarchandise) {
				int alea = r.nextInt(10) + 1;
				if (alea <= 2) {
					double depensesActuelles = f.getDepenses();
					f.setDepenses(depensesActuelles + m.getPrix());
					bilanFinancier += m.getPrix();
					m.setStock(m.getStock() - 1);
					m.setVente(m.getVente()+1);
					marchandiseRepo.save(m);
				}
			}
			i++;
		}
		int dureSejour = f.getDureeSejour();
		dureSejour -= 1;
		f.setDureeSejour(dureSejour);
		// System.out.println(f);
		familleRepo.save(f);
		if (f.getDureeSejour() > 0) {
			System.out.println("je sors de la boutique et je vais dans l'attraction");
			System.out.println("je suis la famille " + f.getId() + " et j'ai " + f.getDepenses() + " d?penses");
			assignementAttraction(f);
		} else {

			familleRepo.save(f);
		}

	}

	public void avancementJournee() // Continue tant que les familles ne sont pas parties
	// permet de faire diminuer le temps qu il reste aux familles

	{
		System.out.println("je suis dans avancement journ?e");
		for (Attraction a : attractionRepo.findAll()) {
			List<Famille> embarque = new ArrayList();
			a = attractionRepo.getById(a.getId());
			int capaciteActuelle = a.getCapacite();
			while (a.getQueue().isEmpty() == false) {
				a = attractionRepo.getById(a.getId());
				System.out.println("je rentre dans l'attraction : " + a);
				Famille famille = (a.getQueue()).get(0);

				if (famille.getNombre() <= capaciteActuelle)// ajout de la famille
				{
					a.getQueue().remove(0);
					a.setNbrVisiteur(a.getNbrVisiteur() + famille.getNombre());
					attractionRepo.save(a);
					capaciteActuelle -= famille.getNombre();

					embarque.add(famille);

					int dureeSejour = famille.getDureeSejour();
					System.out.println(famille);
					System.out.println("la dur?e de l'attraction est de " + a.getDuree());
					dureeSejour -= a.getDuree();

					famille.setDureeSejour(dureeSejour);
					familleRepo.save(famille);
				}

				else if (famille.getNombre() > capaciteActuelle) {
					for (Famille f : a.getQueue()) {
						System.out.println("---------------------------------------------------");
						System.out.println("J'attends dans la queue, capacit? actuelle =" + capaciteActuelle);
						System.out.println("Voici la queue :" + a.getQueue());
						System.out.println("---------------------------------------------------");
						int dureeSejour = f.getDureeSejour();
						dureeSejour -= a.getDuree();
						f.setDureeSejour(dureeSejour);
						capaciteActuelle = a.getCapacite();

					}

					familleRepo.save(famille);
					if (famille.getDureeSejour() < 0) {
						familleRepo.save(famille);
						a.getQueue().remove(famille);
						attractionRepo.save(a);
					}

					for (Famille familleEmbarque : embarque) {
						if (familleEmbarque.getDureeSejour() > 0) {
							List<Famille> listeFamille = new ArrayList();
							listeFamille.add(familleEmbarque);
							System.out.println("il me reste du temps, je vais ? la boutique 1");
							choixAssignation(listeFamille);
						} else {
							familleRepo.save(familleEmbarque);
						}
					}
					embarque.clear();
				}

				if (a.getQueue().isEmpty() == true && embarque.isEmpty() == false) {
					for (Famille familleEmbarque : embarque) {
						if (familleEmbarque.getDureeSejour() > 0) {
							List<Famille> listeFamille = new ArrayList();
							listeFamille.add(familleEmbarque);
							System.out.println("il me reste du temps, je vais ? la boutique 2");
							choixAssignation(listeFamille);
						} else {
							familleRepo.save(familleEmbarque);
						}
					}
					capaciteActuelle = a.getCapacite();
					embarque.clear();
				}
			}
		}
		for (Attraction b : attractionRepo.findAll()) {
			if (b.getQueue().isEmpty() == false) {
				avancementJournee();
			}
		}

	}

}
