package dao;
// T pour le nom de l'objet et K pour le nom de la variable

import java.util.List;

public interface IDAO <T,K> {
	public T findById(K id);
	public List <T> findAll();
	public void update(T o);
	public void insert(T o);
	public void delete(K id);

}
