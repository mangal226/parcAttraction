package SopraAjc.ParcAttractionSpring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SopraAjc.ParcAttractionSpring.exception.PlatException;
import SopraAjc.ParcAttractionSpring.model.Plat;
import SopraAjc.ParcAttractionSpring.repository.PlatRepository;

@Service
public class PlatService {

	@Autowired
	private PlatRepository platRepo;
	
	public void creationOuModification(Plat plat) {
		if (plat.getNom() == null) {
			throw new PlatException();
		}
		platRepo.save(plat);
	}
	
	public void suppression(Long id) {
		suppression(getById(id));
	}

	public void suppression(Plat plat) {
		Plat PlatEnBase = null;
		if (plat.getId() != null) {
			PlatEnBase = platRepo.findById(plat.getId()).orElseThrow(PlatException::new);
			platRepo.delete(PlatEnBase);
		} else {
			throw new PlatException();
		}
	}

	public Plat getById(Long id) {
		if (id != null) {
			return platRepo.findById(id).orElseThrow(PlatException::new);
		}
		throw new PlatException();
	}

	public List<Plat> getAll() {
		return platRepo.findAll();
	}

}
