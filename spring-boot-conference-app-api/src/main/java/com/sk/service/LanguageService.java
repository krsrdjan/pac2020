package com.sk.service;

import com.sk.entity.Language;
import com.sk.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

	@Autowired
	private LanguageRepository repository;

	public List<Language> findAll() {
		return repository.findAll();
	}

	public Language findById(String id) {
		Optional<Language> optional = repository.findById(id);

		return optional.isPresent() ? optional.get() : null;
	}

	public Language save(Language p) {
		return  repository.save(p);
	}

	public void delete(String id) {
		 repository.deleteById(id);
	}

}
