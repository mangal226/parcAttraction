package SopraAjc.ParcAttractionSpring.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Restauration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(JsonViews.Common.class)
	private Long Id;
	@JsonView(JsonViews.Common.class)
	private String nom;
	@JsonView(JsonViews.Common.class)
	private String description;
	@JsonView(JsonViews.Common.class)
	@ManyToMany
	private List<Boisson> boisson = new ArrayList();
	@JsonView(JsonViews.Common.class)
	@ManyToMany
	private  List<Plat> plat = new ArrayList();
	
	@JsonView(JsonViews.Common.class)
	@Embedded
	private Coordonnees coordonnees;
	
	
	public Restauration () {
		
	}
	

	public Restauration(String nom, String description, List<Boisson> boisson, List<Plat> plat, Coordonnees coordonnees) {
		this.nom=nom;
		this.description=description;
  	    this.boisson = boisson;
		this.plat = plat;
		this.coordonnees = coordonnees;
	}


	public List<Boisson> getBoisson() {
		return boisson;
	}
	public void setBoisson(List<Boisson> boisson) {
		this.boisson = boisson;
	}
	public List<Plat> getPlat() {
		return plat;
	}
	public void setPlat(List<Plat> plat) {
		this.plat = plat;
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	
	
	public Coordonnees getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}

	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Restauration [boisson=" + boisson + ", plat=" + plat + "]";
	}
	
	

}
