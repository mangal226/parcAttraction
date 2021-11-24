package model;

import java.util.ArrayList;
import java.util.List;

public class Restauration {
	private List<Boisson> boisson = new ArrayList();
	private List<Plat> plat = new ArrayList();
	
	
	
	public Restauration(List<Boisson> boisson, List<Plat> plat) {
		this.boisson = boisson;
		this.plat = plat;
	}
	
	public List<Boisson> getBoisson() {
		return boisson;
	}
	public void setBoisson(List<Boisson> boisson) {
		this.boisson = boisson;
	}
	public List<Plat> getPlat() {
		return plat;
	}
	public void setPlat(List<Plat> plat) {
		this.plat = plat;
	}

	@Override
	public String toString() {
		return "Restauration [boisson=" + boisson + ", plat=" + plat + "]";
	}
	
	

}
