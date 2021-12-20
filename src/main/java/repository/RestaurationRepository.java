package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Restauration;

public interface RestaurationRepository extends JpaRepository <Restauration, Long>{

	
	
	@Query("select distinct resto from Restauration resto left join fetch resto.plat left join fetch resto.boisson where resto.id=:id")
	Optional<Restauration> findByIdWithPlatAndBoisson(@Param("id") Long id);
}
