package SopraAjc.ParcAttractionSpring.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.hibernate.query.criteria.internal.expression.function.SqrtFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SopraAjc.ParcAttractionSpring.exception.SimulationException;
import SopraAjc.ParcAttractionSpring.model.Attraction;
import SopraAjc.ParcAttractionSpring.model.Boisson;
import SopraAjc.ParcAttractionSpring.model.Boutique;
import SopraAjc.ParcAttractionSpring.model.Compte;
import SopraAjc.ParcAttractionSpring.model.Coordonnees;
import SopraAjc.ParcAttractionSpring.model.Famille;
import SopraAjc.ParcAttractionSpring.model.Marchandise;
import SopraAjc.ParcAttractionSpring.model.Plat;
import SopraAjc.ParcAttractionSpring.model.Restauration;
import SopraAjc.ParcAttractionSpring.model.Simulation;
import SopraAjc.ParcAttractionSpring.repository.AttractionRepository;
import SopraAjc.ParcAttractionSpring.repository.BoissonRepository;
import SopraAjc.ParcAttractionSpring.repository.BoutiqueRepository;
import SopraAjc.ParcAttractionSpring.repository.CompteRepository;
import SopraAjc.ParcAttractionSpring.repository.FamilleRepository;
import SopraAjc.ParcAttractionSpring.repository.MarchandiseRepository;
import SopraAjc.ParcAttractionSpring.repository.PlatRepository;
import SopraAjc.ParcAttractionSpring.repository.RestaurationRepository;
import SopraAjc.ParcAttractionSpring.repository.SimulationRepository;

@Service
public class SimulationService {

	// Ajout services
	@Autowired
	private SimulationRepository simulationRepo;

	public void creationSimulation(Simulation simulation) {
		if (simulation.getId() == null) {
			throw new SimulationException();
		}
		simulationRepo.save(simulation);
	}
	
//	public void update(Simulation simulation) {
//		if (simulation.getId() == null) {
//			throw new SimulationException();
//		}
//		Simulation simulationEnBase = getById(simulation.getId());
//		personnage.setVersion(personnageEnBase.getVersion());
//		
//		personnageRepo.save(personnage);
//	}
	
	public void suppression(Long id) {
		// traitement sur le compagnon
		// delete
		// null maitre
		Simulation simulationEnBase = simulationRepo.findById(id).orElseThrow(SimulationException::new);
		simulationRepo.delete(simulationEnBase);
	}
	
	public void suppression(Simulation simulation) {
		// traitement sur le compagnon
		// delete
		// null maitre
		Simulation simulationEnBase = simulationRepo.findById(simulation.getId()).orElseThrow(SimulationException::new);
		simulationRepo.delete(simulationEnBase);
	}
	
	public List<Simulation> getAll(){
		return simulationRepo.findAll();
	}
	
	public Simulation getById(Long id) {
		if (id !=null) {
			return simulationRepo.findById(id).orElseThrow(SimulationException::new);
		}
		throw new SimulationException();
	}
	
		
	// Simulation du parc
	
	
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
		//LinkedList<Double> nbVisiteurs = new LinkedList();
		//LinkedList<Double> bilanVisiteurs = new LinkedList(); // bilan par visiteur
		
		int i = 1;
		while (i <= nbJour) {
			creationFamille(nbFamille);
			List<Famille> listeFamille = familleRepo.findAll();
			choixAssignation(listeFamille); // Boutique ou attraction ?
			avancementJournee();
			// ajout du bilanFinancier dans une liste et r�initialisation du bilanFinancier
			// pour la journee suivante
			//for (Attraction a : attractionRepo.findAll(){
			//	nbVisiteurs+=a.getNbrVisiteur();
			//}
			//nbVisiteurs.add(nbVisiteur);
			total.add(bilanFinancier);
			//double bilanVisiteur = bilanFinancier/nbVisiteur;
			//bilanVisiteurs.add(bilanVisiteur);
			
			//bilanVisiteur=0;
			//nbVisiteur=0;
			bilanFinancier = 0;
			i++;
		}
		
