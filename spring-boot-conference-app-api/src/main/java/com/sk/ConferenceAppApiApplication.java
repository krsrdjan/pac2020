package com.sk;

import com.sk.entity.Event;
import com.sk.entity.Language;
import com.sk.entity.Level;
import com.sk.entity.Location;
import com.sk.entity.Organization;
import com.sk.entity.Person;
import com.sk.entity.Room;
import com.sk.entity.Talk;
import com.sk.entity.Topic;
import com.sk.repository.EventRepository;
import com.sk.repository.LanguageRepository;
import com.sk.repository.LevelRepository;
import com.sk.repository.LocationRepository;
import com.sk.repository.OrganizationRepository;
import com.sk.repository.PersonRepository;
import com.sk.repository.RoomRepository;
import com.sk.repository.TalkRepository;
import com.sk.repository.TopicRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class ConferenceAppApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConferenceAppApiApplication.class, args);
	}

	@Bean
	ApplicationRunner init(EventRepository eventRepository,
						   LocationRepository locationRepository,
						   OrganizationRepository organizationRepository,
						   PersonRepository personRepository,
						   TopicRepository topicRepository,
						   TalkRepository talkRepository,
						   RoomRepository roomRepository,
						   LevelRepository levelRepository,
						   LanguageRepository languageRepository) {
		return (ApplicationArguments args) -> dataSetup(eventRepository,
				locationRepository,
				organizationRepository,
				personRepository,
				topicRepository,
				talkRepository,
				roomRepository,
				levelRepository,
				languageRepository);
	}

	private void dataSetup(EventRepository eventRepository,
						   LocationRepository locationRepository,
						   OrganizationRepository organizationRepository,
						   PersonRepository personRepository,
						   TopicRepository topicRepository,
						   TalkRepository talkRepository,
						   RoomRepository roomRepository,
						   LevelRepository levelRepository,
						   LanguageRepository languageRepository) {

		Organization o1 = new Organization();
		o1.setName("Prodyna");
		o1 = organizationRepository.save(o1);

		Person p1 = new Person();
		p1.setName("Srdjan Krsmanovic");
		p1.setOrganization(o1);
		p1 = personRepository.save(p1);

		Person p2 = new Person();
		p2.setName("Milos Nikolic");
		p2.setOrganization(o1);
		p2 = personRepository.save(p2);

		Topic java = createTopic(topicRepository,"Java");
		Topic spring = createTopic(topicRepository,"Spring");
		Topic react = createTopic(topicRepository,"React");
		Topic angular = createTopic(topicRepository,"Angular");

		Event belgrade = createEvent(eventRepository, createLocation(locationRepository,"Belgrade"),  "22-10-2020", "22-10-2020");
		//Event frankfurt = createEvent(eventRepository, createLocation(locationRepository, "Frankfurt"),  "23-10-2020", "23-10-2020");

		Room blueRoom = createRoom(roomRepository, "Blue room");
		Room greenRoom = createRoom(roomRepository, "Green room");

		Talk javaTalk = createTalk(talkRepository, "Java Talk", "22-10-2020 09:00");
		javaTalk.addTopic(java);
		javaTalk.setRoom(blueRoom);
		javaTalk.setLevel(createLevel(levelRepository, "Beginner"));
		javaTalk.setLanguage(createLanguage(languageRepository, "English"));
		javaTalk.setEvent(belgrade);
		javaTalk.addPerson(p1);
		talkRepository.save(javaTalk);

		p1.addTalk(javaTalk);
		personRepository.save(p1);

		Talk springTalk = createTalk(talkRepository, "Spring Talk", "22-10-2020 09:00");
		springTalk.addTopic(spring);
		springTalk.setRoom(greenRoom);
		springTalk.setLevel(createLevel(levelRepository, "Intermediate"));
		springTalk.setLanguage(createLanguage(languageRepository, "English"));
		springTalk.setEvent(belgrade);
		springTalk.addPerson(p2);
		talkRepository.save(springTalk);

		p2.addTalk(springTalk);
		personRepository.save(p2);

		belgrade.addTalk(javaTalk);
		belgrade.addTalk(springTalk);

		Talk front = createTalk(talkRepository, "JS Frontend Talk", "22-10-2020 11:00");
		front.addTopic(react);
		front.setRoom(blueRoom);
		front.setLevel(createLevel(levelRepository, "Advanced"));
		front.setLanguage(createLanguage(languageRepository, "German"));
		front.setEvent(belgrade);
		front.addPerson(p2);
		talkRepository.save(front);

		p2.addTalk(front);
		personRepository.save(p2);

		belgrade.addTalk(front);

	}


	private Level createLevel(LevelRepository levelRepository, String name) {
		Level l =  new Level();
		l.setName(name);
		l = levelRepository.save(l);
		return l;
	}

	private Language createLanguage(LanguageRepository languageRepository, String name) {
		Language l =  new Language();
		l.setName(name);
		l = languageRepository.save(l);
		return l;
	}

	private Room createRoom(RoomRepository roomRepository, String name) {
		Room t = new Room();
		t.setName(name);
		t = roomRepository.save(t);
		return t;
	}

	private Talk createTalk(TalkRepository talkRepository, String name, String date) {
		Talk t = new Talk();
		t.setTitle(name);
		t.setDate(createDate(date));
		t = talkRepository.save(t);
		return t;
	}

	private Date createDate(String date) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		try {
			return  df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Topic createTopic(TopicRepository topicRepository, String name) {
		Topic java = new Topic();
		java.setName(name);
		java = topicRepository.save(java);
		return java;
	}

	private Event createEvent(EventRepository eventRepository, Location l, String beginDate, String endDate) {
		Event p = new Event();
		p.setName("Event " + l.getName());
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		try {
			p.setBeginDate(df.parse(beginDate));
			p.setEndDate(df.parse(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		p.setLocation(l);

		p = eventRepository.save(p);
		return p;
	}

	private Location createLocation(LocationRepository locationRepository, String name) {
		Location l = new Location();
		l.setName(name);
		l = locationRepository.save(l);
		return l;
	}
}
