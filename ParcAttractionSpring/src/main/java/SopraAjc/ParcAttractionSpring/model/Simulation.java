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
	private int nbVisiteursTotal;
	
	@JsonView(JsonViews.Common.class)
	private LinkedList<Double> BilanJournaliers = new LinkedList();

	@JsonView(JsonViews.Common.class)
	private LinkedList<Integer> nbVisiteursJournaliers = new LinkedList();
	
	@JsonView(JsonViews.Common.class)
	private LinkedList<Double> bilanVisiteursJournaliers = new LinkedList();
	
	@JsonView(JsonViews.Common.class)
	private double bilanTotal;
	
	@JsonView(JsonViews.Common.class)
	private int nbBoissonsVendus;
	@JsonView(JsonViews.Common.class)
	private int nbPlatsVendus;
	@JsonView(JsonViews.Common.class)
	private int nbMarchandisesVendus;
	@JsonView(JsonViews.Common.class)
	private int nbBoissonsEnStock;
	@JsonView(JsonViews.Common.class)
	private int nbMarchandisesEnStock;
	
	
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



}