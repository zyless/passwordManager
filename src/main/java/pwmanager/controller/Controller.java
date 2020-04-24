package pwmanager.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectWriter;

import lombok.extern.log4j.Log4j2;
import pwmanager.model.User;
import pwmanager.model.Website;
import pwmanager.repository.UserRepository;
import pwmanager.repository.WebsiteRepository;

@RestController
@Log4j2
public class Controller {
	
	@Autowired
	ObjectWriter ow;
	
	@Autowired
	WebsiteRepository websiteRepository;

	@Autowired
	UserRepository userRepository;

	@GetMapping(path = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getUser(@PathVariable("id") Integer id) {
		Optional<User> user = userRepository.findById(id);
		log.info(user.toString());
		return user; 
	
	}

	@PostMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String postUser(@RequestBody User user) {
		userRepository.saveAndFlush(user);
		return "Done";
	}

	@DeleteMapping(path = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteUser(@PathVariable("id") int id) {
		userRepository.deleteById(id);
		return "Done";
	}

	@PutMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@RequestBody User user) {
		User updateUser = userRepository.getOne(user.getId());
		updateUser.setUserName(user.getUserName());
		updateUser.setPassword(user.getPassword());
		updateUser.setActive(user.isActive());
		updateUser.setRoles(user.getRoles());

		return userRepository.saveAndFlush(updateUser);
	}

	@GetMapping(path = "/website/{id}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public Optional<Website> getWebsites(@PathVariable("id") int id) {
		Optional<Website> website = websiteRepository.findById(id);
		return website;
	}

	@PostMapping(path = "/website", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String posWebsites(@RequestBody Website website) {
		websiteRepository.saveAndFlush(website);
		return "Done";
	}

	@DeleteMapping(path = "/website/{id}")
	public String deleteWebsite(@PathVariable("id") int id) {
		websiteRepository.deleteById(id);
		return "Done";
	}

	@PutMapping(path = "/website", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Website updateWebsite(@RequestBody Website website) {
		Website updateWebsite = websiteRepository.getOne(website.getId());
		updateWebsite.setWebsiteName(website.getWebsiteName());
		updateWebsite.setUrl(website.getUrl());
		updateWebsite.setUsername(website.getUsername());
		updateWebsite.setPassword(website.getPassword());

		return websiteRepository.saveAndFlush(updateWebsite);
	}
}
