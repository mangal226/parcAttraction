package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Boisson {
	
	@Id//Obligatoire
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private double prix;
	
	public Boisson(){
		
	}
	
	public Boisson(String nom, double prix)
	{
		this.nom = nom;
		this.prix = prix;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Boisson [id=" + id + ", nom=" + nom + ", prix=" + prix + "]";
	}

	
}
