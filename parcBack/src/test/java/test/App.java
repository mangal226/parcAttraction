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


	
	static double bilanFinancier;
	
	static IDAOFamille daoF = Context.getInstance().getDaoFamille();
	static IDAOAttraction daoA = Context.getInstance().getDaoAttraction();
	static IDAOBoisson daoB = Context.getInstance().getDaoBoisson();
	static IDAOPlat daoP = Context.getInstance().getDaoPlat();
	static IDAOMarchandise daoM = Context.getInstance().getDaoMarchandise();
	static List<Famille> famille = new ArrayList();
	static List<Boisson> boisson = new ArrayList();
	static List <Plat> plat = new ArrayList();
	static List <Attraction> attraction = new ArrayList();
	static List <Boutique> boutique = new ArrayList();
	static List <Restauration> restauration = new ArrayList();
	static Compte connected;
	static IDAOCompte daoC = Context.getInstance().getDaoCompte();
	static LinkedList<Famille> fileAttente = new LinkedList();
	/*static LinkedList<Famille> fileAttenteFP = new LinkedList();*/

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

		Compte gerant= new Gerant(1,"ger","ger");
		Compte caissier= new Caissier(2,"cai","cai");
		Compte ope= new Operateur(3,"ope","ope");

		daoC.save(ope);
		daoC.save(gerant);
		daoC.save(caissier);



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
		//init();
		//System.out.println(Context.getInstance().getDaoBoutique().inventaireBoissonBoutique());
		//menuPrincipal();
		creationFamille(5);
		System.out.println("Les familles ont été créés");
		simulation();
		
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
		System.out.println("0 - Simulation");
		System.out.println("1 - Acces au menu Gestion Attractions");
		System.out.println("2 - Acces au menu Gestion Boutiques");
		System.out.println("3 - Acces au menu Gestion Restauration");
		System.out.println("4 - Afficher le bilan financier de la journee");
		System.out.println("5 - Afficher la liste de tous les visiteurs");
		System.out.println("6 - Se deconnecter");

		int choix = saisieInt("Choisir un menu : ");
		switch(choix) 
		{
		case 0 : simulation();break;
		case 1 : menuGestionAttractions();break;
		case 2 : menuGestionBoutiques();break;
		case 3 : menuRestauration();break;
		case 4 : afficherBilanFinancier();break;
		case 5 : afficherListeVisiteurs();;break;
		case 6 : connected=null;menuPrincipal();break;
		}
	}

	public static void simulation(){

		int choixJouer = saisieInt("Choisir le nombre de jours à simuler");
		int choixFamille = saisieInt("Choisir le nombre de famille");

		LinkedList <Double> total = new LinkedList();
		
		int i=1;
		while(i<=choixJouer) {
		List<Famille>listeFamille=ajoutFamilleParc(choixFamille);
		choixAssignation(listeFamille); // Boutique ou attraction ?
		//System.out.println("je sors de choix Assignation");
		avancementJournee();
		//System.out.println("je sors d'avancement journée'");
		bilanSimulation();
		System.out.println("bilan de la journée "+i+" :" + bilanFinancier);
		
		//ajout du bilanFinancier dans une liste et réinitialisation du bilanFinancier pour la journee suivante
		total.add(bilanFinancier); 
		bilanFinancier=0;  
		
		//Réinitialisation du temps de sejour des familles pour la journee suivante
		listeFamille=daoF.findAll();
		for(Famille f : listeFamille)
		{
			//f.setDepenses(0);
			//f.setDureeSejour(40);
			daoF.save(f);
		}
		i++;
		}
		for(double t : total) {
			System.out.println(t);
		}
	}
	
	public static List <Famille> creationFamille(int nombreFamilles){
		List <Famille> familles=new ArrayList();
		Random r=new Random();
		for (int i=0; i<nombreFamilles; i++) {
			double depenses=0;
			int tailleMin, tailleMax, nombre, dureeSejour=40;
			Boolean  handicap;
			nombre=r.nextInt(10)+1;
			tailleMin=r.nextInt(140-120+1)+120;
			tailleMax=r.nextInt(190-165+1)+165;
			handicap=r.nextBoolean();
			
			Famille f=new Famille(nombre,tailleMin,tailleMax,dureeSejour,handicap,depenses);
			daoF.save(f);
	
		}
		return familles;
	}

	public static List<Famille> ajoutFamilleParc(int choixFamille)// Recupere famille dans BDD
	{
		List<Famille> familles=daoF.findAll();
		List <Famille> famillesAss=new ArrayList();
		int tableAlea []=new int[choixFamille], exist=0, i=0;
		Random r  = new Random();

		while (i<choixFamille){
			int aleaF = r.nextInt(familles.size());
			for(int j=0; j<i; j++) {
				if (aleaF==tableAlea[j]){
					exist=1;}}
			if(exist==0) {
			tableAlea[i]=aleaF;
			//tableAlea[i]=i;
			i++;}
			exist=0;
			}

		for (int k=0; k<choixFamille; k++){

			double depenses =familles.get(tableAlea[k]).getDepenses();
			depenses+=30*familles.get(tableAlea[k]).getNombre();
			bilanFinancier+=depenses;
			familles.get(tableAlea[k]).setDepenses(depenses);
			famillesAss.add(familles.get(tableAlea[k]));}

		return famillesAss;
	}

	public static void choixAssignation(List<Famille> listeFamille){

		//System.out.println("voici la liste des familles dans le parc"+listeFamille);
		Random r  = new Random();
		for (Famille f : listeFamille){

			int alea = r.nextInt(10);
			if (alea <=6){//70% de chance de rentrer dans une attraction

				assignementAttraction(f);
			}
			else {
				achatBoutiqueRestauration(f);
			}
		}
	}

	public static void assignementAttraction(Famille f) //choisir l'attraction de la famille
	{
		Random r  = new Random();
		List<Attraction> listeAttraction=daoA.findAll();
		int alea = r.nextInt(listeAttraction.size());
		for (int i = 0; i<listeAttraction.size();i++)
		{
			Attraction a = listeAttraction.get(i);
			//System.out.println(i+"------"+alea);
			if(i==alea){
				
				//System.out.println(alea+ "-???");
				
				//System.out.println("l'attraction dans laquelle je vais être enregistré : "+a);
				List<Famille> newQueue =a.getQueue();		

				newQueue.add(f);
				a.setQueue(newQueue);
				
				daoF.save(f); // ? pourquoi save la famille? Est ce qu'on ajoute pas la même famille encore dans la bdd?
				daoA.save(a);
				//System.out.println("l'attraction dans laquelle j'ai été enregistré : "+a);
				//System.out.println(daoA.findById(a.getId()));
				/*for (Attraction b : daoA.findAll())
				{
					//System.out.println(b);
				}*/
			}
		}
		//System.out.println("-----------------");
		//System.out.println(listeAttraction);
		//System.out.println("-----------------");
	}


	public static void achatBoutiqueRestauration(Famille f)
	{

		//System.out.println("je rentre dans la boutique");
		Random r = new Random();
		int i=1;

		List<Boisson> listeBoisson= daoB.findAll();  
		List<Plat> listePlat= daoP.findAll();  
		List<Marchandise> listeMarchandise= daoM.findAll();  
		//Revoir la fonction pour les achats peut être affiner 
		while(i<=f.getNombre()){
		for (Boisson b : listeBoisson){
			int alea = r.nextInt(10)+1;
			if (alea<=2) {
				double depensesActuelles=f.getDepenses();
				f.setDepenses(depensesActuelles+b.getPrix());
				bilanFinancier+=b.getPrix();
			}
		}
		for (Plat p : listePlat){
			int alea = r.nextInt(10)+1;
			if (alea<=2) {
				double depensesActuelles=f.getDepenses();
				f.setDepenses(depensesActuelles+p.getPrix());
				bilanFinancier+=p.getPrix();
			}
		}
		for (Marchandise m : listeMarchandise){
			int alea = r.nextInt(10)+1;
			if (alea<=2) {
				double depensesActuelles=f.getDepenses();
				f.setDepenses(depensesActuelles+m.getPrix());
				bilanFinancier+=m.getPrix();
			}
		}
		i++;
		}
		int dureSejour = f.getDureeSejour();
		dureSejour-=1;
		f.setDureeSejour(dureSejour);
		//System.out.println(f);
		daoF.save(f);
		if (f.getDureeSejour() >0) {
			//System.out.println("je sors de la boutique et je vais dans l'attraction");
			assignementAttraction(f);
		}
		else {
			
			daoF.save(f);	//Pourquoi save la famille?
		}



	}


	public static void avancementJournee() // Continue tant que les familles ne sont pas parties
	//permet de faire diminuer le temps qu il reste aux familles

	{ 
		//System.out.println("je suis dans avancement journée");
		for (Attraction a : daoA.findAll())
		{
			//System.out.println(a);
			List<Famille> embarque= new ArrayList();
			a=daoA.findById(a.getId());// ? utilité??
			int capaciteActuelle=a.getCapacite();
			while (a.getQueue().isEmpty()==false)
			{
				a=daoA.findById(a.getId());
				//System.out.println("je rentre dans l'attraction : "+a);
				Famille famille=(a.getQueue()).get(0);
				
				if(famille.getNombre()<=capaciteActuelle)//ajout de la famille
				{
					a.getQueue().remove(0);
					daoA.save(a);
					capaciteActuelle-=famille.getNombre();

					embarque.add(famille);

					int dureeSejour=famille.getDureeSejour();
					//System.out.println(famille);
					//System.out.println("la durée de l'attraction est de "+a.getDuree());
					dureeSejour-=a.getDuree();

					famille.setDureeSejour(dureeSejour);

					if(famille.getDureeSejour()>0)
					{
						List<Famille> listeFamille = new ArrayList();  //Pourquoi creer une liste?
						listeFamille.add(famille);
						//System.out.println("il me reste du temps, je vais à la boutique 1");
						choixAssignation(listeFamille);
					}
					else
					{	


						daoF.save(famille);
					}
				}

				else if (famille.getNombre()>capaciteActuelle)
				{
					for (Famille f : a.getQueue()) {
						
						//System.out.println("J'attends dans la queue");	// Ajouter un timer pour diminuer le temps de la famille
						int dureeSejour=f.getDureeSejour();
						dureeSejour-=a.getDuree();
						f.setDureeSejour(dureeSejour);
						capaciteActuelle=a.getCapacite();

					}

					daoF.save(famille);
					if(famille.getDureeSejour()<0)
					{
						daoF.save(famille);
						a.getQueue().remove(famille);
						daoA.save(a);
					}
					embarque.clear();
				}

				else if(a.getQueue().isEmpty()==true && embarque.isEmpty()==false)
				{
					int duree=famille.getDureeSejour()-a.getDuree();
					famille.setDureeSejour(duree);
					if(famille.getDureeSejour()<0)
					{
						daoF.save(famille);
					}
					else {
						List<Famille> listeFamille = new ArrayList();
						listeFamille.add(famille);
						//System.out.println("il me reste du temps, je vais à la boutique 2");
						choixAssignation(listeFamille);
					}
					embarque.clear();
				}
				
			}
		}
		for (Attraction b : daoA.findAll())
		{
			if (b.getQueue().isEmpty()==false){avancementJournee();}
			
		}
	}
				
	public static void bilanSimulation() {};


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
		//case 1 : afficherCarteBoutique();break;
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
/*
	public static void afficherCarteBoutique(){
		List<Boutique> boutique =showJoinFetchNoDoublons();
		for (Boutique b : boutique)
		System.out.println("Voici l'inventaire :");
		/*for (Marchandise m : daoM.findAll()){System.out.println(m+" ("+m.getPrix()+"euros)");}
		//remplacer marchandise par daoM.findAll

		String reponse=saisieString("Retourner au  menu restauration?(y/n)");
		if(reponse.equals("y"))
		{menuGestionBoutiques();}

	}

public static void afficherCarteRestauration(){
		List<Restauration> restauration =showJoinFetchNoDoublons();
		for (Restauration r : restauration)
		System.out.println("Voici la liste des marchandises :");
		/*for (Marchandise m : daoM.findAll()){System.out.println(m+" ("+m.getPrix()+"euros)");}
		//remplacer marchandise par daoM.findAll

		String reponse=saisieString("Retourner au  menu restauration?(y/n)");
		if(reponse.equals("y"))
		{menuGestionBoutiques();}

	}

	private static List<Restauration> showJoinFetchNoDoublons() {
	
	return null;
}

*/

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

		List<Attraction> listeAttraction = daoA.findAll();

		System.out.println("Voici les attractions existantes :");
		for (Attraction a : listeAttraction)
		{
			System.out.println(a);
		}


		System.out.println("----- Menu Gestion attraction -----");
		System.out.println("1 - Ajouter une attraction");
		System.out.println("2 - Modifier un parametre");
		System.out.println("3 - Ouvrir une attraction");
		System.out.println("4 - Fermer une attraction");
		System.out.println("5 - se déconnecter");

		int choix = saisieInt("Choisir un menu : ");
		switch(choix) 
		{
		case 1 : ajoutAttraction();
		case 2 : modifierParametreAtt();break;
		case 3 : ouvrirAttraction();break;
		case 4 : fermerAttraction(); break;
		case 5 : menuGerant(); break;
		}

	}
	public static void ajoutAttraction() {

		System.out.println("Voici les attractions existantes :");
		for (Attraction a : daoA.findAll())
		{
			System.out.println(a);
		}
		String nom = saisieString("Saisir le nom");
		int duree = saisieInt("Saisir la duree de l'attraction");
		int capacite = saisieInt("Saisir la capacite maximale");
		int tailleMin = saisieInt("Saisir la taille minimale");
		int tailleMax = saisieInt("Saisir la taille maximale");
		boolean restHandi = saisieBoolean("Y a t'il des restrictions pour handicapes? oui = true, non = false ");


		Attraction attraction = new Attraction(nom,duree,capacite,tailleMin,tailleMax,restHandi);

		daoA.save(attraction);


	}
	public static void modifierParametreAtt() 
	{
		System.out.println("Voici les attractions existantes :");
		for (Attraction a : daoA.findAll())
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


//	Context.getInstance().getEmf().close();

} 

