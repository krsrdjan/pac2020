package com.sk.rest;

import com.sk.entity.Language;
import com.sk.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class LanguageRestController {

	@Autowired
	private LanguageRepository repository;


	@GetMapping("/languages")
	public ResponseEntity<List<Language>> getAll() {
		try {
			List<Language>  events = repository.findAll();
			if (events.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(events, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/languages/{id}")
	public ResponseEntity<Language> update(@PathVariable("id") String id, @RequestBody Language update) {
		Optional<Language> data = repository.findById(id);

		if	 (data.isPresent()) {
			Language found = data.get();
			found.setName(update.getName());
			return new ResponseEntity<>(repository.save(found), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/languages/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
