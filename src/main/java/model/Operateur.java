package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("operateur")
public class Operateur extends Compte {

	public Operateur(int id, String login, String password) {
		super(id, login, password);
	}

	public Operateur() {
	}
	
	
	
}
