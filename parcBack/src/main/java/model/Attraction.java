package model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Attraction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(JsonViews.Common.class)
    private Long id;
	@JsonView(JsonViews.Common.class)
	private String nom;
	@JsonView(JsonViews.Common.class)
	private int duree, capacite, tailleMin, tailleMax;
	@JsonView(JsonViews.Common.class)
	private boolean restHandi;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Famille> queue=new ArrayList();
	
	public Attraction() {
	
	}
	
	public Attraction(Long id, String nom, int duree, int capacite, int tailleMin, int tailleMax, boolean restHandi) {
		this.id=id;
		this.nom=nom;
		this.duree = duree;
		this.capacite = capacite;
		this.tailleMin = tailleMin;
		this.tailleMax = tailleMax;
		this.restHandi = restHandi;
	}

	public Attraction(String nom, int duree, int capacite, int tailleMin, int tailleMax, boolean restHandi) {
		this.nom=nom;
		this.duree = duree;
		this.capacite = capacite;
		this.tailleMin = tailleMin;
		this.tailleMax = tailleMax;
		this.restHandi = restHandi;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
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

	public List<Famille> getQueue() {
		return queue;
	}

	public void setQueue(List<Famille> queue) {
		this.queue = queue;
	}

	

	@Override
	public String toString() {
		return "Attraction [nom=" + nom + ", id=" + id + ", duree=" + duree + ", capacite=" + capacite + ", tailleMin="
				+ tailleMin + ", tailleMax=" + tailleMax + ", restHandi=" + restHandi + ", queue=" + queue + "]";
	}
	
	
	
	
}
