package service;

import org.springframework.beans.factory.annotation.Autowired;

import exception.AttractionException;
import model.Attraction;
import repository.AttractionRepository;

public class AttractionService {
	
	@Autowired
	private AttractionRepository attractionRepo;

	public void creation(Attraction attraction) {
		if (attraction.getNom() == null) {
			throw new AttractionException();
		}
		attractionRepo.save(attraction);
	}
	
	public void suppression(Attraction attraction) {
		// traitement sur le compagnon
		// delete
		// null maitre
		Attraction attractionEnBase = attractionRepo.findById(attraction.getId()).orElseThrow(AttractionException::new);
		attractionRepo.delete(attractionEnBase);
	}
	
}
