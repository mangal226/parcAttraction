package SopraAjc.ParcAttractionSpring.restcontroller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import SopraAjc.ParcAttractionSpring.model.JsonViews;
import SopraAjc.ParcAttractionSpring.model.Role;
import SopraAjc.ParcAttractionSpring.model.User;
import SopraAjc.ParcAttractionSpring.repository.UserRepository;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserRestController {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserRepository userRepo;
	
	@PostMapping("/inscription")
	@JsonView(JsonViews.Common.class)
	public User inscription(@RequestBody User user) {
		if (user.getRoles().get(0)==Role.ROLE_ADMIN){
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRoles(Arrays.asList(Role.ROLE_ADMIN));
			user.setEnable(true);
			return userRepo.save(user);
		}
		else if (user.getRoles().get(0)==Role.ROLE_CAISSIER) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRoles(Arrays.asList(Role.ROLE_CAISSIER));
			user.setEnable(true);
			return userRepo.save(user);
		}
		else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRoles(Arrays.asList(Role.ROLE_OPERATEUR));
			user.setEnable(true);
			return userRepo.save(user);
		}
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		user.setRoles(Arrays.asList(Role.ROLE_ADMIN));
//		user.setRoles(user.getRoles());
//		user.setEnable(true);
//		return userRepo.save(user);
	}
	
	@GetMapping("")
	public boolean login() {
		return true;
	}
}
