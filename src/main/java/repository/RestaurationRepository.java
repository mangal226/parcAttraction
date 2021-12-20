package repository;

import model.Restauration;

public interface RestaurationRepository extends JpaRepository <Restauration, Long>{

	
	
	@Query("select distinct resto from Restauration resto left join fetch resto.plat left join fetch resto.boisson where resto.id=:id")
	Optional<Restauration> findByIdWithPlatAndBoisson(@Param("id") Long id);
}
