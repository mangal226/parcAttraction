package SopraAjc.ParcAttractionSpring.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonView;

@Embeddable
public class Coordonnees {
	
	@JsonView(JsonViews.Common.class)
	@Min(value=0)
	@Max(value=20)
	private int x;
	
	@JsonView(JsonViews.Common.class)
	@Min(value=0)
	@Max(value=20)
	private int y;
	
	
	public Coordonnees(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordonnees() {}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "Coordonnees [x=" + x + ", y=" + y + "]";
	}
	
	
	
	

}
