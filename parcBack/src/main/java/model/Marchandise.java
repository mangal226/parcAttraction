package model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Marchandise {
	@Id//Obligatoire
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(JsonViews.Common.class)
	private Long Id;
	@JsonView(JsonViews.Common.class)
	private double prix;
	@JsonView(JsonViews.Common.class)
	private String nom;
	@JsonView(JsonViews.Common.class)
	private int stock;
	@JsonView(JsonViews.Common.class)
	private int vente;
	
	
	public Marchandise(String nom,double prix,int stock) {
		super();
		this.prix = prix;
		this.nom = nom;
		this.stock=stock;
	}
	public Marchandise() {
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
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
	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Marchandise other = (Marchandise) obj;
		return Objects.equals(Id, other.Id);
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getVente() {
		return vente;
	}
	public void setVente(int vente) {
		this.vente = vente;
	}
	@Override
	public String toString() {
		return "Marchandise [Id=" + Id + ", prix=" + prix + ", nom=" + nom + ", stock=" + stock + ", vente=" + vente
				+ "]";
	}
	
	
	

}
