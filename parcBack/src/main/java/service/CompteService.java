package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.CompteException;
import model.Boutique;
import model.Compte;
import repository.CompteRepository;



@Service
public class CompteService {
	
	@Autowired
	private CompteRepository compteRepo;
	

	public void creation(Compte compte) {
		if (compte.getId() == null) {
			throw new CompteException();
		}
		compteRepo.save(compte);
	}
	
	public void suppression(Compte compte) {
		Compte compteEnBase = compteRepo.findById(compte.getId()).orElseThrow(CompteException::new);
		compteRepo.delete(compteEnBase);
	}
	
	public void suppression(Long id) {
		suppression(getById(id));
	}
	
	public Compte getById(Long id) {
		if (id != null) {
			return compteRepo.findById(id).orElseThrow(CompteException::new);
		}
		throw new CompteException();
		
			
	}
	
	
	public List<Compte> getAll() {
		return compteRepo.findAll();
	}
	
	
}

	
	

