package SopraAjc.ParcAttractionSpring;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import SopraAjc.ParcAttractionSpring.model.Attraction;
import SopraAjc.ParcAttractionSpring.repository.AttractionRepository;

@Transactional
@Rollback(true)
@ExtendWith(SpringExtension.class) // remplace @RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
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
