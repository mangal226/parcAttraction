package SopraAjc.ParcAttractionSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import SopraAjc.ParcAttractionSpring.repository.UserRepository;


@Service
public class CustomUserDetailsSevice implements UserDetailsService{

	
	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("utilisateur inconnu"));
	}

}
