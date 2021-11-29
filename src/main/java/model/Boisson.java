package model;

import java.io.Serializable;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import java.util.List;
@Entity
public class Boisson {
	
	@Id//Obligatoire
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private double prix;
	
	public Boisson(){
		
	}
	
	public Boisson(String nom, double prix)
	{
		this.nom = nom;
		this.prix = prix;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
