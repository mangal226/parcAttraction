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
import model.Boisson;
import repository.BoissonRepository;


@Transactional
@Rollback(true)
@ExtendWith(SpringExtension.class) // remplace @RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
class BoissonTest {
	
	@Autowired
	private BoissonRepository boissonRepo;

	@Test
	public void testInsert() {
		Boisson boisson = new Boisson("cafe",15);
		boissonRepo.save(boisson);
		assertNotNull(boisson.getId());
		assertTrue(boissonRepo.findById(boisson.getId()).isPresent());
	}
}
