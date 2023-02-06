package com.sk.service;

import com.sk.entity.Room;
import com.sk.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

	@Autowired
	private RoomRepository repository;

	public List<Room> findAll() {
		return repository.findAll();
	}

	public Room findById(String id) {
		Optional<Room> optional = repository.findById(id);

		return optional.isPresent() ? optional.get() : null;
	}

	public Room save(Room p) {
		return  repository.save(p);
	}

	public void delete(String id) {
		 repository.deleteById(id);
	}

}
