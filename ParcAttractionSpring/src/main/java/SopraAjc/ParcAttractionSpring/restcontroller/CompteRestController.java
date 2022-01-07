package SopraAjc.ParcAttractionSpring.restcontroller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import SopraAjc.ParcAttractionSpring.exception.CompteException;
import SopraAjc.ParcAttractionSpring.model.Compte;
import SopraAjc.ParcAttractionSpring.model.JsonViews;
import SopraAjc.ParcAttractionSpring.services.CompteService;


@RestController
@RequestMapping("/api/compte")
public class CompteRestController {

	@Autowired
	private CompteService compteService;

	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Compte> getAll() {
		return compteService.getAll();
	}

	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	private Compte getById(@PathVariable Long id) {
		return compteService.getById(id);
	}

	

	
	//////////////////////////////////////////////////////////////////////////////////////

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	public Compte creation(@Valid @RequestBody Compte compte, BindingResult br) {
		if (br.hasErrors()) {
			throw new CompteException();
		}
		compteService.creation(compte);
		return compte;

	}

	
	@JsonView(JsonViews.Common.class)
	@PutMapping("/{id}")
	public Compte replace(@Valid @RequestBody Compte compte, BindingResult br, @PathVariable("id") Long id) {
		compteService.creation(compte);
		return compteService.getById(id);
	}
	

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void suppression(@PathVariable("id") Long id) {
		compteService.suppression(id);
	}
}


