package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Attraction;

public interface AttractionRepository extends JpaRepository <Attraction, Long>{
	
	Optional<Attraction> findByNom(String nom);
	
}
