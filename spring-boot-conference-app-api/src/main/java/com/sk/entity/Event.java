package com.sk.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Document
public class Event {

	@Id
	private String id;

	private String name;

	private Location location;

	private Date beginDate;

	private Date endDate;

	private Set<Talk> talks;

	private String topics = "";

	public void addTalk(Talk t) {
		if(getTalks() == null) {
			setTalks(new HashSet<Talk>());
		}

		String current = getTopics().isEmpty() ? "" : getTopics() + ",";
		setTopics(current + t.getTopics().stream().map(Topic::getName).collect(Collectors.joining(",")));
	}
}