		//Double [] bilanVisiteurs_tableau = bilanVisiteurs.toArray(new Double[bilanVisiteurs.size()]);
		Double [] total_tableau = total.toArray(new Double[total.size()]);
		//Double [] nbVisiteurs_tableau = nbVisiteurs.toArray(new Double[nbVisiteurs.size()]);
		
		//System.out.println(bilanVisiteurs_tableau);
		System.out.println(total_tableau);
		//System.out.println(nbVisiteurs_tableau);
		
		System.out.println("-----------------------------------------");
		System.out.println("Nombre total de visiteurs : " + nbrVisiteurTotal);
		System.out.println("-----------------------------------------");
		System.out.println("Bilan financier total : " + total);
		System.out.println("-----------------------------------------");
		System.out.println("Voici l'�tat des stocks :");
		System.out.println("-----------------------------------------");
		System.out.println("Boissons :");
		for (Boisson b : boissonRepo.findAll()) {
			System.out.println("Nombre de " + b.getNom() + " vendus : " + b.getVente() + ", pour "
					+ b.getPrix() * b.getVente() + "� en tout. Il en reste : " + b.getStock() + " en stock.");
		}
		System.out.println("Plats :");
		for (Plat b : platRepo.findAll()) {
			System.out.println("Nombre de " + b.getNom() + " vendus : " + b.getVente() + ", pour "
					+ b.getPrix() * b.getVente() + "� en tout. Il en reste : " + b.getStock() + " en stock.");
		}
		System.out.println("Marchandises :");
		for (Marchandise b : marchandiseRepo.findAll()) {
			System.out.println("Nombre de " + b.getNom() + " vendus : " + b.getVente() + ", pour "
					+ b.getPrix() * b.getVente() + "� en tout. Il en reste : " + b.getStock() + " en stock.");
		}
		System.out.println("-----------------------------------------");
		System.out.println("Bilan visites Attractions :");
		System.out.println("-----------------------------------------");
		for (Attraction a : attractionRepo.findAll()) {
			System.out.println(a.getNom() + " a eu " + a.getNbrVisiteur() + " visiteurs");
		}
	}

	public void creationFamille(int nbFamille) {
		System.out.println("je crée des familles");
		List<Famille> familles = new ArrayList();
		Random r = new Random();
		for (int i = 0; i < nbFamille; i++) {
			double depenses = 0;
			int tailleMin, tailleMax, nombre;
			int dureeSejour = 40;
			Boolean handicap;
			nombre = r.nextInt(10) + 1;
			tailleMin = r.nextInt(140 - 120 + 1) + 120;
			tailleMax = r.nextInt(190 - 165 + 1) + 165;
			handicap = r.nextBoolean();
			depenses = 30 * nombre;

			Famille f = new Famille(nombre, tailleMin, tailleMax, dureeSejour, handicap, depenses);
			familleRepo.save(f);

			nbrVisiteurTotal += nombre;
			double nbVisiteur = nbrVisiteurTotal;
		}
	}

	public void choixAssignation(List<Famille> listeFamille) {

		// System.out.println("voici la liste des familles dans le parc"+listeFamille);
		Random r = new Random();
		for (Famille f : listeFamille) {

			int alea = r.nextInt(10);
			if (alea <= 6) {// 70% de chance de rentrer dans une attraction

				assignementAttraction(f);
			} else { // 50% de chance d'être dans boutique ou restauration
				int alea2 = r.nextInt(10);
				if (alea2 <= 5) {
					assignementBoutique(f);
				} else {
					assignementRestauration(f);
				}

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

		if (f.getTailleMin() >= a.getTailleMin() && f.getTailleMax() <= a.getTailleMax()) {
			System.out.println("Durée de trajet : " + distance(f.getPosition(), a.getCoordonnees()) / 2
					+ ", Durée de séjour avant trajet :" + f.getDureeSejour());
			f.setDureeSejour(f.getDureeSejour() - distance(f.getPosition(), a.getCoordonnees()) / 2);
			System.out.println("Durée de trajet : " + distance(f.getPosition(), a.getCoordonnees()) / 2
					+ ", Durée de séjour après trajet :" + f.getDureeSejour());
			f.setPosition(a.getCoordonnees());

			newQueue.add(f);
			a.setQueue(newQueue);
			familleRepo.save(f);
			attractionRepo.save(a);
		} else {
			assignementAttraction(f);
		}
	}

	public void assignementBoutique(Famille f) {
		Random r = new Random();
		List<Boutique> listeBoutique = boutiqueRepo.findAll();
		int alea = r.nextInt(listeBoutique.size());
		Boutique b = listeBoutique.get(alea);
		List<Marchandise> listeMarchandise = marchandiseRepo.findAll();
		f.setDureeSejour(f.getDureeSejour() - distance(f.getPosition(), b.getCoordonnees()) / 2);

		f.setPosition(b.getCoordonnees());
		int i = 1;
		while (i <= f.getNombre()) {
			for (Marchandise m : listeMarchandise) {
				int aleaChoix = r.nextInt(10) + 1;
				if (aleaChoix <= 2) {
					double depensesActuelles = f.getDepenses();
					f.setDepenses(depensesActuelles + m.getPrix());
					bilanFinancier += m.getPrix();
					m.setStock(m.getStock() - 1);
					m.setVente(m.getVente() + 1);
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
			System.out.println("je suis la famille " + f.getId() + " et j'ai " + f.getDepenses() + " d�penses");
			assignementAttraction(f);
		} else {

			familleRepo.save(f);
		}
	}

	public void assignementRestauration(Famille f) {
		Random r = new Random();
		List<Restauration> listeRestauration = restaurationRepo.findAll();
		int alea = r.nextInt(listeRestauration.size());
		Restauration restau = listeRestauration.get(alea);
		List<Boisson> listeBoisson = restau.getBoisson();
		List<Plat> listePlat = restau.getPlat();

		f.setDureeSejour(f.getDureeSejour() - distance(f.getPosition(), restau.getCoordonnees()) / 2);
		f.setPosition(restau.getCoordonnees());

		int i = 1;

		while (i <= f.getNombre()) {
			for (Boisson b : listeBoisson) {
				int aleaChoix = r.nextInt(10) + 1;
				if (aleaChoix <= 2) {
					double depensesActuelles = f.getDepenses();
					f.setDepenses(depensesActuelles + b.getPrix());
					bilanFinancier += b.getPrix();
					b.setStock(b.getStock() - 1);
					b.setVente(b.getVente() + 1);
					boissonRepo.save(b);
				}
			}
			for (Plat p : listePlat) {
				int aleaChoix = r.nextInt(10) + 1;
				if (aleaChoix <= 2) {
					double depensesActuelles = f.getDepenses();
					f.setDepenses(depensesActuelles + p.getPrix());
					bilanFinancier += p.getPrix();
					p.setStock(p.getStock() - 1);
					p.setVente(p.getVente() + 1);
					platRepo.save(p);
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
			System.out.println("je suis la famille " + f.getId() + " et j'ai " + f.getDepenses() + " d�penses");
			assignementAttraction(f);
		} else {

			familleRepo.save(f);
		}
	}

	public void avancementJournee() // Continue tant que les familles ne sont pas parties
	// permet de faire diminuer le temps qu il reste aux familles

	{
		System.out.println("je suis dans avancement journ�e");
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
					System.out.println("la dur�e de l'attraction est de " + a.getDuree());
					dureeSejour -= a.getDuree();

					famille.setDureeSejour(dureeSejour);
					familleRepo.save(famille);
				}

				else if (famille.getNombre() > capaciteActuelle) {
					for (Famille f : a.getQueue()) {
						System.out.println("---------------------------------------------------");
						System.out.println("J'attends dans la queue, capacit� actuelle =" + capaciteActuelle);
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
							System.out.println("il me reste du temps, je vais � la boutique 1");
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
							System.out.println("il me reste du temps, je vais � la boutique 2");
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

	public int distance(Coordonnees coordonnees1, Coordonnees coordonnees2) {
		int x1 = coordonnees1.getX();
		int y1 = coordonnees1.getY();
		int x2 = coordonnees2.getX();
		int y2 = coordonnees2.getY();
		return (int) Math.round(Math.sqrt((y2 - y1) ^ 2 + (x2 - x1) ^ 2));

	}

}
