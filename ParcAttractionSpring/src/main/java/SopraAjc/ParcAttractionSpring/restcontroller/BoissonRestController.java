package SopraAjc.ParcAttractionSpring.restcontroller;

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

import SopraAjc.ParcAttractionSpring.exception.BoissonException;
import SopraAjc.ParcAttractionSpring.model.Boisson;
import SopraAjc.ParcAttractionSpring.model.JsonViews;
import SopraAjc.ParcAttractionSpring.services.BoissonService;

@RestController
@RequestMapping("/api/boisson")
public class BoissonRestController {
	
	@Autowired
	private BoissonService boissonService;
	
	@GetMapping({"","/"})
	@JsonView(JsonViews.Common.class)
	public List<Boisson> getAll() {
		return boissonService.getAll();
	}
	
	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
	public Boisson getById(@PathVariable("id") Long id) {
		return boissonService.getById(id);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	@PostMapping("")
	public Boisson create(@Valid @RequestBody Boisson Boisson, BindingResult br) {
		if (br.hasErrors()) {
			throw new BoissonException();
		}
		boissonService.creation(Boisson);
		return Boisson;
	}

	@JsonView(JsonViews.Common.class)
	@PutMapping("/{id}")
	public Boisson replace(@Valid @RequestBody Boisson Boisson, BindingResult br, @PathVariable("id") Long id) {
		boissonService.creation(Boisson);
		return boissonService.getById(id);
	}

	@JsonView(JsonViews.Common.class)
	@PatchMapping("/{id}")
	public Boisson update(@RequestBody Map<String, Object> fields, @PathVariable("id") Long id) {
		Boisson Boisson = boissonService.getById(id);
		fields.forEach((k, v) -> {
			Field field = ReflectionUtils.findField(Boisson.class, k);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, Boisson, v);
		});
		boissonService.creation(Boisson);
		return Boisson;
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		boissonService.suppression(id);
	}

}
