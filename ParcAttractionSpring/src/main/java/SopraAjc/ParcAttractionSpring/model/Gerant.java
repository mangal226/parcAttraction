package SopraAjc.ParcAttractionSpring.model;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import java.util.List;

@Entity
public class Gerant extends Compte{

	
	public Gerant(Long id, String login, String password) {
		super(id, login, password);
	}
	
	public Gerant() {
	}
	

	

}
