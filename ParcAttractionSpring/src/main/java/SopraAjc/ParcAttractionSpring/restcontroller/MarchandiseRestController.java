package SopraAjc.ParcAttractionSpring.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import SopraAjc.ParcAttractionSpring.exception.MarchandiseException;
import SopraAjc.ParcAttractionSpring.model.JsonViews;
import SopraAjc.ParcAttractionSpring.model.Marchandise;
import SopraAjc.ParcAttractionSpring.services.MarchandiseService;



@RestController
@RequestMapping("/api/marchandise")
@CrossOrigin(origins = "*")
public class MarchandiseRestController {

	@Autowired
	private MarchandiseService marchandiseService;

	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Marchandise> getAll() {
		return marchandiseService.getAll();
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	@PostMapping("")
	public Marchandise create(@Valid @RequestBody Marchandise marchandise, BindingResult br) {
		if (br.hasErrors()) {
			throw new MarchandiseException();
		}
		marchandiseService.creation(marchandise);
		return marchandise;
	}

	@JsonView(JsonViews.Common.class)
	@PutMapping("/{id}")
	public Marchandise replace(@Valid @RequestBody Marchandise marchandise, BindingResult br, @PathVariable("id") Long id) {
		marchandiseService.creation(marchandise);
		return marchandiseService.getById(id);
	}
	
//	@JsonView(JsonViews.Marchandise.class)
//	@PatchMapping("/{id}")
//	public Marchandise update(@RequestBody Map<String, Object> fields, @PathVariable("id") Long id) {
//		Marchandise marchandise = marchandiseService.getById(id);
//		fields.forEach((k, v) -> {
//			Field field = ReflectionUtils.findField(Marchandise.class, k);
//			ReflectionUtils.makeAccessible(field);
//			ReflectionUtils.setField(field, marchandise, v);
//		});
//		marchandiseService.creation(marchandise);
//		return marchandise;
//	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		marchandiseService.suppression(id);
	}
}


