package com.sk.rest;

import com.sk.entity.Talk;
import com.sk.repository.TalkRepository;
import com.sk.service.TalkService;
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
public class TalkRestController {

	@Autowired
	private TalkService service;

	@Autowired
	private TalkRepository repository;

	@GetMapping("/talks")
	public ResponseEntity<List<Talk>> getAllTalks() {
		try {
			List<Talk>  events = service.findAll();
			if (events.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(events, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/talks/{id}")
	public ResponseEntity<Talk> getTalkById(@PathVariable("id") String id) {
		Optional<Talk> studentData = repository.findById(id);
		if (studentData.isPresent()) {
			return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/talks/{id}")
	public ResponseEntity<Talk> updateTalk(@PathVariable("id") String id, @RequestBody Talk talk) {
		Optional<Talk> data = repository.findById(id);

		if (data.isPresent()) {
			Talk found = data.get();
			found.setTitle(talk.getTitle());
			return new ResponseEntity<>(repository.save(found), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


	@DeleteMapping("/talks/{id}")
	public ResponseEntity<HttpStatus> deleteTalk(@PathVariable("id") String id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
