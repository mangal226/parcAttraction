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

import SopraAjc.ParcAttractionSpring.model.Famille;
import SopraAjc.ParcAttractionSpring.repository.FamilleRepository;

@Transactional
@Rollback(true)
@ExtendWith(SpringExtension.class) // remplace @RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class FamilleTest {

	@Autowired
	FamilleRepository familleRepo;
	
	@Test
	public void testInsert() {
		Famille famille = new Famille(4,150,200,40,false,0);
		familleRepo.save(famille);
		assertNotNull(famille.getId());
		assertTrue(familleRepo.findById(famille.getId()).isPresent());
	}
	
}
