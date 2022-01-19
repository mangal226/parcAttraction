package SopraAjc.ParcAttractionSpring.services;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import SopraAjc.ParcAttractionSpring.model.Attraction;
import SopraAjc.ParcAttractionSpring.model.Boisson;
import SopraAjc.ParcAttractionSpring.model.Boutique;
import SopraAjc.ParcAttractionSpring.model.Coordonnees;
import SopraAjc.ParcAttractionSpring.model.Marchandise;
import SopraAjc.ParcAttractionSpring.model.Plat;
import SopraAjc.ParcAttractionSpring.model.Restauration;
import SopraAjc.ParcAttractionSpring.model.Simulation;
import SopraAjc.ParcAttractionSpring.repository.AttractionRepository;
import SopraAjc.ParcAttractionSpring.repository.BoissonRepository;
import SopraAjc.ParcAttractionSpring.repository.BoutiqueRepository;
import SopraAjc.ParcAttractionSpring.repository.FamilleRepository;
import SopraAjc.ParcAttractionSpring.repository.MarchandiseRepository;
import SopraAjc.ParcAttractionSpring.repository.PlatRepository;
import SopraAjc.ParcAttractionSpring.repository.RestaurationRepository;
import SopraAjc.ParcAttractionSpring.repository.UserRepository;

@Service
public class ConsoleService implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	static private BoissonRepository boissonRepo;

	@Autowired
	static private PlatRepository platRepo;

	@Autowired
	static private MarchandiseRepository marchandiseRepo;

	@Autowired
	static private SimulationService simulationService;

	@Autowired
	static private FamilleRepository familleRepo;

	@Autowired
	static private AttractionRepository attractionRepo;
	
	@Autowired
	static private BoutiqueRepository boutiqueRepo;
	@Autowired
	static private RestaurationRepository restaurationRepo;

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleService.class);

	@Override
	public void run(String... args) throws Exception {
		//initPassword();
		Attraction a1 = new Attraction("Roue", 7, 40, 110, 200, false, new Coordonnees(0, 0));
		attractionRepo.save(a1);
		Attraction a2 = new Attraction("GrandHuit", 11, 40, 110, 200, false, new Coordonnees(10, 0));
		attractionRepo.save(a2);

		Boisson a = new Boisson("cafe", 15, 300);
		Boisson b = new Boisson("the", 15, 300);
		List<Boisson> listeBoisson = Arrays.asList(a, b);
		Plat c = new Plat("pate", 15, 300);
		Plat d = new Plat("pizza", 15, 300);
		List<Plat> listePlat = Arrays.asList(c, d);
		Restauration r1 = new Restauration("barbecue",listeBoisson,listePlat,new Coordonnees(10, 5));
		Marchandise e = new Marchandise("peluche", 15, 300);
		Marchandise f = new Marchandise("poing", 15, 300);
		List<Marchandise> listeMarchandise = Arrays.asList(e, f);
		Boutique b1 = new Boutique("Souvenirs",listeMarchandise, new Coordonnees(5, 5));
		boissonRepo.save(a);
		boissonRepo.save(b);
		platRepo.save(c);
		platRepo.save(d);
		restaurationRepo.save(r1);
		marchandiseRepo.save(e);
		marchandiseRepo.save(f);
		boutiqueRepo.save(b1);
		Simulation simulation=new Simulation();
		simulationService.simulation(1, 3, simulation);
		
	}

	private void initPassword() {
		userRepo.findAll().forEach(u->{
			u.setPassword(passwordEncoder.encode(u.getPassword()));
			userRepo.save(u);
		});

	}
}
