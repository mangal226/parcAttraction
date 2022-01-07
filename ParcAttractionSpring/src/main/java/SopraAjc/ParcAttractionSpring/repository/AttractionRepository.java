package SopraAjc.ParcAttractionSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import SopraAjc.ParcAttractionSpring.model.Attraction;

public interface AttractionRepository extends JpaRepository <Attraction, Long>{
	
}
