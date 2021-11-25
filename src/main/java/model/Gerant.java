package model;

import java.util.ArrayList;
import javax.persistence.Entity;
import java.util.List;

@Entity
public class Gerant extends Compte{



	private List <Famille> listeFamilleAyantVisite = new ArrayList();
	
	public Gerant(int id, String login, String password) {
		super(id, login, password);
	}
	
	public Gerant() {
	}
	

	public List<Famille> getListeFamilleAyantVisite() {
		return listeFamilleAyantVisite;
	}

	public void setListeFamilleAyantVisite(List<Famille> listeFamilleAyantVisite) {
		this.listeFamilleAyantVisite = listeFamilleAyantVisite;
	}
	

}
