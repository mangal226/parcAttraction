package model;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


public class Boutique {
	
	private int id;
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
