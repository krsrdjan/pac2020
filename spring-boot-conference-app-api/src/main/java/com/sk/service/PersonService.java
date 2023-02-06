package com.sk.service;

import com.sk.entity.Person;
import com.sk.entity.Talk;
import com.sk.repository.PersonRepository;
import com.sk.repository.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private TalkRepository talkRepository;

	public List<Person> findAll() {
		return personRepository.findAll();
	}

	public Person find(String id) {
		Optional<Person> p = personRepository.findById(id);
		if(p.isPresent()) {
			List<Talk> all = talkRepository.findAll();
			Set<Talk> talks = all.stream().filter(talk -> talk.getPeople().contains(p.get())).collect(Collectors.toSet());
			p.get().setTalks(talks);
			return p.get();
		}

		return null;
	}

	public Person findById(String id) {
		Optional<Person> person = personRepository.findById(id);

		return person.isPresent() ? person.get() : null;
	}

	public Person save(Person p) {
		return  personRepository.save(p);
	}

	public void delete(String id) {
		 personRepository.deleteById(id);
	}

}
