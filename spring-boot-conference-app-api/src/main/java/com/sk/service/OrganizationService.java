package com.sk.service;

import com.sk.entity.Organization;
import com.sk.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

	@Autowired
	private OrganizationRepository repository;

	public List<Organization> findAll() {
		return repository.findAll();
	}

	public Organization findById(String id) {
		Optional<Organization> optional = repository.findById(id);

		return optional.isPresent() ? optional.get() : null;
	}

	public Organization save(Organization p) {
		return  repository.save(p);
	}

	public void delete(String id) {
		 repository.deleteById(id);
	}

}
