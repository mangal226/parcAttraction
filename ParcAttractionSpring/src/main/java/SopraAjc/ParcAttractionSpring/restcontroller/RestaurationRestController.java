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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import SopraAjc.ParcAttractionSpring.exception.RestaurationException;
import SopraAjc.ParcAttractionSpring.model.JsonViews;
import SopraAjc.ParcAttractionSpring.model.Restauration;
import SopraAjc.ParcAttractionSpring.services.RestaurationService;

@RestController
@RequestMapping("api/restauration")
public class RestaurationRestController {

	@Autowired
	private RestaurationService RestaurationService;
	
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Restauration> getAll(){
		return RestaurationService.getAll();
	}
	
	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
	public Restauration getById(@PathVariable("id") Long id) {
		return RestaurationService.getById(id);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	@PostMapping("")
	public Restauration create (@Valid @RequestBody Restauration Restauration, BindingResult br) {
		if (br.hasErrors()) {
			throw new RestaurationException();
		}
		RestaurationService.creationOuModification(Restauration);
		return Restauration;
	}
	
	@JsonView(JsonViews.Common.class)
	@PatchMapping("/{id}")
	public Restauration update(@RequestBody Map<String, Object> fields, @PathVariable("id") Long id) {
		Restauration Restauration = RestaurationService.getById(id);
		fields.forEach((k, v) -> {
			Field field = ReflectionUtils.findField(Restauration.class, k);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, Restauration, v);
		});
		RestaurationService.creationOuModification(Restauration);
		return Restauration;
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		RestaurationService.suppression(id);
	}
}
