package model;
import javax.persistence.*;

@Entity
@DiscriminatorValue("plat")
public class Plat {
	@Id//Obligatoire
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String nom;
	private double prix;
	
	
	public Plat(String nom, double prix)
	{
		this.nom = nom;
		this.prix = prix;
	}
	
	public Plat(){
	}
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
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
