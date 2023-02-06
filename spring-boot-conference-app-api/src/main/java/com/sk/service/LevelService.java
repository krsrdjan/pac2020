package com.sk.service;

import com.sk.entity.Level;
import com.sk.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelService {

	@Autowired
	private LevelRepository repository;

	public List<Level> findAll() {
		return repository.findAll();
	}

	public Level findById(String id) {
		Optional<Level> optional = repository.findById(id);

		return optional.isPresent() ? optional.get() : null;
	}

	public Level save(Level p) {
		return  repository.save(p);
	}

	public void delete(String id) {
		 repository.deleteById(id);
	}

}
