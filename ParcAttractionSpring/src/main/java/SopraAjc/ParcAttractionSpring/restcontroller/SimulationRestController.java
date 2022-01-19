package SopraAjc.ParcAttractionSpring.restcontroller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import SopraAjc.ParcAttractionSpring.exception.AttractionException;
import SopraAjc.ParcAttractionSpring.exception.SimulationException;
import SopraAjc.ParcAttractionSpring.model.Attraction;
import SopraAjc.ParcAttractionSpring.model.JsonViews;
import SopraAjc.ParcAttractionSpring.model.Simulation;
import SopraAjc.ParcAttractionSpring.services.SimulationService;

@RestController
@RequestMapping("/api/simulation")
@CrossOrigin(origins = "*")
public class SimulationRestController {
	
	@Autowired
	private SimulationService simulationService;
	
	
	@GetMapping({"","/"})
	@JsonView(JsonViews.Common.class)
	public List<Simulation> getAll() {
		return simulationService.getAll();
	}

	
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(JsonViews.Common.class)
	@PostMapping("")
	public Simulation create(@Valid @RequestBody Simulation simulation, BindingResult br) {
		if (br.hasErrors()) {
			throw new SimulationException();
		}
		simulationService.simulation(simulation.getNbFamilles(), simulation.getNbJours());
		//simulationService.creationSimulation(simulation);
		return simulation;
	}
	
	

}

