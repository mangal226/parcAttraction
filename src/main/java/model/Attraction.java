package model;
import java.util.List;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.*;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Attraction {
@id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String nom;
	private int duree, capacite, tailleMin, tailleMax;
	private boolean restHandi;
	@OneToMany(mappedBy = "attraction")
	private LinkedList<Famille> queue=new LinkedList();
	
	public Attraction() {
		
	}
	
	/*public Attraction(String nom, int duree, int capacite, int tailleMin, int tailleMax, boolean restHandi) {
		this.nom=nom;
		this.duree = duree;
		this.capacite = capacite;
		this.tailleMin = tailleMin;
		this.tailleMax = tailleMax;
		this.restHandi = restHandi;
	}*/

	public Attraction(int id, String nom, int duree, int capacite, int tailleMin, int tailleMax, boolean restHandi) {
		this.id=id;
		this.nom=nom;
		this.duree = duree;
		this.capacite = capacite;
		this.tailleMin = tailleMin;
		this.tailleMax = tailleMax;
		this.restHandi = restHandi;
	}

	



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public int getTailleMin() {
		return tailleMin;
	}

	public void setTailleMin(int tailleMin) {
		this.tailleMin = tailleMin;
	}

	public int getTailleMax() {
		return tailleMax;
	}

	public void setTailleMax(int tailleMax) {
		this.tailleMax = tailleMax;
	}

	public boolean isRestHandi() {
		return restHandi;
	}

	public void setRestHandi(boolean restHandi) {
		this.restHandi = restHandi;
	}

	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public LinkedList<Famille> getQueue() {
		return queue;
	}

	public void setQueue(LinkedList<Famille> queue) {
		this.queue = queue;
	}

	@Override
	public String toString() {
		return "Attraction [nom=" + nom + ", id=" + id + ", duree=" + duree + ", capacite=" + capacite + ", tailleMin="
				+ tailleMin + ", tailleMax=" + tailleMax + ", restHandi=" + restHandi + ", queue=" + queue + "]";
	}
	
	
	
	
}
