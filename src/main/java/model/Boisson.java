package model;

import java.io.Serializable;
import java.util.List;

public class Boisson {
	private String nom;
	private double prix;
	
	public Boisson(String nom, double prix)
	{
		this.nom = nom;
		this.prix = prix;
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
