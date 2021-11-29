package model;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import java.util.List;
import javax.persistence.ManyToMany;



@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) 
public class Caissier extends Compte {
	
	public Caissier() {
	}
	
	public Caissier(int id, String login, String password) {
		super(id, login, password);
	}

	@Override
	public String toString() {
		return "Caissier [id=" + id + ", login=" + login + ", password=" + password + ", salaire=" + salaire
				+ ", getId()=" + getId() + ", getLogin()=" + getLogin() + ", getPassword()=" + getPassword()
				+ ", getSalaire()=" + getSalaire() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
    
	

}
