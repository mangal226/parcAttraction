package repository;

import java.util.Optional;

import model.Famille;
import java.util.List;



public interface FamilleRepository extends JpaRepository <Famille, Long>{
	
	Optional <Famille> findById(Long Id);
	List <Famille> findByAll(Long Id);

}
