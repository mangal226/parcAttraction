package restcontroller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils.FieldFilter;
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
import com.google.protobuf.Field;

import exception.FamilleException;
import model.Famille;
import model.JsonViews;
import service.FamilleService;


@RestController
@RequestMapping("/api/famille")
public class FamilleRestController {

	@Autowired
	private FamilleService familleService;

	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Famille> getAll() {
		return familleService.getAll();
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	@PostMapping("")
	public Famille create(@Valid @RequestBody Famille famille, BindingResult br) {
		if (br.hasErrors()) {
			throw new FamilleException();
		}
		familleService.creation(famille);
		return famille;
	}

	@JsonView(JsonViews.Common.class)
	@PutMapping("/{id}")
	public Famille replace(@Valid @RequestBody Famille famille, BindingResult br, @PathVariable("id") Long id) {
		familleService.creation(famille);
		return familleService.getById(id);
	}

//	@JsonView(JsonViews.Common.class)
//	@PatchMapping("/{id}")
//	public Famille update(@RequestBody Map<String, Object> fields, @PathVariable("id") Long id) {
//		Famille famille = familleService.getById(id);
//		fields.forEach((k, v) -> {
//			Field field = ReflectionUtils.findField(Famille.class, k);
//			ReflectionUtils.makeAccessible(field);
//			ReflectionUtils.setField(field, famille, v);
//		});
//		familleService.creation(famille);
//		return famille;
//	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		familleService.suppression(id);
	}
}


