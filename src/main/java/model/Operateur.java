package model;

import javax.persistence.Entity;

@Entity
public class Operateur extends Compte {

	public Operateur(int id, String login, String password) {
		super(id, login, password);
	}

	public Operateur() {
	}
	
	
	
}
