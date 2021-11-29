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

@Entity
@DiscriminatorColumn(name="carte_a_disposition")
public class Restauration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@ManyToMany
	@JoinTable(
			name="liste_boisson", //Rename la table
			joinColumns = @JoinColumn(name="id_du_restaurant"), //rename la clé principale (Personnage car on est dans la classe Personnage)
			inverseJoinColumns = @JoinColumn(name="id_boisson") //rename l'autre clé, celle de l'attribut donc Equipement ici
			)
	private List<Boisson> boisson = new ArrayList();
	@ManyToMany
	@JoinTable(
			name="liste_plat", //Rename la table
			joinColumns = @JoinColumn(name="id_du_restaurant"), //rename la clé principale (Personnage car on est dans la classe Personnage)
			inverseJoinColumns = @JoinColumn(name="id_plat") //rename l'autre clé, celle de l'attribut donc Equipement ici
			)
	private List<Plat> plat = new ArrayList();
	
	
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
