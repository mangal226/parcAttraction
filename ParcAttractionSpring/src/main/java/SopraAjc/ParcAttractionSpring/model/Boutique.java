package SopraAjc.ParcAttractionSpring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

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
	
	@Embedded
	private Coordonnees coordonnees;
	
	public Boutique() {
		
	}

	public Boutique(List<Marchandise> enVente, Coordonnees coordonnees) {
		super();
		this.enVente = enVente;
		this.coordonnees = coordonnees;
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

	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
	
}
