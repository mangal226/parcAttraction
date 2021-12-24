
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


@RestController
@RequestMapping("/api/personnage")
public class BoutiqueRestController {

	@Autowired
	private BoutiqueService boutiqueService;

	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Boutique> getAll() {
		return boutiqueService.getAll();
	}

	@GetMapping("/{id}")
	@JsonView(JsonViews.BoutiqueMarchandise.class)
	private Boutique getEnVente(@PathVariable Long id) {
		return boutiqueService.getById(id);
	}

	

	
	
	//////////////////////////////////////////////////////////////////////////////////////

	@PostMapping("")
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	public Boutique create(@Valid @RequestBody Boutique boutique, BindingResult br) {
		if (br.hasErrors()) {
			throw new BoutiqueException();
		}
		boutiqueService.creation(boutique);
		return boutique;

	}

	
	@PutMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Boutique put(@Valid @RequestBody Boutique boutique, BindingResult br, @PathVariable Long id) {
		if (br.hasErrors()) {
			throw new BoutiqueException();
		}
		if (boutique.getId() == null) {
			boutique.setId(id);
		}
		boutiqueService.update(boutique);
		return boutique;
	}

	@PatchMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Boutique patch(@RequestBody Map<String, Object> fields, @PathVariable Long id) {
		Boutique boutique = boutiqueService.getById(id);
		fields.forEach((k, v) -> {
			Field field = ReflectionUtils.findField(Boutique.class, k);
			ReflectionUtils.makeAccessible(field);
			if (k.equals("race")) {
				boutique.setRace(Race.valueOf(v.toString()));
			} else {
				ReflectionUtils.setField(field, boutique, v);
			}
		});
		boutiqueService.update(boutique);
		return boutique;
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		boutiqueService.suppression(id);
	}

}
