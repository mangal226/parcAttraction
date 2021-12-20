package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Boutique;

public interface CompteRepository extends JpaRepository<Boutique, Long> {

}
