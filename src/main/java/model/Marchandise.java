package model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Marchandise {
	@Id//Obligatoire
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private double prix;
	private String nom;
	
	
	public Marchandise(double prix, String nom) {
		super();
		this.prix = prix;
		this.nom = nom;
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
	
	

}
