package com.sk.rest;

import com.sk.entity.Person;
import com.sk.entity.Person;
import com.sk.repository.PersonRepository;
import com.sk.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonRestController {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private PersonRepository repository;

	@GetMapping("/persons")
	public ResponseEntity<List<Person>> getAllPersons(@RequestParam(required = false) String firstName) {
		try {
			List<Person> events = personService.findAll();
			if (events.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(events, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/persons/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable("id") String id) {
		Person person = personService.findById(id);
		if (person != null) {
			return new ResponseEntity<>(person, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/persons/{id}")
	public ResponseEntity<Person> update(@PathVariable("id") String id, @RequestBody Person update) {
		Optional<Person> data = repository.findById(id);

		if	 (data.isPresent()) {
			Person found = data.get();
			found.setName(update.getName());
			return new ResponseEntity<>(repository.save(found), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	@DeleteMapping("/persons/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
