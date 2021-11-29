package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.IDAOAttraction;
import dao.IDAOCompte;
import dao.IDAOBoisson;
import dao.IDAOFamille;
import dao.IDAOPlat;
import dao.IDAOMarchandise;
import dao.jdbc.*;
import dao.jpa.DAOAttraction;
import dao.jpa.DAOBoisson;
import dao.jpa.DAOCompte;
import dao.jpa.DAOMarchandise;
import dao.jpa.DAOPlat;
import dao.jpa.DAOFamille;
import model.Compte;

public class Context {


	//elements liés à NOTRE PROJET//
	private Compte connected;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetJPA");
	
	private IDAOCompte daoCompte = new DAOCompte();
	private IDAOAttraction daoAttraction = new DAOAttraction();
	private IDAOBoisson daoBoisson = new DAOBoisson();
	private IDAOFamille daoFamille = new DAOFamille();
	private IDAOMarchandise daoMarchandise = new DAOMarchandise(); 
	private IDAOPlat daoPlat = new DAOPlat(); 
	/////
	
	
	
	private static Context singleton=null;

	private Context() {}
	
	//getInstance est la methode permettant de recuper l'objet unique dans l'appi (singleton) 
	public static Context getInstance() 
	{
		if(singleton==null) 
		{
			singleton=new Context();
		}
		return singleton;
	}

	public Compte getConnected() {
		return connected;
	}

	public void setConnected(Compte connected) {
		this.connected = connected;
	}

	public IDAOCompte getDaoCompte() {
		return daoCompte;
	}

	public void setDaoCompte(IDAOCompte daoCompte) {
		this.daoCompte = daoCompte;
	}

	public IDAOAttraction getDaoAttraction() {
		return daoAttraction;
	}

	public void setDaoAttraction(IDAOAttraction daoAttraction) {
		this.daoAttraction = daoAttraction;
	}

	public IDAOBoisson getDaoBoisson() {
		return daoBoisson;
	}

	public void setDaoBoisson(IDAOBoisson daoBoisson) {
		this.daoBoisson = daoBoisson;
	}

	public IDAOFamille getDaoFamille() {
		return daoFamille;
	}

	public void setDaoFamille(IDAOFamille daoFamille) {
		this.daoFamille = daoFamille;
	}

	public IDAOMarchandise getDaoMarchandise() {
		return daoMarchandise;
	}

	public void setDaoMarchandise(IDAOMarchandise daoMarchandise) {
		this.daoMarchandise = daoMarchandise;
	}

	public IDAOPlat getDaoPlat() {
		return daoPlat;
	}

	public void setDaoPlat(IDAOPlat daoPlat) {
		this.daoPlat = daoPlat;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	
	///
	//Getters / Setters liés à NOTRE PROJET
	
	
	
	///
	
	
	
	
	
}
