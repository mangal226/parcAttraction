package SopraAjc.ParcAttractionSpring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import SopraAjc.ParcAttractionSpring.model.Boutique;




public interface BoutiqueRepository extends JpaRepository<Boutique, Long> {
	
	
	@Query("select distinct boutique from Boutique boutique left join fetch boutique.enVente as enVente where boutique.id=:id")
	Optional<Boutique> findById(@Param("id") Long id);
	
	
	
	
}
