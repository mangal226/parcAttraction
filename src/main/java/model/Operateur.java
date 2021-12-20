package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("operateur")
public class Operateur extends Compte {
	
public Operateur() {
	
}

	public Operateur(Long id, String login, String password) {
		
	}
	
	
}