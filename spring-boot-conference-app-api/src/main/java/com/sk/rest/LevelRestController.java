package com.sk.rest;

import com.sk.entity.Level;
import com.sk.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class LevelRestController {

	@Autowired
	private LevelRepository repository;


	@GetMapping("/levels")
	public ResponseEntity<List<Level>> getAll() {
		try {
			List<Level>  events = repository.findAll();
			if (events.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(events, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/levels/{id}")
	public ResponseEntity<Level> update(@PathVariable("id") String id, @RequestBody Level update) {
		Optional<Level> data = repository.findById(id);

		if (data.isPresent()) {
			Level found = data.get();
			found.setName(update.getName());
			return new ResponseEntity<>(repository.save(found), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	@DeleteMapping("/levels/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
