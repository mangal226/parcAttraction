package SopraAjc.ParcAttractionSpring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SopraAjc.ParcAttractionSpring.exception.AttractionException;
import SopraAjc.ParcAttractionSpring.model.Attraction;
import SopraAjc.ParcAttractionSpring.repository.AttractionRepository;

@Service
public class AttractionService {
	
	@Autowired
	private AttractionRepository attractionRepo;

	public void creation(Attraction attraction) {
		if (attraction.getNom() == null) {
			throw new AttractionException();
		}
		attractionRepo.save(attraction);
	}
	
	public void suppression(Long id) {
		// traitement sur le compagnon
		// delete
		// null maitre
		Attraction attractionEnBase = attractionRepo.findById(id).orElseThrow(AttractionException::new);
		attractionRepo.delete(attractionEnBase);
	}
	
	public void suppression(Attraction attraction) {
		// traitement sur le compagnon
		// delete
		// null maitre
		Attraction attractionEnBase = attractionRepo.findById(attraction.getId()).orElseThrow(AttractionException::new);
		attractionRepo.delete(attractionEnBase);
	}
	
	public List<Attraction> getAll(){
		return attractionRepo.findAll();
	}
	
	public Attraction getById(Long id) {
		if (id !=null) {
			return attractionRepo.findById(id).orElseThrow(AttractionException::new);
		}
		throw new AttractionException();
	}
	
}
