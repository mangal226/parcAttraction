package service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import model.Attraction;
import model.Boisson;
import model.Famille;
import model.Marchandise;
import model.Plat;

public class SimulationService {
	
	
	
	
	public static void simulation(){

//		int choixJouer = saisieInt("Choisir le nombre de jours à simuler");
//		int choixFamille = saisieInt("Choisir le nombre de famille");

		LinkedList <Double> total = new LinkedList();
		
		int i=1;
		while(i<=choixJouer) {
		List<Famille>listeFamille=ajoutFamilleParc(choixFamille);
		choixAssignation(listeFamille); // Boutique ou attraction ?
		avancementJournee();
		bilanSimulation();
//		System.out.println("bilan de la journée "+i+" :" + bilanFinancier);
		
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
//		for(double t : total) {
//			System.out.println(t);
//		}
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
				
				daoF.save(f);
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
			
			daoF.save(f);
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
			a=daoA.findById(a.getId());
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
						List<Famille> listeFamille = new ArrayList();
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
						
						//System.out.println("J'attends dans la queue");
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

}
