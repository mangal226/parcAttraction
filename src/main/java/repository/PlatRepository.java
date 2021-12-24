package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Plat;


public interface PlatRepository extends JpaRepository <Plat, Long>{

	List<Plat> findByNom(String nom);

	List<Plat> findByNomContaining(String nom);

}
