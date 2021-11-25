package model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Restauration {
	private int Id;
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
	
	

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	@Override
	public String toString() {
		return "Restauration [boisson=" + boisson + ", plat=" + plat + "]";
	}
	
	

}
