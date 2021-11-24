package model;

import java.util.ArrayList;
import java.util.List;

public class Gerant extends Compte{



	private List <Famille> listeFamilleAyantVisite = new ArrayList();
	
	public Gerant(int id, String login, String password) {
		super(id, login, password);
	}

	public List<Famille> getListeFamilleAyantVisite() {
		return listeFamilleAyantVisite;
	}

	public void setListeFamilleAyantVisite(List<Famille> listeFamilleAyantVisite) {
		this.listeFamilleAyantVisite = listeFamilleAyantVisite;
	}
	

}
