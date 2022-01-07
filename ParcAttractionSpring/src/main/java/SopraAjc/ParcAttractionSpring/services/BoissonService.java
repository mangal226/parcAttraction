package SopraAjc.ParcAttractionSpring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SopraAjc.ParcAttractionSpring.exception.BoissonException;
import SopraAjc.ParcAttractionSpring.model.Boisson;
import SopraAjc.ParcAttractionSpring.repository.BoissonRepository;

@Service
public class BoissonService {
	
	@Autowired
	private BoissonRepository boissonRepo;

	public void creation(Boisson boisson) {
		if (boisson.getNom() == null) {
			throw new BoissonException();
		}
		boissonRepo.save(boisson);
	}
	
	public void suppression(Long id) {
		// traitement sur le compagnon
		// delete
		// null maitre
		Boisson boissonEnBase = boissonRepo.findById(id).orElseThrow(BoissonException::new);
		boissonRepo.delete(boissonEnBase);
	}
	
	public void suppression(Boisson boisson) {
		// traitement sur le compagnon
		// delete
		// null maitre
		Boisson boissonEnBase = boissonRepo.findById(boisson.getId()).orElseThrow(BoissonException::new);
		boissonRepo.delete(boissonEnBase);
	}
	
	public List<Boisson> getAll(){
		return boissonRepo.findAll();
	}
	
	public Boisson getById(Long id) {
		return boissonRepo.getById(id);
	}
}
