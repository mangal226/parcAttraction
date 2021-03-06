package SopraAjc.ParcAttractionSpring.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

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
	private String description;
	@JsonView(JsonViews.Common.class)
	private int duree, capacite;
	@JsonView(JsonViews.Common.class)
	@Column(name="taille_min")
	private int tailleMin;
	@JsonView(JsonViews.Common.class)
	@Column(name="taille_max")
	private int tailleMax;
	@JsonView(JsonViews.Common.class)
	@Column(name="rest_handi")
	private boolean restHandi;
	@JsonView(JsonViews.Common.class)
	@Column(name="nbr_visiteur")
	private int nbrVisiteur=0;
	@JsonView(JsonViews.Common.class)
	@Embedded
	private Coordonnees coordonnees;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Famille> queue=new ArrayList();
	
	public Attraction() {
	
	}
	
	
	public Attraction(Long id, String nom, String description, int duree, int capacite, int tailleMin, int tailleMax, boolean restHandi, Coordonnees coordonnees) {
		super();
		this.id = id;
		this.nom = nom;
		this.description=description;
		this.duree = duree;
		this.capacite = capacite;
		this.tailleMin = tailleMin;
		this.tailleMax = tailleMax;
		this.restHandi = restHandi;
		this.coordonnees = coordonnees;
	}


	public Attraction(String nom, int duree, int capacite, int tailleMin, int tailleMax, boolean restHandi, Coordonnees coordonnees) {
		this.nom = nom;
		this.duree = duree;
		this.capacite = capacite;
		this.tailleMin = tailleMin;
		this.tailleMax = tailleMax;
		this.restHandi = restHandi;
		this.coordonnees = coordonnees;
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
	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public List<Famille> getQueue() {
		return queue;
	}

	public void setQueue(List<Famille> queue) {
		this.queue = queue;
	}

	

	public int getNbrVisiteur() {
		return nbrVisiteur;
	}

	public void setNbrVisiteur(int nbrVisiteur) {
		this.nbrVisiteur = nbrVisiteur;
	}
	

	public Coordonnees getCoordonnees() {
		return coordonnees;
	}


	public void setCoordonnees(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
	}

	@Override
	public String toString() {
		return "Attraction [id=" + id + ", nom=" + nom + ", duree=" + duree + ", capacite=" + capacite + ", tailleMin="
				+ tailleMin + ", tailleMax=" + tailleMax + ", restHandi=" + restHandi + ", nbrVisiteur=" + nbrVisiteur
				+ ", queue=" + queue + "]";
	}



	
	
	
	
	
}
