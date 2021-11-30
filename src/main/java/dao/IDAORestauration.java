package dao;

import java.util.List;

import model.Restauration;

public interface IDAORestauration extends IDAO<Restauration,Integer>{
	public List<Restauration> inventaireGeneral();
}
