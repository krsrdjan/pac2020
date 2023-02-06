package com.sk.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class DayOverview {

	private String name;

	private Date date;

	private List<String> rooms = new ArrayList<>();

	private List<DayOverviewItem> items = new ArrayList<>();

}
