package pwmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pwmanager.advice.TrackExecutionTime;
import pwmanager.model.AuthenticationRequest;
import pwmanager.model.AuthenticationResponse;
import pwmanager.model.User;
import pwmanager.model.Website;
import pwmanager.repository.UserRepository;
import pwmanager.repository.WebsiteRepository;
import pwmanager.service.MyUserDetailsService;
import pwmanager.util.JwtUtil;

@RestController
public class Controller {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	WebsiteRepository websiteRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@TrackExecutionTime
	@GetMapping(path = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Cacheable("user")
	public Object getUser(@PathVariable("id") Integer id) {
		User user = userRepository.getOne(id);
		return user;

	}

	@TrackExecutionTime
	@PostMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String postUser(@RequestBody User user) {
		userRepository.saveAndFlush(user);
		return "Done";
	}

	@TrackExecutionTime
	@DeleteMapping(path = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteUser(@PathVariable("id") int id) {
		userRepository.deleteById(id);
		return "Done";
	}

	@TrackExecutionTime
	@PutMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@RequestBody User user) {
		User updateUser = userRepository.getOne(user.getId());
		updateUser.setUserName(user.getUserName());
		updateUser.setPassword(user.getPassword());
		updateUser.setActive(user.isActive());
		updateUser.setRoles(user.getRoles());

		return userRepository.saveAndFlush(updateUser);
	}

	@TrackExecutionTime
	@GetMapping(path = "/website/{id}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public List<Website> getWebsites(@PathVariable("id") int id) {
		User user = userRepository.getOne(id);
		List<Website> website = user.getWebsites();
		return website;
	}

	@TrackExecutionTime
	@PostMapping(path = "/website", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String posWebsites(@RequestBody Website website) {
		websiteRepository.saveAndFlush(website);
		return "Done";
	}

	@TrackExecutionTime
	@DeleteMapping(path = "/website/{id}")
	public String deleteWebsite(@PathVariable("id") int id) {
		websiteRepository.deleteById(id);
		return "Done";
	}

	@TrackExecutionTime
	@PutMapping(path = "/website", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Website updateWebsite(@RequestBody Website website) {
		Website updateWebsite = websiteRepository.getOne(website.getId());
		updateWebsite.setWebsiteName(website.getWebsiteName());
		updateWebsite.setUrl(website.getUrl());
		updateWebsite.setUsername(website.getUsername());
		updateWebsite.setPassword(website.getPassword());

		return websiteRepository.saveAndFlush(updateWebsite);
	}

	// Authentication

	@PostMapping(path = "/authenticate")
	public ResponseEntity<?> createAuthentication(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		
		}catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
}

}

