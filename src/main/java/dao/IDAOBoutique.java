package dao;

import java.util.List;

import model.Boutique;

public interface IDAOBoutique extends IDAO<Boutique,Integer>{

	public List<Boutique> inventaireBoissonBoutique();
}
