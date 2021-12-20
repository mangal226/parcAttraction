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

	
	static double bilanFinancier;

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

			// ajout du bilanFinancier dans une liste et réinitialisation du bilanFinancier
			// pour la journee suivante
			total.add(bilanFinancier);
			bilanFinancier = 0;

			// Réinitialisation du temps de sejour des familles pour la journee suivante
			listeFamille = familleRepo.findAll();
			for (Famille f : listeFamille) {
				familleRepo.save(f);
			}
			i++;
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

			Famille f = new Famille(nombre, tailleMin, tailleMax, dureeSejour, handicap, depenses);
			familleRepo.save(f);
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
		for (int i = 0; i < listeAttraction.size(); i++) {
			Attraction a = listeAttraction.get(i);
			// System.out.println(i+"------"+alea);
			if (i == alea) {
				List<Famille> newQueue = a.getQueue();

				newQueue.add(f);
				a.setQueue(newQueue);

				familleRepo.save(f);
				attractionRepo.save(a);
			}
		}
	}

	public void achatBoutiqueRestauration(Famille f) {

		// System.out.println("je rentre dans la boutique");
		Random r = new Random();
		int i = 1;

		List<Boisson> listeBoisson = boissonRepo.findAll();
		List<Plat> listePlat = platRepo.findAll();
		List<Marchandise> listeMarchandise = marchandiseRepo.findAll();
		// Revoir la fonction pour les achats peut être affiner
		while (i <= f.getNombre()) {
			for (Boisson b : listeBoisson) {
				int alea = r.nextInt(10) + 1;
				if (alea <= 2) {
					double depensesActuelles = f.getDepenses();
					f.setDepenses(depensesActuelles + b.getPrix());
					bilanFinancier += b.getPrix();
				}
			}
			for (Plat p : listePlat) {
				int alea = r.nextInt(10) + 1;
				if (alea <= 2) {
					double depensesActuelles = f.getDepenses();
					f.setDepenses(depensesActuelles + p.getPrix());
					bilanFinancier += p.getPrix();
				}
			}
			for (Marchandise m : listeMarchandise) {
				int alea = r.nextInt(10) + 1;
				if (alea <= 2) {
					double depensesActuelles = f.getDepenses();
					f.setDepenses(depensesActuelles + m.getPrix());
					bilanFinancier += m.getPrix();
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
			// System.out.println("je sors de la boutique et je vais dans l'attraction");
			assignementAttraction(f);
		} else {

			familleRepo.save(f);
		}

	}

	public void avancementJournee() // Continue tant que les familles ne sont pas parties
	// permet de faire diminuer le temps qu il reste aux familles

	{
		System.out.println("je suis dans avancement journée");
		for (Attraction a : attractionRepo.findAll()) {
			List<Famille> embarque = new ArrayList();
			//a = attractionRepo.findById(a.getId());
			int capaciteActuelle = a.getCapacite();
			while (a.getQueue().isEmpty() == false) {
				//a = attractionRepo.findById(a.getId());
				System.out.println("je rentre dans l'attraction : "+a);
				Famille famille = (a.getQueue()).get(0);

				if (famille.getNombre() <= capaciteActuelle)// ajout de la famille
				{
					a.getQueue().remove(0);
					attractionRepo.save(a);
					capaciteActuelle -= famille.getNombre();

					embarque.add(famille);

					int dureeSejour = famille.getDureeSejour();
					System.out.println(famille);
					System.out.println("la durée de l'attraction est de "+a.getDuree());
					dureeSejour -= a.getDuree();

					famille.setDureeSejour(dureeSejour);
					familleRepo.save(famille);

					if (famille.getDureeSejour() > 0) {
						List<Famille> listeFamille = new ArrayList();
						listeFamille.add(famille);
						System.out.println("il me reste du temps, je vais à la boutique 1");
						choixAssignation(listeFamille);
						System.out.println("Etat de Embarque"+embarque);
						System.out.println("Etat de la queue apprès choix assignation"+a.getQueue());
					} 
					else {
						familleRepo.save(famille);
					}
				}

				else if (famille.getNombre() > capaciteActuelle) {
					for (Famille f : a.getQueue()) {
						System.out.println("---------------------------------------------------");
						System.out.println("J'attends dans la queue, capacité actuelle ="+capaciteActuelle);
						System.out.println("Voici la queue :"+a.getQueue());
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
					embarque.clear();
				}

				else if (a.getQueue().isEmpty() == true && embarque.isEmpty() == false) {
					int duree = famille.getDureeSejour() - a.getDuree();
					famille.setDureeSejour(duree);
					if (famille.getDureeSejour() < 0) {
						familleRepo.save(famille);
					} else {
						List<Famille> listeFamille = new ArrayList();
						listeFamille.add(famille);
						System.out.println("il me reste du temps, je vais à la boutique 2");
						choixAssignation(listeFamille);
					}
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
