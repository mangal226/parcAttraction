package service;

import org.springframework.beans.factory.annotation.Autowired;

import exception.BoissonException;
import model.Boisson;
import repository.BoissonRepository;

public class BoissonService {
	
	@Autowired
	private BoissonRepository boissonRepo;

	public void creation(Boisson boisson) {
		if (boisson.getNom() == null) {
			throw new BoissonException();
		}
		boissonRepo.save(boisson);
	}
	
	public void suppression(Boisson boisson) {
		// traitement sur le compagnon
		// delete
		// null maitre
		Boisson boissonEnBase = boissonRepo.findById(boisson.getId()).orElseThrow(BoissonException::new);
		boissonRepo.delete(boissonEnBase);
	}
}
