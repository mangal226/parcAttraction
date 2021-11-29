package model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_personne")
public class Compte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	protected String login;
	protected String password;
	protected double salaire;


	public Compte() {
	}
	
	public Compte(int id, String login, String password, double salaire) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.salaire = salaire;
	}




	public Compte(int id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
	}




	public int getId() {
	return id;
	}
	public void setId(int id) {
	this.id = id;
	}
	public String getLogin() {
	return login;
	}
public void setLogin(String login) {
	this.login = login;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public double getSalaire() {
	return salaire;
}
public void setSalaire(double salaire) {
	this.salaire = salaire;
}


  
  
}
