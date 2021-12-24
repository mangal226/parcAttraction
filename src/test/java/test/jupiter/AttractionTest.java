package test.jupiter;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.AppConfig;
import model.Attraction;
import repository.AttractionRepository;

@Transactional
@Rollback(true)
@ExtendWith(SpringExtension.class) // remplace @RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
class AttractionTest {

	@Autowired
	AttractionRepository attractionRepo;
	
	@Test
	public void testInsert() {
		Attraction attraction = new Attraction("Roue",20,40,150,200,false);
		attractionRepo.save(attraction);
		assertNotNull(attraction.getId());
		assertTrue(attractionRepo.findById(attraction.getId()).isPresent());
	}

}
