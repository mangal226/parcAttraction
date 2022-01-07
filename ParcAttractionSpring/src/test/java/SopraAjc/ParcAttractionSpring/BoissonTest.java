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

import SopraAjc.ParcAttractionSpring.model.Boisson;
import SopraAjc.ParcAttractionSpring.repository.BoissonRepository;


@Transactional
@Rollback(true)
@ExtendWith(SpringExtension.class) // remplace @RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class BoissonTest {
	
	@Autowired
	private BoissonRepository boissonRepo;

	@Test
	public void testInsert() {
		Boisson boisson = new Boisson("cafe",15,300);
		boissonRepo.save(boisson);
		assertNotNull(boisson.getId());
		assertTrue(boissonRepo.findById(boisson.getId()).isPresent());
	}
}
