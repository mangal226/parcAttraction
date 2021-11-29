package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("operateur")
public class Operateur extends Compte {
	
public Operateur() {
	
}

	public Operateur(int id, String login, String password) {
		
	}
	
	
}