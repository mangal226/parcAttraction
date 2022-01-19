package SopraAjc.ParcAttractionSpring.model;

import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Simulation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(JsonViews.Common.class)
	private Long id;
	
	@JsonView(JsonViews.Common.class)
	private int nbFamilles;
	@JsonView(JsonViews.Common.class)
	private int nbJours;
	@JsonView(JsonViews.Common.class)
	private int nbrVisiteurs;
	
	@JsonView(JsonViews.Common.class)
	private int nbBoissons;
	
	@JsonView(JsonViews.Common.class)
	private int nbPlats;
	
	@JsonView(JsonViews.Common.class)
	private int nbMarchandises;
	
	@JsonView(JsonViews.Common.class)
	private double bilanFinancier;
	
	
	public Simulation() {
		
	}


	public Simulation(Long id, int nbFamilles, int nbJours) {
		this.id = id;
		this.nbFamilles = nbFamilles;
		this.nbJours = nbJours;

	}


	


	public Simulation(int nbFamilles, int nbJours) {
		this.nbFamilles = nbFamilles;
		this.nbJours = nbJours;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public int getNbFamilles() {
		return nbFamilles;
	}


	public void setNbFamilles(int nbFamilles) {
		this.nbFamilles = nbFamilles;
	}


	public int getNbJours() {
		return nbJours;
	}


	public void setNbJours(int nbJours) {
		this.nbJours = nbJours;
	}


	public int getNbrVisiteurs() {
		return nbrVisiteurs;
	}


	public void setNbrVisiteurs(int nbrVisiteurs) {
		this.nbrVisiteurs = nbrVisiteurs;
	}


	public int getNbBoissons() {
		return nbBoissons;
	}


	public void setNbBoissons(int nbBoissons) {
		this.nbBoissons = nbBoissons;
	}


	public int getNbPlats() {
		return nbPlats;
	}


	public void setNbPlats(int nbPlats) {
		this.nbPlats = nbPlats;
	}


	public int getNbMarchandises() {
		return nbMarchandises;
	}


	public void setNbMarchandises(int nbMarchandises) {
		this.nbMarchandises = nbMarchandises;
	}


	public double getBilanFinancier() {
		return bilanFinancier;
	}


	public void setBilanFinancier(double bilanFinancier) {
		this.bilanFinancier = bilanFinancier;
	}
	
	



}