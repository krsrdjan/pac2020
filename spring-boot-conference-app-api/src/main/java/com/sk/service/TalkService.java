package com.sk.service;

import com.sk.entity.Talk;
import com.sk.repository.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TalkService {

	@Autowired
	private TalkRepository repository;

	public List<Talk> findAll() {
		return repository.findAll();
	}

	public Talk findById(String id) {
		Optional<Talk> optional = repository.findById(id);

		return optional.isPresent() ? optional.get() : null;
	}

	public Talk save(Talk p) {
		return  repository.save(p);
	}

	public void delete(String id) {
		 repository.deleteById(id);
	}

}
