package com.sk.service;

import com.sk.entity.Topic;
import com.sk.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

	@Autowired
	private TopicRepository repository;

	public List<Topic> findAll() {
		return repository.findAll();
	}

	public Topic findById(String id) {
		Optional<Topic> optional = repository.findById(id);

		return optional.isPresent() ? optional.get() : null;
	}

	public Topic save(Topic p) {
		return  repository.save(p);
	}

	public void delete(String id) {
		 repository.deleteById(id);
	}

}
