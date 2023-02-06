package com.sk.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Document
public class Person {

	@Id
	private String id;

	private String name;

	private Organization organization;

	private Set<Talk> talks;

	private String talksString = "";

	public void addTalk(Talk t) {
		if (talks == null)
			talks = new HashSet<Talk>();

		talks.add(t);

		String current = getTalksString().isEmpty() ? "" : getTalksString() + ",";
		setTalksString(current + t.getTopics().stream().map(Topic::getName).collect(Collectors.joining(",")));
	}
}
