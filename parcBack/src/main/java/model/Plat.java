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
	
	@JsonView(JsonViews.Common.class)
	private int stock;
	
	@JsonView(JsonViews.Common.class)
	private int vente;
	
	public Plat(String nom, double prix, int stock)
	{
		this.nom = nom;
		this.prix = prix;
		this.stock=stock;
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getVente() {
		return vente;
	}

	public void setVente(int vente) {
		this.vente = vente;
	}

	@Override
	public String toString() {
		return "Plat [Id=" + Id + ", nom=" + nom + ", prix=" + prix + ", stock=" + stock + ", vente=" + vente + "]";
	}

	

}
