package com.sk.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document
public class Topic {

	@Id
	private String id;

	private String name;

	private Set<Topic> children;

	private Set<Topic> parents;

}
