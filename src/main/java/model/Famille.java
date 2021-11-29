package model;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.*;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Famille {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int nombre, tailleMin, tailleMax, dureeSejour;
	private boolean handicap;
	private double depenses=0;
	
	
	public Famille() {
	
	}
	
	
	public Famille(int id,int nombre, int tailleMin, int tailleMax, int dureeSejour, boolean handicap,
			double depenses) {
		this.id=id;
		this.nombre = nombre;
		this.tailleMin = tailleMin;
		this.tailleMax = tailleMax;
		this.dureeSejour = dureeSejour;
		this.handicap = handicap;
		this.depenses = depenses;
	}
	
	public Famille(int nombre, int tailleMin, int tailleMax, int dureeSejour, boolean handicap,
			double depenses) {
		this.nombre = nombre;
		this.tailleMin = tailleMin;
		this.tailleMax = tailleMax;
		this.dureeSejour = dureeSejour;
		this.handicap = handicap;
		this.depenses = depenses;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
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

	public int getDureeSejour() {
		return dureeSejour;
	}

	public void setDureeSejour(int dureeSejour) {
		this.dureeSejour = dureeSejour;
	}

	public boolean isHandicap() {
		return handicap;
	}

	public void setHandicap(boolean handicap) {
		this.handicap = handicap;
	}
	public double getDepenses() {
		return depenses;
	}

	public void setDepenses(double depenses) {
		this.depenses = depenses;
	}

	public int getId() {
		return id;
	}

	public void setId_Famille(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Famille [id=" + id + ", nombre=" + nombre + ", tailleMin=" + tailleMin + ", tailleMax=" + tailleMax
				+ ", dureeSejour=" + dureeSejour + ", handicap=" + handicap + ", depenses=" + depenses + "]";
	}
	
	
}
