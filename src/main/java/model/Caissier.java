package model;
import javax.persistence.Entity;

@Entity
public class Caissier extends Compte {
	
	public Caissier(int id, String login, String password) {
		super(id, login, password);
	}
	public Caissier() {
	}
    

}
