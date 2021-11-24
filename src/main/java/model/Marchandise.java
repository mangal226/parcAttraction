package model;

public class Marchandise {
	private double prix;
	private String nom;
	public Marchandise(double prix, String nom) {
		super();
		this.prix = prix;
		this.nom = nom;
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
