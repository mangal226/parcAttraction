package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Marchandise {
	@Id//Obligatoire
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private double prix;
	private String nom;
	
	
	public Marchandise(double prix, String nom) {
		super();
		this.prix = prix;
		this.nom = nom;
	}
	public Marchandise() {
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	

}
