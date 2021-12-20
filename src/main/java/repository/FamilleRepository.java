package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Famille;
import java.util.List;



public interface FamilleRepository extends JpaRepository <Famille, Long>{
	

}
