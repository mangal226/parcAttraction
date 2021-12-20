package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import exception.MarchandiseException;
import model.Marchandise;
import repository.MarchandiseRepository;

public class MarchandiseService {
	
	@Autowired
	private MarchandiseRepository marchandiseRepo;
	
	public void creation(Marchandise marchandise) {
		if (marchandise.getNom() == null) {
			throw new MarchandiseException();
		}
		marchandiseRepo.save(marchandise);
	}

	public void suppression(Marchandise marchandise) {
		Marchandise MarchandiseEnBase = null;
		if (marchandise.getId() != null) {
			MarchandiseEnBase = marchandiseRepo.findById(marchandise.getId()).orElseThrow(MarchandiseException::new);
			marchandiseRepo.delete(MarchandiseEnBase);
		} else {
			throw new MarchandiseException();
		}
	}

	public Marchandise getById(Long id) {
		if (id != null) {
			return marchandiseRepo.findById(id).orElseThrow(MarchandiseException::new);
		}
		throw new MarchandiseException();
	}

	public List<Marchandise> getAll() {
		return marchandiseRepo.findAll();
	}

}
