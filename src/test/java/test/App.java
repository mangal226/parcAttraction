package test;

import model.*;
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


public class App {


	static DAOFamille daoF = new DAOFamille();
	static List<Famille> famille = new ArrayList();
	static List<Boisson> boisson = new ArrayList();
	static List <Plat> plat = new ArrayList();
	static List <Attraction> attraction = new ArrayList();
	static Compte connected;
	static DAOCompte daoC = new DAOCompte();
	static LinkedList<Famille> fileAttente = new LinkedList();
	static LinkedList<Famille> fileAttenteFP = new LinkedList();
	static List <Marchandise> marchandise = new ArrayList();
	static boolean fermeture = true;

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
		boisson.add(coca);
		boisson.add(fanta);
		
		Attraction grand8 = new Attraction(1,"Grand 8", 25, 10, 150, 200, false );
		Attraction asterix = new Attraction(2,"Asterix", 40, 15, 160, 220, false );
		attraction.add(grand8);
		attraction.add(asterix);
		
	}

	public static void main(String[] args) {
		System.out.println("Bienvenue Ã  FISTILAND!!!!!!!!");
		init();
		menuPrincipal();

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
		System.out.println("1 - AccÃ¨s au menu Gestion Attractions");
		System.out.println("2 - AccÃ¨s au menu Gestion Boutiques");
		System.out.println("3 - AccÃ¨s au menu Gestion Restauration");
		System.out.println("4 - Afficher le bilan financier de la journÃ©e");
		System.out.println("5 - Afficher la liste de tous les visiteurs");
		System.out.println("6 - Se dÃ©connecter");

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
		System.out.println("2-Se dÃ©connecter");

		int choix = saisieInt("Choisir un menu");
		switch(choix)
		{
		case 1 : ajoutFamille();break;
		case 2 : menuPrincipal();break;
		}
	}

	public static void ajoutFamille(){ //Ajout famille BDD liste d'attente
		System.out.println("Accueilir familles et leur demander informations"); 
		int id = saisieInt("Saisir le numÃ©ro de carte fidÃ©litÃ©");

		// Si famille pas dans BDD

		Famille f = daoF.findById(id);
		if (f==null){
			System.out.println("La famille est nouvelle dans le parc");
			System.out.println("+++++ Ajout d'une nouvelle famille Ã  la file d'attente +++++");
			int nombre = saisieInt("Saisir le nombre des membres de la famille");
			int tailleMin = saisieInt("ðŸ‘¨â€� ðŸ¦²Saisir la taille minimale ðŸ‘¨â€�ðŸ¦²");
			int tailleMax = saisieInt("ðŸ§’ Saisir la taille maximale ðŸ§’");
			int dureeSejour= saisieInt("ðŸŽ¢Indiquer la durÃ©e du sÃ©jourðŸŽ¢");
			Boolean handicap = saisieBoolean("ðŸ‘©â€�ðŸ¦¼ Etes-vous handicapÃ© ðŸ‘©â€�ðŸ¦¼?");
			f = new Famille(nombre,tailleMin,tailleMax, dureeSejour, handicap,30);
			daoF.insert(f);
		}

		//si la famille est dans la base
		else{
			System.out.println("-----Ajout d'une famille existante Ã  la file d'attente-----");
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
	
	il reste une methode ici 
	public static void assignementAttraction(Famille f)
	{
		Random r  = new Random();
		int alea = r.nextInt(attraction.size())+1;
		for (Attraction a : attraction)
		{
			if(a.getId()==alea){
				List<Famille> newQueue =a.getQueue();
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
		System.out.println("2 - Ajouter un produit Ã  la carte");
		System.out.println("3 - Retirer un produit Ã  la carte");
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
			if (fermeture == true) {System.out.println("ðŸ˜�Les boutiques sont fermÃ©esðŸ˜�");}
			else {System.out.println(" ðŸ˜´Les boutiques sont fermÃ©esðŸ˜´");}
		}
	}
	
	public static void afficherCarteBoutique(){

        System.out.println("Voici la liste des marchandises :");
		for (Marchandise m : marchandise){System.out.println(m+" ("+m.getPrix()+"â‚¬)");}
		

		String reponse=saisieString("Retourner au  menu restauration?(y/n)");
		if(reponse.equals("y"))
		{menuGestionBoutiques();}

	}


	public static void ajouterProduitBoutique(){

		String produit = saisieString("Quel type de produit doit Ãªtre rajouter? (Boisson/Plat/Marchandise)");
		if (produit.equals("Boisson"))
		{
			System.out.println("Ajout d'une nouvelle boisson : ");
			String nom = saisieString("Nom de la boisson :");
			double prix= saisieDouble("Prix de la boisson :");
			Boisson b = new Boisson(nom,prix);
			boisson.add(b);

		}
		else if(produit.equals("Plat"))
		{
			System.out.println("Ajout d'un nouveau plat : ");
			String nom = saisieString("Nom du plat :");
			double prix= saisieDouble("Prix du plat :");
			Plat p = new Plat(nom,prix);
			plat.add(p);

		}

		else if (produit.equals("Marchandise"))
		{
			System.out.println("Ajout d'une nouvelle marchandise");
			String nom = saisieString("Nom de la marchandise");
			double prix = saisieDouble("ðŸ¤‘ðŸ¤‘ Prix de la marchandise ðŸ¤‘ðŸ¤‘");
			Marchandise m = new Marchandise(prix, nom);
			marchandise.add(m);
		}

		else {System.out.println("La saisie est incorrecte");}


	}
	public static void retirerProduitBoutique(){
    String produit = saisieString("Quel type de produit doit Ãªtre supprimÃ©? (Boisson/Plat/Marchandise)");
		if (produit.equals("Boisson"))
		{
			System.out.println("Suppression d'une boisson : ");

			String bstringsupp = saisieString("Saisir la boisson : ");
			for (Boisson b : boisson)
			{
				if (b.getNom()==bstringsupp){boisson.remove(b);}
				else {System.out.println("Le nom de la boisson est incorrecte");}
			}


		}
		else if(produit.equals("Plat"))
		{
			System.out.println("Suppression d un plat : ");

			String pstringsupp = saisieString("Saisir le plat : ");
			for (Plat p : plat)
			{
				if (p.getNom()==pstringsupp){plat.remove(p);}
				else {System.out.println("Le nom du plat est incorrect");}
			}
		}
        else if(produit.equals("Marchandise"))
		{
			System.out.println("Suppression d'une marchandise' : ");

			String pstringsupp = saisieString("Saisir la marchandise : ");
			for (Marchandise m : marchandise)
			{
				if (m.getNom()==pstringsupp){marchandise.remove(m);}
				else {System.out.println("Le nom de la marchandise est incorrect");}
			}
		}
	}

	public static void menuGestionAttractions(){

		System.out.println("----- Menu Gestion attraction -----");
		System.out.println("1 - Modifier un paramÃ¨tre");
		System.out.println("2 - Ouvrir une attraction");
		System.out.println("3 - Fermer une attraction");
		System.out.println("4 - se dÃ©connecter");

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
		int id = saisieInt("Saisir l'id de l'attraction Ã  modifier");
		System.out.println("Modification de l'attraction nÂ° "+id);
		String nom = saisieString("Saisir le nom");
		int duree = saisieInt("Saisir la duree de l'attraction");
		int capacite = saisieInt("Saisir la capacite maximale");
		int tailleMin = saisieInt("Saisir la taille minimale");
		int tailleMax = saisieInt("Saisir la taille maximale");
		boolean restHandi = saisieBoolean("Y il a t'il des restrictions pour handicapÃ©? oui = 1, non = 0 ");
	
		
		Attraction attraction = new Attraction(nom,duree,capacite,tailleMin,tailleMax,restHandi);
		//Dans l'objet Attraction il faut rajouter un constructeur sans la liste <Famille>
		//mettre Ã  jour dans la base
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
		for (Attraction a : attraction) //	static boolean fermeture = false;
		{
			if (fermeture == false) {System.out.println("Les attractions sont fermÃ©es");}
			else {System.out.println("Les attractions sont ouvertes");}
		}


	}
	public static void fermerAttraction() {

		Boolean fermeture = saisieBoolean("Voulez-vous fermer les attractions ? ");    
		for (Attraction a : attraction) //	static boolean fermeture = false;
		{
			if (fermeture == false) {System.out.println("Les attractions sont ouvertes");}
			else {System.out.println("Les attractions sont fermÃ©es");}
		}


	};


	public static void menuOperateur(){

		System.out.println("******* Menu OpÃ©rateur *******");
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
	il reste des methodes ici
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
			System.out.println("ðŸ¤¤ðŸ¤¤ Menu Restauration ðŸ¤¤ðŸ¤¤");
			System.out.println("1 - Afficher la carte");
			System.out.println("2 - Passer une commmande");
			System.out.println("3 - Ajouter un produit Ã  la carte");
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
			System.out.println("ðŸ¤¤ðŸ¤¤ Menu Restauration ðŸ¤¤ðŸ¤¤");
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
		for (Boisson b : boisson){System.out.println(b+" ("+b.getPrix()+"â‚¬)");}
		System.out.println("Voici la liste des plats :");
		for (Plat p : plat){System.out.println(p+" ("+p.getPrix()+"â‚¬)");}

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
		Famille choixFamille=null;
		while (choix==true)
		{
			String reponse=saisieString("Voulez vous une boisson ? (y/n)");
			if(reponse.equals("y"))
			{
				System.out.println("Voici la liste des boissons :");
				for (Boisson b : boisson){System.out.println(b+" ("+b.getPrix()+"â‚¬)");}
				String boissonString = saisieString("Saisir votre boisson : "); 

				for (Boisson b : boisson){

					if (b.getNom()==boissonString){
						choixBoisson=b;
					}
				}
				for (Famille f : famille){

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
		String produit = saisieString("Quel type de produit doit Ãªtre rajouter? (Boisson/Plat)");
		if (produit.equals("Boisson"))
		{
			System.out.println("Ajout d'une nouvelle boisson : ");
			String nom = saisieString("Nom de la boisson :");
			double prix= saisieDouble("Prix de la boisson :");
			Boisson b = new Boisson(nom,prix);
			boisson.add(b);

		}
		else if(produit.equals("Plat"))
		{
			System.out.println("Ajout d'un nouveau plat : ");
			String nom = saisieString("Nom du plat :");
			double prix= saisieDouble("Prix deu plat :");
			Plat p = new Plat(nom,prix);
			plat.add(p);

		}
		else {System.out.println("La saisie est incorrecte");}

	}
	public static void retirerProduitRestauration()
	{
		String produit = saisieString("Quel type de produit doit Ãªtre supprimer? (Boisson/Plat)");
		if (produit.equals("Boisson"))
		{
			System.out.println("Suppression d'une boisson : ");

			String bstringsupp = saisieString("Saisir la boisson : ");
			for (Boisson b : boisson)
			{
				if (b.getNom()==bstringsupp){boisson.remove(b);}
				else {System.out.println("Le nom de la boisson est incorrecte");}
			}


		}
		else if(produit.equals("Plat"))
		{
			System.out.println("Suppression d un plat : ");

			String pstringsupp = saisieString("Saisir le plat : ");
			for (Plat p : plat)
			{
				if (p.getNom()==pstringsupp){plat.remove(p);}
				else {System.out.println("Le nom du plat est incorrect");}
			}


		}
		else {System.out.println("La saisie est incorrecte");}
	}

}
