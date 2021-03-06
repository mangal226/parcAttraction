
package restcontroller;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;



import exception.BoutiqueException;
import model.JsonViews;
import model.Marchandise;
import model.Boutique;
import service.BoutiqueService;
import service.MarchandiseService;

@RestController
@RequestMapping("/api/boutique")
public class BoutiqueRestController {

	@Autowired
	private BoutiqueService boutiqueService;
	@Autowired
	private MarchandiseService marchandiseService;

	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Boutique> getAll() {
		return boutiqueService.getAll();
	}

	@GetMapping("/{id}")
	@JsonView(JsonViews.BoutiqueMarchandise.class)
	private List <Marchandise> getEnVente(@PathVariable Long id) {
		return boutiqueService.getById(id).getEnVente();
	}

	

	
	
	//////////////////////////////////////////////////////////////////////////////////////

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	public Boutique creation(@Valid @RequestBody Boutique boutique, BindingResult br) {
		if (br.hasErrors()) {
			throw new BoutiqueException();
		}
		boutiqueService.creation(boutique);
		return boutique;

	}

	
	@JsonView(JsonViews.Common.class)
	@PutMapping("/{id}")
	public Boutique replace(@Valid @RequestBody Boutique boutique, BindingResult br, @PathVariable("id") Long id) {
		boutiqueService.creation(boutique);
		return boutiqueService.getById(id);
	}
	

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void suppression(@PathVariable("id") Long id) {
		boutiqueService.suppression(id);
	}
}


