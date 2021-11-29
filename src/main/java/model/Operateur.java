package model;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;


@Entity
public class Operateur extends Compte {
@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )

    public Operateur() {
	super();
}
	
	
	public Operateur(int id, String login, String password) {
		super(id, login, password);
	}

	
	
}
