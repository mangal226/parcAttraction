package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Boutique {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(JsonViews.Common.class)
	private Long id;
	
	@ManyToMany
	@JsonView(JsonViews.BoutiqueMarchandise.class)
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

	public Long getId() {
		return id;
	}
	
	
	
	
	
}
