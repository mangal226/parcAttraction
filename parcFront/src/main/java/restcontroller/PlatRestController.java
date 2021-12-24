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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import exception.PlatException;
import model.JsonViews;
import model.Plat;
import service.PlatService;

@RestController
@RequestMapping("api/plat")
public class PlatRestController {
	
	@Autowired
	private PlatService platService;
	
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Plat> getAll(){
		return platService.getAll();
	}
	
	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
	public Plat getById(@PathVariable("id") Long id) {
		return platService.getById(id);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	@PostMapping("")
	public Plat create (@Valid @RequestBody Plat plat, BindingResult br) {
		if (br.hasErrors()) {
			throw new PlatException();
		}
		platService.creationOuModification(plat);
		return plat;
	}
	
	@JsonView(JsonViews.Common.class)
	@PatchMapping("/{id}")
	public Plat update(@RequestBody Map<String, Object> fields, @PathVariable("id") Long id) {
		Plat plat = platService.getById(id);
		fields.forEach((k, v) -> {
			Field field = ReflectionUtils.findField(Plat.class, k);
			ReflectionUtils.makeAccessible(field);
			ReflectionUtils.setField(field, plat, v);
		});
		platService.creationOuModification(plat);
		return plat;
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		platService.suppression(id);
	}

}
