package repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Marchandise;


public interface MarchandiseRepository  extends JpaRepository <Marchandise, Long>{

}
