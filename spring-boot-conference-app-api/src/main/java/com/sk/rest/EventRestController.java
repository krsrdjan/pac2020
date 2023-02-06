package com.sk.rest;

import com.sk.entity.Event;
import com.sk.repository.EventRepository;
import com.sk.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class EventRestController {

	@Autowired
	private EventRepository repository;

	@Autowired
	private EventService service;

	@GetMapping("/events")
	public ResponseEntity<List<Event>> getAllEvents() {
		try {
			List<Event>  events = service.findAllEventsWithTalk();
			if (events.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(events, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/events/{id}")
	public ResponseEntity<Event> getEventById(@PathVariable("id") String id) {
		Optional<Event> studentData = repository.findById(id);
		if (studentData.isPresent()) {
			return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/events/{id}")
	public ResponseEntity<Event> updateEvent(@PathVariable("id") String id, @RequestBody Event event) {
		Optional<Event> data = repository.findById(id);

		if (data.isPresent()) {
			Event found = data.get();
			found.setName(event.getName());
			return new ResponseEntity<>(repository.save(found), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


}
