package com.sk.rest;

import com.sk.entity.Room;
import com.sk.entity.Room;
import com.sk.repository.RoomRepository;
import com.sk.service.RoomService;
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
public class RoomRestController {

	@Autowired
	private RoomRepository repository;


	@GetMapping("/rooms")
	public ResponseEntity<List<Room>> getAll() {
		try {
			List<Room>  events = repository.findAll();
			if (events.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(events, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/rooms/{id}")
	public ResponseEntity<Room> update(@PathVariable("id") String id, @RequestBody Room update) {
		Optional<Room> data = repository.findById(id);

		if	 (data.isPresent()) {
			Room found = data.get();
			found.setName(update.getName());
			return new ResponseEntity<>(repository.save(found), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/rooms/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
