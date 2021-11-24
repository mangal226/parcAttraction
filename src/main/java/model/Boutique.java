package model;

import java.util.*;

public class Boutique {
	
	private List <Marchandise> enVente = new ArrayList();

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
