package model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Restauration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(JsonViews.Common.class)
	private Long Id;
	
	@ManyToMany
	private List<Boisson> boisson = new ArrayList();
	
	@ManyToMany
	private  List<Plat> plat = new ArrayList();
	
	
	public Restauration () {
		
	}
	
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
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	@Override
	public String toString() {
		return "Restauration [boisson=" + boisson + ", plat=" + plat + "]";
	}
	
	

}
