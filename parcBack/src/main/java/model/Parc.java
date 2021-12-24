package model;


public class Parc {
	private double prixEntree, prixPass;

	public Parc(double prixEntree, double prixPass) {
		this.prixEntree = prixEntree;
		this.prixPass = prixPass;
	}

	public double getPrixEntree() {
		return prixEntree;
	}

	public void setPrixEntree(double prixEntree) {
		this.prixEntree = prixEntree;
	}

	public double getPrixPass() {
		return prixPass;
	}

	public void setPrixPass(double prixPass) {
		this.prixPass = prixPass;
	}
	
	
	

}
