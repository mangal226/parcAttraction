package dao;

import model.Compte;

public interface IDAOCompte extends IDAO<Compte,Integer> {

	public Compte connect(String login,String password);
	
}
