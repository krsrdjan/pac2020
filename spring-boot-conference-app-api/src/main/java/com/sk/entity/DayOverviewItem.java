package com.sk.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DayOverviewItem {

	private Date time;

	private List<DayOverviewCell> cells;

}
