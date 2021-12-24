package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Attraction;

public interface AttractionRepository extends JpaRepository <Attraction, Long>{
	
}
