package SopraAjc.ParcAttractionSpring.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SopraAjc.ParcAttractionSpring.exception.FamilleException;
import SopraAjc.ParcAttractionSpring.model.Famille;
import SopraAjc.ParcAttractionSpring.repository.FamilleRepository;

@Service
public class FamilleService {

	@Autowired
	private FamilleRepository familleRepo;
	
	public void creation(Famille famille) {
		if (famille.getNombre() == 0) {
			throw new FamilleException();
		}
		familleRepo.save(famille);
	}
	
	public void suppression(Long Id) {
		suppression(getById(Id));
	}

	public void suppression(Famille famille) {
		Famille familleEnBase = null;
		if (famille.getId() != null) {
			familleEnBase = familleRepo.findById(famille.getId()).orElseThrow(FamilleException::new);
			familleRepo.delete(familleEnBase);
		} else {
			throw new FamilleException();
		}
	}
	
	public Famille getById(Long id) {
		if (id != null) {
			return familleRepo.findById(id).orElseThrow(FamilleException::new);
		}
		throw new FamilleException();
	}

	public List<Famille> getAll() {
		return familleRepo.findAll();
	}
}
