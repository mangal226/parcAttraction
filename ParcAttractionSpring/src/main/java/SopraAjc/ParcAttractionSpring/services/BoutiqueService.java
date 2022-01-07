package SopraAjc.ParcAttractionSpring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SopraAjc.ParcAttractionSpring.exception.BoutiqueException;
import SopraAjc.ParcAttractionSpring.model.Boutique;
import SopraAjc.ParcAttractionSpring.model.Marchandise;
import SopraAjc.ParcAttractionSpring.repository.BoutiqueRepository;
import SopraAjc.ParcAttractionSpring.repository.MarchandiseRepository;


@Service
public class BoutiqueService {
	
	@Autowired
	private BoutiqueRepository boutiqueRepo;
	@Autowired
	private MarchandiseRepository marchandiseRepo;

	public void creation(Boutique boutique) {
		if (boutique.getId() == null) {
			throw new BoutiqueException();
		}
		boutiqueRepo.save(boutique);
	}
	
	public void suppression(Boutique boutique) {
		Boutique boutiqueEnBase = boutiqueRepo.findById(boutique.getId()).orElseThrow(BoutiqueException::new);
		boutiqueRepo.delete(boutiqueEnBase);
	}
	
	public void suppression(Long id) {
		suppression(getById(id));
	}
	
	public Boutique getById(Long id) {
		if (id != null) {
			return boutiqueRepo.findById(id).orElseThrow(BoutiqueException::new);
		}
		throw new BoutiqueException();
	}

	public List<Boutique> getAll() {
		return boutiqueRepo.findAll();
	}
	
	/*public List<Boutique> getEnVente() {
		return boutiqueRepo.findAll();
	}*/
	
	
	public List<Marchandise> getEnVente() {
		return marchandiseRepo.findAll();
	}
	
}
