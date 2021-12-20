package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;



import model.Boutique;
import model.Compte;



public interface CompteRepository extends JpaRepository<Compte, Long> {
	
	
	
//	@Query("select from Compte c where c.login=:log and c.password=:pass")
//	Optional<Compte> connect(@Param("log") String log, @Param("pass") String pass);
}
