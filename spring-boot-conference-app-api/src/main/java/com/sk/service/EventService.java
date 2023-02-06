package com.sk.service;

import com.sk.entity.DayOverview;
import com.sk.entity.DayOverviewCell;
import com.sk.entity.DayOverviewItem;
import com.sk.entity.Event;
import com.sk.entity.Level;
import com.sk.entity.Room;
import com.sk.entity.Talk;
import com.sk.repository.EventRepository;
import com.sk.repository.TalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;

	@Autowired
	private TalkRepository talkRepository;

	public List<Event> findAll() {
		return repository.findAll();
	}

	public Event findById(String id) {
		Optional<Event> optional = repository.findById(id);

		return optional.isPresent() ? optional.get() : null;
	}

	public List<DayOverview> getDayOverviewsByEventId(String id) {
		Optional<Event> optional = repository.findById(id);
		if(optional.isPresent()) {
			ArrayList<DayOverview> result = new ArrayList<>();

			//get dates from event
			Date beginDate = optional.get().getBeginDate();
			Date endDate = optional.get().getEndDate();
			ArrayList<Date> dates = new ArrayList<>();
			if(beginDate.getDate() == endDate.getDate()) {
				dates.add(beginDate);
			} else {
				dates.add(beginDate);
				dates.add(endDate);
			}

			//iterate over dates
			for (Date day: dates) {
				//create day overview
				DayOverview overview = new DayOverview();
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				DateFormat dfTime = new SimpleDateFormat("hh:mm");

				//filter talks by event id and date
				List<Talk> eventTalkList = talkRepository.findAll().stream()
						.filter(talk -> talk.getEvent().getId().equals(id) &&
						talk.getDate().getDate() == day.getDate())
						.collect(Collectors.toList());

				//sort talk list by date
				eventTalkList.sort(new Comparator<Talk>() {
					@Override
					public int compare(Talk o1, Talk o2) {
						return o1.getDate().compareTo(o2.getDate());
					}
				});

				HashMap<Date, ArrayList<DayOverviewCell>> mapItems = new HashMap<>();

				//create cells and pack them in one row per talk start time
				for (Talk t: eventTalkList ) {
					DayOverviewCell cell = new DayOverviewCell();
					cell.setLevel(t.getLevel().getName());
					cell.setPersons(t.getPersons());
					cell.setRoom(t.getRoom().getName());
					cell.setTitle(t.getTitle());
					cell.setTopics(t.getTopicsString());
					if(!mapItems.containsKey(t.getDate())) {
						mapItems.put(t.getDate(), new ArrayList<DayOverviewCell>());
					}
					mapItems.get(t.getDate()).add(cell);

					if(!overview.getRooms().contains(cell.getRoom())) {
						overview.getRooms().add(cell.getRoom());
					}
				}

				//create items from map and add them to overview
				for (Map.Entry<Date, ArrayList<DayOverviewCell>> e : mapItems.entrySet()) {
					DayOverviewItem item = new DayOverviewItem();
					item.setTime(e.getKey());
					item.setCells(e.getValue());

					overview.getItems().add(item);
					overview.setDate(day);
					overview.setName(optional.get().getName());
				}

				overview.getItems().sort(new Comparator<DayOverviewItem>() {
					@Override
					public int compare(DayOverviewItem o1, DayOverviewItem o2) {
						return o1.getTime().compareTo(o2.getTime());
					}
				});

				result.add(overview);
			}

			return result;
		}

		return null;
	}


	public Event save(Event p) {
		return repository.save(p);
	}

	public void delete(String id) {
		repository.deleteById(id);
	}

	public List<Event> findAllEventsWithTalk() {
		List<Event> events = findAll();
		List<Talk> talks = talkRepository.findAll();

		for (Event e : events) {
			for (Talk t : talks) {
				if (t.getEvent().getId().equals(e.getId())) {
					e.addTalk(t);
				}
			}
		}

		return events;
	}
}
