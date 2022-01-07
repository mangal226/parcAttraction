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

import SopraAjc.ParcAttractionSpring.exception.AttractionException;
import SopraAjc.ParcAttractionSpring.model.Attraction;
import SopraAjc.ParcAttractionSpring.model.JsonViews;
import SopraAjc.ParcAttractionSpring.services.AttractionService;

@RestController
@RequestMapping("/api/attraction")
public class AttractionRestController {
	
	@Autowired
	private AttractionService attractionService;
	
	@GetMapping({"","/"})
	@JsonView(JsonViews.Common.class)
	public List<Attraction> getAll() {
		return attractionService.getAll();
	}
	
	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
	public Attraction getById(@PathVariable("id") Long id) {
		return attractionService.getById(id);
	}

	
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	@PostMapping("")
	public Attraction create(@Valid @RequestBody Attraction attraction, BindingResult br) {
		if (br.hasErrors()) {
			throw new AttractionException();
		}
		attractionService.creation(attraction);
		return attraction;
	}

	@JsonView(JsonViews.Common.class)
	@PutMapping("/{id}")
	public Attraction replace(@Valid @RequestBody Attraction attraction, BindingResult br, @PathVariable("id") Long id) {
		attractionService.creation(attraction);
		return attractionService.getById(id);
	}

	@JsonView(JsonViews.Common.class)
	@PatchMapping("/{id}")
	public Attraction update(@RequestBody Map<String, Object> fields, @PathVariable("id") Long id) {
		Attraction attraction = attractionService.getById(id);
		fields.forEach((k, v) -> {
			Field field = ReflectionUtils.findField(Attraction.class, k);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, attraction, v);
		});
		attractionService.creation(attraction);
		return attraction;
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		attractionService.suppression(id);
	}

}
