package com.sk.rest;

import com.sk.entity.DayOverview;
import com.sk.entity.Event;
import com.sk.repository.EventRepository;
import com.sk.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DayOverviewRestController {

	@Autowired
	private EventService service;


	@GetMapping("/overviews")
	public ResponseEntity<List<DayOverview>> getAll() {
		try {
			List<Event>  events = service.findAllEventsWithTalk();
			if (events.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			ArrayList<DayOverview> result = new ArrayList<>();

			for (Event e : events) {
				result.addAll(service.getDayOverviewsByEventId(e.getId()));
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
