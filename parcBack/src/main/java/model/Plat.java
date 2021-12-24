package model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("plat")
public class Plat {
	@Id//Obligatoire
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(JsonViews.Common.class)
	private Long Id;
	
	@JsonView(JsonViews.Common.class)
	private String nom;
	
	@JsonView(JsonViews.Common.class)
	private double prix;
	
	
	public Plat(String nom, double prix)
	{
		this.nom = nom;
		this.prix = prix;
	}
	
	public Plat(){
	}
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		this.Id = id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom() {
		this.nom = nom;
		
	}
	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

}
