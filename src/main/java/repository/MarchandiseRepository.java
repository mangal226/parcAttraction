package repository;
import java.util.List;
import java.util.Optional;

import model.Famille;

public interface MarchandiseRepository  extends JpaRepository <Marchandise, Long>{
	Optional <Famille> findById(Long Id);
	List <Famille> findByAll(Long Id);

}
