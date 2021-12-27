package test.jupiter;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.AppConfig;
import model.Attraction;
import model.Boisson;
import model.Famille;
import model.Marchandise;
import model.Plat;
import repository.AttractionRepository;
import repository.BoissonRepository;
import repository.FamilleRepository;
import repository.MarchandiseRepository;
import repository.PlatRepository;
import service.SimulationService;


@Transactional
@Rollback(true)
@ExtendWith(SpringExtension.class) // remplace @RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
class SimulationTest {
	
//	Autowired
//	AttractionRepository attractionRepo;
//	
//	@Test
//	public void testInsert() {
//		Attraction attraction = new Attraction("Roue",20,40,150,200,false);
//		attractionRepo.save(attraction);
//		assertNotNull(attraction.getId());
//		assertTrue(attractionRepo.findById(attraction.getId()).isPresent());
//	}

	@Autowired
	private BoissonRepository boissonRepo;
	
	@Autowired
	private PlatRepository platRepo;
	
	@Autowired
	private MarchandiseRepository marchandiseRepo;
	
	@Autowired
	private SimulationService simulationService;
	
	@Autowired
	private FamilleRepository familleRepo;
	
	@Autowired
	private AttractionRepository attractionRepo;
	
	@Test
	@Disabled
	void testSimulation() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testCreationFamille() {
		int nbFamille=5;
		simulationService.creationFamille(nbFamille);
		assertEquals(0,familleRepo.getById(1L).getDepenses());
	}

//	@Test
//	void testChoixAssignation() {
//		Famille f1=new Famille(5,120,200,40,false,0);
//		Famille f2=new Famille(6,120,200,40,false,0);
//		Famille f3=new Famille(7,120,200,40,false,0);
//		List<Famille> familles = Arrays.asList(f1,f2,f3);
//		simulationService.choixAssignation(familles);
//		assert
//	}

	@Test
	@Disabled
	void testAssignementAttraction() {
		Attraction attraction = new Attraction("Roue",20,40,150,200,false);
		attractionRepo.save(attraction);
		Famille f1=new Famille(5,120,200,40,false,0);
		simulationService.assignementAttraction(f1);
		assertNotNull(attractionRepo.getById(1L).getQueue());
	}
	
	@Test
	@Disabled
	void testAchatBoutiqueRestauration() {
		Boisson a = new Boisson("cafe",15,300);
		Boisson b = new Boisson("the",15,300);
		Boisson c = new Boisson("coca",15,300);
		Boisson d = new Boisson("z",15,300);
		Boisson e = new Boisson("s",15,300);
		Boisson f = new Boisson("a",15,300);
		boissonRepo.save(a);
		boissonRepo.save(b);
		boissonRepo.save(c);
		boissonRepo.save(d);
		boissonRepo.save(e);
		boissonRepo.save(f);
		Famille f1=new Famille(5,120,200,40,false,0);
		familleRepo.save(f1);
		simulationService.achatBoutiqueRestauration(f1);
		assertEquals(0, familleRepo.getById(1L).getDepenses());
		
	}
	@Test
	void testAvancementJournee() {
		Attraction a1 = new Attraction("Roue",7,40,110,200,false);
		attractionRepo.save(a1);
		Attraction a2 = new Attraction("GrandHuit",11,40,110,200,false);
		attractionRepo.save(a2);
		Boisson a = new Boisson("cafe",15,300);
		Boisson b = new Boisson("the",15,300);
		Plat c = new Plat("pate",15,300);
		Plat d = new Plat("pizza",15,300);
		Marchandise e = new Marchandise("peluche",15,300);
		Marchandise f = new Marchandise("poing",15,300);
		boissonRepo.save(a);
		boissonRepo.save(b);
		platRepo.save(c);
		platRepo.save(d);
		marchandiseRepo.save(e);
		marchandiseRepo.save(f);
		simulationService.simulation(1, 3);
//		assertEquals(70, familleRepo.getById(1L).getDureeSejour());
		assertEquals(0, attractionRepo.getById(2L).getNbrVisiteur());
//		assertEquals(300, boissonRepo.getById(1L).getStock());
	}

}
