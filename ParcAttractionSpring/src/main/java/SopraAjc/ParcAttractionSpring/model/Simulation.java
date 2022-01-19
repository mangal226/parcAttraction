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


	public Simulation(Long id, int nbFamilles, int nbJours, int nbVisiteursTotal, double bilanFinancier,
			int nbBoissonsVendus, int nbPlatsVendus, int nbMarchandisesVendus, int nbBoissonsEnStock,
			int nbMarchandisesEnStock) {
		this.id = id;
		this.nbFamilles = nbFamilles;
		this.nbJours = nbJours;
		this.nbVisiteursTotal = nbVisiteursTotal;
		this.bilanTotal = bilanFinancier;
		this.nbBoissonsVendus = nbBoissonsVendus;
		this.nbPlatsVendus = nbPlatsVendus;
		this.nbMarchandisesVendus = nbMarchandisesVendus;
		this.nbBoissonsEnStock = nbBoissonsEnStock;
		this.nbMarchandisesEnStock = nbMarchandisesEnStock;
	}


	


	public Simulation(int nbFamilles, int nbJours, int nbVisiteursTotal, double bilanFinancier, int nbBoissonsVendus,
			int nbPlatsVendus, int nbMarchandisesVendus, int nbBoissonsEnStock, int nbMarchandisesEnStock) {
		this.nbFamilles = nbFamilles;
		this.nbJours = nbJours;
		this.nbVisiteursTotal = nbVisiteursTotal;
		this.bilanTotal = bilanFinancier;
		this.nbBoissonsVendus = nbBoissonsVendus;
		this.nbPlatsVendus = nbPlatsVendus;
		this.nbMarchandisesVendus = nbMarchandisesVendus;
		this.nbBoissonsEnStock = nbBoissonsEnStock;
		this.nbMarchandisesEnStock = nbMarchandisesEnStock;
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


	public int getNbVisiteursTotal() {
		return nbVisiteursTotal;
	}


	public void setNbVisiteursTotal(int nbVisiteursTotal) {
		this.nbVisiteursTotal = nbVisiteursTotal;
	}


	public double getBilanFinancier() {
		return bilanTotal;
	}


	public void setBilanFinancier(double bilanFinancier) {
		this.bilanTotal = bilanFinancier;
	}


	public int getNbBoissonsVendus() {
		return nbBoissonsVendus;
	}


	public void setNbBoissonsVendus(int nbBoissonsVendus) {
		this.nbBoissonsVendus = nbBoissonsVendus;
	}


	public int getNbPlatsVendus() {
		return nbPlatsVendus;
	}


	public void setNbPlatsVendus(int nbPlatsVendus) {
		this.nbPlatsVendus = nbPlatsVendus;
	}


	public int getNbMarchandisesVendus() {
		return nbMarchandisesVendus;
	}


	public void setNbMarchandisesVendus(int nbMarchandisesVendus) {
		this.nbMarchandisesVendus = nbMarchandisesVendus;
	}


	public int getNbBoissonsEnStock() {
		return nbBoissonsEnStock;
	}


	public void setNbBoissonsEnStock(int nbBoissonsEnStock) {
		this.nbBoissonsEnStock = nbBoissonsEnStock;
	}


	public int getNbMarchandisesEnStock() {
		return nbMarchandisesEnStock;
	}


	public void setNbMarchandisesEnStock(int nbMarchandisesEnStock) {
		this.nbMarchandisesEnStock = nbMarchandisesEnStock;
	}


	public LinkedList<Double> getBilanJournaliers() {
		return BilanJournaliers;
	}


	public void setBilanJournaliers(LinkedList<Double> bilanJournaliers) {
		BilanJournaliers = bilanJournaliers;
	}


	public LinkedList<Integer> getNbVisiteursJournaliers() {
		return nbVisiteursJournaliers;
	}


	public void setNbVisiteursJournaliers(LinkedList<Integer> nbVisiteursJournaliers) {
		this.nbVisiteursJournaliers = nbVisiteursJournaliers;
	}


	public LinkedList<Double> getBilanVisiteursJournaliers() {
		return bilanVisiteursJournaliers;
	}


	public void setBilanVisiteursJournaliers(LinkedList<Double> bilanVisiteursJournaliers) {
		this.bilanVisiteursJournaliers = bilanVisiteursJournaliers;
	}


	public double getBilanTotal() {
		return bilanTotal;
	}


	public void setBilanTotal(double bilanTotal) {
		this.bilanTotal = bilanTotal;
	}
	

	
	
	
	

}
