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
	private IDAOCompte daoCompte = new DAOCompte();
	private IDAOAttraction daoAttraction = new DAOAttraction();
	private IDAOBoisson daoBoisson = new DAOBoisson();
	private IDAOFamille daoFamille = new DAOFamille();
	private IDAOMarchandise daoMarchandise = new DAOMarchandise(); 
	private IDAOPlat daoPlat = new DAOPlat(); 
	/////
	
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("voyage");
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

	public IDAOAttraction getDaoPlanete() {
		return daoPlanete;
	}

	public void setDaoPlanete(IDAOAttraction daoPlanete) {
		this.daoPlanete = daoPlanete;
	}

	public IDAOBoisson getDaoTrajet() {
		return daoTrajet;
	}

	public void setDaoTrajet(IDAOBoisson daoTrajet) {
		this.daoTrajet = daoTrajet;
	}

	public IDAOFamille getDaoVaisseau() {
		return daoVaisseau;
	}

	public void setDaoVaisseau(IDAOFamille daoVaisseau) {
		this.daoVaisseau = daoVaisseau;
	}

	public IDAOMarchandise getDaoPassager() {
		return daoPassager;
	}

	public void setDaoPassager(IDAOMarchandise daoPassager) {
		this.daoPassager = daoPassager;
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
