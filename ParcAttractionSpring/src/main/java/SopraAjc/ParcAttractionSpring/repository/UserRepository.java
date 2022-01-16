package SopraAjc.ParcAttractionSpring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import SopraAjc.ParcAttractionSpring.model.User;

public interface UserRepository extends JpaRepository<User, Long>{


	
	Optional<User> findByLogin (String login);
	
	Optional<User> findByPassword (String password);
}
