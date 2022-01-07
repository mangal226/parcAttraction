package SopraAjc.ParcAttractionSpring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SopraAjc.ParcAttractionSpring.exception.RestaurationException;
import SopraAjc.ParcAttractionSpring.model.Restauration;
import SopraAjc.ParcAttractionSpring.repository.RestaurationRepository;

@Service
public class RestaurationService {

	@Autowired
	private RestaurationRepository restoRepo;
	
	public void creationOuModification(Restauration restauration) {
		if (restauration.getId() == null) {
			throw new RestaurationException();
		}
		restoRepo.save(restauration);
	}
	
	public void suppression(Long id) {
		suppression(getById(id));
	}

	public void suppression(Restauration restauration) {
		Restauration RestaurantEnBase = null;
		if (restauration.getId() != null) {
			RestaurantEnBase = restoRepo.findById(restauration.getId()).orElseThrow(RestaurationException::new);
			restoRepo.delete(RestaurantEnBase);
		} else {
			throw new RestaurationException();
		}
	}

	public Restauration getById(Long id) {
		if (id != null) {
			return restoRepo.findById(id).orElseThrow(RestaurationException::new);
		}
		throw new RestaurationException();
	}

	public List<Restauration> getAll() {
		return restoRepo.findAll();
	}
}
