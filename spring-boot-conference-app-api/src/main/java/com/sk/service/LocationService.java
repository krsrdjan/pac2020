package com.sk.service;

import com.sk.entity.Location;
import com.sk.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;

	public List<Location> findAll() {
		return locationRepository.findAll();
	}

	public Location findById(String id) {
		Optional<Location> o = locationRepository.findById(id);

		return o.isPresent() ? o.get() : null;
	}

	public Location save(Location p) {
		return locationRepository.save(p);
	}

	public void delete(String id) {
		 locationRepository.deleteById(id);
	}

}
