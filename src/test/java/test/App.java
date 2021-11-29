package test;

import model.*;
import util.Context;
import dao.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class App {


	static IDAOFamille daoF = Context.getInstance().getDaoFamille();
	static IDAOAttraction daoA = Context.getInstance().getDaoAttraction();
	static IDAOBoisson daoB = Context.getInstance().getDaoBoisson();
	static IDAOPlat daoP = Context.getInstance().getDaoPlat();
	static IDAOMarchandise daoM = Context.getInstance().getDaoMarchandise();
	static List<Famille> famille = new ArrayList();
	static List<Boisson> boisson = new ArrayList();
	static List <Plat> plat = new ArrayList();
	static List <Attraction> attraction = new ArrayList();
	static Compte connected;
	static IDAOCompte daoC = Context.getInstance().getDaoCompte();
	static LinkedList<Famille> fileAttente = new LinkedList();
	static LinkedList<Famille> fileAttenteFP = new LinkedList();
	static List <Marchandise> marchandise = new ArrayList();
	static boolean fermeture = true;
	
	
	
	EntityManager em = Context.getInstance().getEmf().createEntityManager();
	
	

	public static String saisieString(String msg)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		String valeur= sc.nextLine();
		return valeur;
	}

	public static int saisieInt(String msg)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		int valeur= sc.nextInt();
		return valeur;
	}

	public static double saisieDouble(String msg)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		double valeur= sc.nextDouble();
		return valeur;
	}

	public static boolean saisieBoolean(String msg)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(msg);
		boolean valeur= sc.nextBoolean();
		return valeur;
	}
	
	public static void init() 
	{

		Boisson coca = new Boisson("Coca",2);
		Boisson fanta = new Boisson("Fanta",3);
		daoB.save(coca);
		daoB.save(fanta);
		
		Attraction grand8 = new Attraction("Grand 8", 25, 10, 150, 200, false );
		Attraction asterix = new Attraction("Asterix", 40, 15, 160, 220, false );
		daoA.save(grand8);
		daoA.save(asterix);
		
	}

	public static void main(String[] args) {
		System.out.println("Bienvenue a  FISTILAND!!!!!!!!");
		/*
		init();
		menuPrincipal();
		*/

	}
	public static void menuPrincipal(){
		System.out.println("Appli Parc d'attraction'");
		System.out.println("Choisir un menu : ");
		System.out.println("1 - Connexion");
		System.out.println("2 - Stop" );
		int choix = saisieInt("");
		switch(choix) 
		{
		case 1 : seConnecter();break;
		case 2 : System.exit(0);break;
		}
		menuPrincipal();
	};

	public static void seConnecter(){
		String login = saisieString("Saisir votre login");
		String password = saisieString("Saisir votre password");
		connected = daoC.connect(login, password);
		if(connected instanceof Gerant) 
		{
			menuGerant();
		}
		else if(connected instanceof Operateur) 
		{
			menuOperateur();
		}
		else if(connected instanceof Caissier) 
		{
			menuCaissier();
		}
		else if(connected == null) {System.out.println("Identifiants invalides");
		}
		menuPrincipal();
	}
	public static void menuGerant(){
		System.out.println("----- Menu Gerant -----");
		System.out.println("1 - Acces au menu Gestion Attractions");
		System.out.println("2 - Acces au menu Gestion Boutiques");
		System.out.println("3 - Acces au menu Gestion Restauration");
		System.out.println("4 - Afficher le bilan financier de la journee");
		System.out.println("5 - Afficher la liste de tous les visiteurs");
		System.out.println("6 - Se deconnecter");

		int choix = saisieInt("Choisir un menu : ");
		switch(choix) 
		{
		case 1 : menuGestionAttractions();break;
		case 2 : menuGestionBoutiques();break;
		case 3 : menuRestauration();break;
		case 4 : afficherBilanFinancier();break;
		case 5 : afficherListeVisiteurs();;break;
		case 6 : connected=null;menuPrincipal();break;
		}
	}

	public static void afficherBilanFinancier(){}
	public static void afficherListeVisiteurs(){
		//		for (Famille f : ((Gerant)connected.getListeFamilleAyantVisite).size){}
		//		System.out.println(f);

	}

	public static void menuCaissier(){
		System.out.println("$$$ Menu caissier $$$");
		System.out.println("1-Ajouter une famille dans le parc");
		System.out.println("2-Se deconnecter");

		int choix = saisieInt("Choisir un menu");
		switch(choix)
		{
		case 1 : ajoutFamille();break;
		case 2 : menuPrincipal();break;
		}
	}

	public static void ajoutFamille(){ //Ajout famille BDD liste d'attente
		System.out.println("Accueilir familles et leur demander informations"); 
		int id = saisieInt("Saisir le numero de carte fidelite");

		// Si famille pas dans BDD

		Famille f = daoF.findById(id);
		if (f==null){
			System.out.println("La famille est nouvelle dans le parc");
			System.out.println("+++++ Ajout d'une nouvelle famille dans la file d'attente +++++");
			int nombre = saisieInt("Saisir le nombre des membres de la famille");
			int tailleMin = saisieInt("Saisir la taille minimale ");
			int tailleMax = saisieInt("Saisir la taille maximale");
			int dureeSejour= saisieInt("Indiquer la duree du sejour");
			Boolean handicap = saisieBoolean(" Etes-vous handicapes");
			f = new Famille(id, nombre,tailleMin,tailleMax, dureeSejour, handicap,30);
			daoF.save(f);
			//daoF.insert(f);
		}

		//si la famille est dans la base
		else{
			System.out.println("-----Ajout d'une famille existante dans  la file d'attente-----");
			System.out.println(f);
			f.setDepenses(20);
		}
		fileAttente.add(f);
		System.out.println("La taille de la file d'attente est de "+fileAttente.size());

		for (Attraction a : attraction){
			System.out.println(a.getNom());
		}

		assignementAttraction(f);
		

	}
	
	//il reste une methode ici 
	public static void assignementAttraction(Famille f)
	{
		Random r  = new Random();
		int alea = r.nextInt(attraction.size())+1;
		for (Attraction a : attraction)
		{
			if(a.getId()==alea){
				LinkedList<Famille> newQueue =a.getQueue();
				newQueue.add(f);
				a.setQueue(newQueue);
			}
		}
	}


	public static void afficherFile(){

		System.out.println("La taille de la file d'attente est de "+fileAttente.size());
	}

	public static void menuGestionBoutiques(){
		System.out.println("----- Menu Gestion Boutiques -----");
		System.out.println("1 - Afficher la carte de la boutique");
		System.out.println("2 - Ajouter un produit a  la carte");
		System.out.println("3 - Retirer un produit a  la carte");
		System.out.println("4 - Fermeture des boutiques");
		System.out.println("5 - Se deconnecter");

		int choix = saisieInt("Choisir un menu : ");
		switch(choix) 
		{
		case 1 : afficherCarteBoutique();break;
		case 2 : ajouterProduitBoutique();break;
		case 3 : retirerProduitBoutique();break;
		case 4 : fermetureBoutique();break;
		case 5 : menuGerant(); break;
		}

	}
	/////???///


	public static void fermetureBoutique(){

		Boolean fermeture = saisieBoolean("Voulez-vous fermer les boutiques ? ");    
		List<Boutique> boutiques=new ArrayList();
		for (Boutique b : boutiques)
		{
			if (fermeture == true) {System.out.println("Les boutiques sont fermees");}
			else {System.out.println(" Les boutiques sont fermees");}
		}
	}
	
	public static void afficherCarteBoutique(){

        System.out.println("Voici la liste des marchandises :");
		for (Marchandise m : daoM.findAll()){System.out.println(m+" ("+m.getPrix()+"euros)");}
		//remplacer marchandise par daoM.findAll

		String reponse=saisieString("Retourner au  menu restauration?(y/n)");
		if(reponse.equals("y"))
		{menuGestionBoutiques();}

	}


	public static void ajouterProduitBoutique(){

		String produit = saisieString("Quel type de produit doit etre rajouter? (Boisson/Plat/Marchandise)");
		if (produit.equals("Boisson"))
		{
			System.out.println("Ajout d'une nouvelle boisson : ");
			String nom = saisieString("Nom de la boisson :");
			double prix= saisieDouble("Prix de la boisson :");
			Boisson b = new Boisson(nom,prix);
			//boisson.add(b);
			daoB.save(b);

		}
		else if(produit.equals("Plat"))
		{
			System.out.println("Ajout d'un nouveau plat : ");
			String nom = saisieString("Nom du plat :");
			double prix= saisieDouble("Prix du plat :");
			Plat p = new Plat(nom,prix);
			//plat.add(p);
			daoP.save(p);

		}

		else if (produit.equals("Marchandise"))
		{
			System.out.println("Ajout d'une nouvelle marchandise");
			String nom = saisieString("Nom de la marchandise");
			double prix = saisieDouble("Prix de la marchandise");
			Marchandise m = new Marchandise(prix, nom);
			//marchandise.add(m);
			daoM.save(m);
		}

		else {System.out.println("La saisie est incorrecte");}


	}
	public static void retirerProduitBoutique(){
    String produit = saisieString("Quel type de produit doit etre supprime? (Boisson/Plat/Marchandise)");
		if (produit.equals("Boisson"))
		{
			System.out.println("Suppression d'une boisson : ");

			String bstringsupp = saisieString("Saisir la boisson : ");
			for (Boisson b : daoB.findAll())
			{
				if (b.getNom()==bstringsupp){daoB.delete(b.getId());}
				//if (b.getNom()==bstringsupp){daoB.delete(b)boisson.remove(b);}
				else {System.out.println("Le nom de la boisson est incorrecte");}
			}


		}
		else if(produit.equals("Plat"))
		{
			System.out.println("Suppression d un plat : ");

			String pstringsupp = saisieString("Saisir le plat : ");
			for (Plat p : daoP.findAll())
			{
				if (p.getNom()==pstringsupp){daoP.delete(p.getId());}
				//if (p.getNom()==pstringsupp){plat.remove(p);}
				else {System.out.println("Le nom du plat est incorrect");}
			}
		}
        else if(produit.equals("Marchandise"))
		{
			System.out.println("Suppression d'une marchandise' : ");

			String pstringsupp = saisieString("Saisir la marchandise : ");
			for (Marchandise m : daoM.findAll())
			{
				if (m.getNom()==pstringsupp){daoM.delete(m.getId());}
				else {System.out.println("Le nom de la marchandise est incorrect");}
			}
		}
	}

	public static void menuGestionAttractions(){

		System.out.println("----- Menu Gestion attraction -----");
		System.out.println("1 - Modifier un parametre");
		System.out.println("2 - Ouvrir une attraction");
		System.out.println("3 - Fermer une attraction");
		System.out.println("4 - se drconnecter");

		int choix = saisieInt("Choisir un menu : ");
		switch(choix) 
		{
		case 1 : modifierParametreAtt();break;
		case 2 : utiliserFastPass();break;
		case 3 : ouvrirAttraction();break;
		case 4 : fermerAttraction(); break;
		case 5 : menuGerant(); break;
		}

	}

	public static void modifierParametreAtt() 
	{
		System.out.println("Voici les attractions existantes :");
		for (Attraction a : attraction)
		{
			System.out.println(a);
		}
		int id = saisieInt("Saisir l'id de l'attraction a  modifier");
		System.out.println("Modification de l'attraction ° "+id);
		String nom = saisieString("Saisir le nom");
		int duree = saisieInt("Saisir la duree de l'attraction");
		int capacite = saisieInt("Saisir la capacite maximale");
		int tailleMin = saisieInt("Saisir la taille minimale");
		int tailleMax = saisieInt("Saisir la taille maximale");
		boolean restHandi = saisieBoolean("Y il a t'il des restrictions pour handicapes? oui = 1, non = 0 ");
	
		
		Attraction attraction = new Attraction(nom,duree,capacite,tailleMin,tailleMax,restHandi);
		//Dans l'objet Attraction il faut rajouter un constructeur sans la liste <Famille>
		//mettre ÃƒÂ  jour dans la base
		//daoA.update(attraction);
	}
	//il reste une methode ici
	public static void utiliserFastPass() 
	{
		int id_famille = saisieInt("Saisir l'id de la famille");
		// demander l'id de la famille
		// affecter cette famille dans la file d attente prioritaire
		// faire en sorte de "vider" la liste Fast pass avant l'autre file
	}
	public static void ouvrirAttraction() {

		boolean ouverture = saisieBoolean("Voulez-vous ouvrir les attractions ? ");    
		for (Attraction a : daoA.findAll()) //	static boolean fermeture = false;
		{
			if (fermeture == false) {System.out.println("Les attractions sont fermees");}
			else {System.out.println("Les attractions sont ouvertes");}
		}


	}
	public static void fermerAttraction() {

		Boolean fermeture = saisieBoolean("Voulez-vous fermer les attractions ? ");    
		for (Attraction a : daoA.findAll()) //	static boolean fermeture = false;
		{
			if (fermeture == false) {System.out.println("Les attractions sont ouvertes");}
			else {System.out.println("Les attractions sont fermees");}
		}


	};


	public static void menuOperateur(){

		System.out.println("******* Menu Operateur *******");
		System.out.println("1-Faire un tour d'attraction");
		System.out.println("2-Partir en pause");
		System.out.println("3-Afficher salaire");
		System.out.println("4-Se deconnecter");
		int choix = saisieInt ("Choisir un menu");
		switch(choix)
		{
		case 1 : tourAttraction();break;
		case 2 : pause();break;
		case 3 : afficherSalaire(connected.getId());break;
		case 4 : menuPrincipal();break;

		}

	}
	//il reste des methodes ici
	public static void tourAttraction(){
		int choixId=saisieInt("Choisir l'ID de l'attraction");
		Attraction a = daoA.findById(choixId);
		
	}
	public static void pause(){
		System.out.println("******* Menu Pause *******");
	}
	
	public static void afficherSalaire(int id){
		Compte c = daoC.findById(id);
		System.out.println("Le salaire est" +c.getSalaire());
	}

	//Partie restauration
	public static void menuRestauration()
	{
		if (fermeture==false)
		{
			System.out.println("Menu Restauration ");
			System.out.println("1 - Afficher la carte");
			System.out.println("2 - Passer une commmande");
			System.out.println("3 - Ajouter un produit a  la carte");
			System.out.println("4 - Retirer un produit de la carte");
			System.out.println("5 - Fermeture du restaurant");
			System.out.println("6 - Retour");

			int choix = saisieInt ("Choisir un menu");
			switch(choix)
			{
			case 1 : afficheCarte();break;
			case 2 : passerCom();break;
			case 3 : ajouterProduitRestauration();break;
			case 4 : retirerProduitRestauration();break;
			case 5 : fermeture=true;break;
			case 6 : menuGerant();break;

			}
		}
		else
		{
			System.out.println(" Menu Restauration ¤");
			System.out.println("1 - Afficher la carte");
			System.out.println("2 - Ouverture du restaurant");
			System.out.println("3 - Retour");

			int choix = saisieInt ("Choisir un menu");
			switch(choix)
			{
			case 1 : afficheCarte();break;
			case 2 : fermeture=false;break;
			case 3 : menuGerant();break;
			}
		}
	}

	public static void afficheCarte()
	{
		System.out.println("Voici la liste des boissons :");
		for (Boisson b : daoB.findAll()){System.out.println(b+" ("+b.getPrix()+"euros)");}
		System.out.println("Voici la liste des plats :");
		for (Plat p : daoP.findAll()){System.out.println(p+" ("+p.getPrix()+"euros)");}

		String reponse=saisieString("Retourner au  menu restauration?(y/n)");
		if(reponse.equals("y"))
		{menuRestauration();}
	}
	public static void passerCom()
	{
		int id_famille = saisieInt("Saisir l'id de la famille");
		afficheCarte();
		boolean choix=true;
		Boisson choixBoisson=null;
		Plat choixPlat=null;
		Famille choixFamille=null;
		while (choix==true)
		{
			String reponse=saisieString("Voulez vous une boisson ? (y/n)");
			if(reponse.equals("y"))
			{
				System.out.println("Voici la liste des boissons :");
				for (Boisson b : daoB.findAll()){System.out.println(b+" ("+b.getPrix()+"euros)");}
				String boissonString = saisieString("Saisir votre boisson : "); 

				for (Boisson b : daoB.findAll()){

					if (b.getNom()==boissonString){
						choixBoisson=b;
					}
				}
				for (Famille f : daoF.findAll()){

					if (f.getId()==id_famille){
						choixFamille=f;
					}
				}
				double depenseActuelle=choixFamille.getDepenses();
				choixFamille.setDepenses(depenseActuelle+choixBoisson.getPrix());


			}
			else
			{choix=false;}

		}
		while (choix==true)
		{
			String reponse=saisieString("Voulez vous une plat ? (y/n)");
			if(reponse.equals("y"))
			{
				System.out.println("Voici la liste des plats :");
				for (Plat p : daoP.findAll()){System.out.println(p+" ("+p.getPrix()+"euros)");}
				String platString = saisieString("Saisir votre plat : "); 

				for (Plat p : daoP.findAll()){

					if (p.getNom()==platString){
						choixPlat=p;
					}
				}
				for (Famille f : daoF.findAll()){

					if (f.getId()==id_famille){
						choixFamille=f;
					}
				}
				double depenseActuelle=choixFamille.getDepenses();
				choixFamille.setDepenses(depenseActuelle+choixBoisson.getPrix());


			}
			else
			{choix=false;}

		}
	}
	public static void ajouterProduitRestauration()
	{
		String produit = saisieString("Quel type de produit doit etre rajouter? (Boisson/Plat)");
		if (produit.equals("Boisson"))
		{
			System.out.println("Ajout d'une nouvelle boisson : ");
			String nom = saisieString("Nom de la boisson :");
			double prix= saisieDouble("Prix de la boisson :");
			Boisson b = new Boisson(nom,prix);
			//boisson.add(b);
			daoB.save(b);

		}
		else if(produit.equals("Plat"))
		{
			System.out.println("Ajout d'un nouveau plat : ");
			String nom = saisieString("Nom du plat :");
			double prix= saisieDouble("Prix deu plat :");
			Plat p = new Plat(nom,prix);
			//plat.add(p);
			daoP.save(p);

		}
		else {System.out.println("La saisie est incorrecte");}

	}
	public static void retirerProduitRestauration()
	{
		String produit = saisieString("Quel type de produit doit etre supprimer? (Boisson/Plat)");
		if (produit.equals("Boisson"))
		{
			System.out.println("Suppression d'une boisson : ");

			String bstringsupp = saisieString("Saisir la boisson : ");
			for (Boisson b : daoB.findAll())
			{
				if (b.getNom()==bstringsupp){daoB.delete(b.getId());}
				else {System.out.println("Le nom de la boisson est incorrecte");}
			}


		}
		else if(produit.equals("Plat"))
		{
			System.out.println("Suppression d un plat : ");

			String pstringsupp = saisieString("Saisir le plat : ");
			for (Plat p : daoP.findAll())
			{
				if (p.getNom()==pstringsupp){daoP.delete(p.getId());}
				else {System.out.println("Le nom du plat est incorrect");}
			}
		}
		else {System.out.println("La saisie est incorrecte");}
	}

}
