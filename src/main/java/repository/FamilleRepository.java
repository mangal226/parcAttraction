package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Famille;



public interface FamilleRepository extends JpaRepository <Famille, Long>{
	

}
