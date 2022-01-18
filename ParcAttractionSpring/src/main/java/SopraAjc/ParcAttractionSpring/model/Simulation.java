package SopraAjc.ParcAttractionSpring.model;

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
	private double bilanFinancier;
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
		this.bilanFinancier = bilanFinancier;
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
		this.bilanFinancier = bilanFinancier;
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
		return bilanFinancier;
	}


	public void setBilanFinancier(double bilanFinancier) {
		this.bilanFinancier = bilanFinancier;
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
	



	@Override
	public String toString() {
		return "Simulation [id=" + id + ", nbFamilles=" + nbFamilles + ", nbJours=" + nbJours + ", nbVisiteursTotal="
				+ nbVisiteursTotal + ", bilanFinancier=" + bilanFinancier + ", nbBoissonsVendus=" + nbBoissonsVendus
				+ ", nbPlatsVendus=" + nbPlatsVendus + ", nbMarchandisesVendus=" + nbMarchandisesVendus
				+ ", nbBoissonsEnStock=" + nbBoissonsEnStock + ", nbMarchandisesEnStock=" + nbMarchandisesEnStock + "]";
	}
	
	
	
	

}
