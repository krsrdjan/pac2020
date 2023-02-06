package com.sk.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Document
public class Talk {

	@Id
	private String id;

	private String title;

	private Long duration;

	private Language language;

	private Set<Person> people;

	private Level level;

	private Set<Topic> topics;

	private Room room;

	private Date date;

	private Event event;

	private Location location;

	private String persons = "";

	private String topicsString = "";

	public void addPerson(Person p) {
		String current = getPersons().isEmpty() ? "" : getPersons() + ",";
		setPersons(current + p.getName());
	}

	public void addTopic(Topic t) {
		if(getTopics() == null) {
			setTopics(new HashSet<Topic>());
		}

		getTopics().add(t);

		String current = getTopicsString().isEmpty() ? "" : getTopicsString() + ",";
		setTopicsString(current + t.getName());
	}
}
