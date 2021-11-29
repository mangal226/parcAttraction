package model;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Boutique {
	
	
	@OneToMany(mappedBy = "boutique")
	private List <Marchandise> enVente = new ArrayList();
	
	public Boutique() {
		
	}

	public Boutique(List<Marchandise> enVente) {
		this.enVente = enVente;
	}

	public List<Marchandise> getEnVente() {
		return enVente;
	}

	public void setEnVente(List<Marchandise> enVente) {
		this.enVente = enVente;
	}
	
	
	
	
	
}
