package com.shubhendra.sprintBootTestProject.springBootTestApplication.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.shubhendra.sprintBootTestProject.springBootTestApplication.DTO.PersonDTO;

@RestController
public class PersonController {

	Map<Integer, PersonDTO> persons = null;

	public PersonController() {
		super();
		persons = new HashMap<>();
		persons.put(1, new PersonDTO(1, "Sam Winchester", "sam@sn.com", "9988776655"));
		persons.put(2, new PersonDTO(2, "Dean Winchester", "dean@sn.com", "1122334455"));
		persons.put(3, new PersonDTO(3, "Mary Winchester", "Mary@sn.com", "5566778899"));
		persons.put(4, new PersonDTO(4, "John Winchester", "John@sn.com", "5544332211"));
	}

	@GetMapping(value = "/person/{id}")
	@ResponseBody()
	public PersonDTO getPerson(@PathVariable("id") int id) {

		return persons.get(id);
	}

	@PostMapping(value = "/person/", produces = "application/json")
	public ResponseEntity<?> addPerson(@RequestBody PersonDTO person, UriComponentsBuilder ucBuilder) {
		persons.put(person.getId(), person);
		System.out.println(persons.keySet());

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/getPerson/{id}").buildAndExpand(person.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/person/", method = RequestMethod.GET)
	public ResponseEntity<List<PersonDTO>> listAllPersons() {
		List<PersonDTO> users = Collections.list(Collections.enumeration(persons.values()));
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PersonDTO>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePerson(@PathVariable("id") int id) {

		PersonDTO person = persons.get(id);
		if (person == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		persons.remove(id);
		return new ResponseEntity<PersonDTO>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/person/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePerson(@PathVariable("id") int id, @RequestBody PersonDTO person) {

		PersonDTO currentPerson = persons.get(id);

		if (currentPerson == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		System.out.println(person);
		currentPerson.setName(person.getName());
		currentPerson.setEmail(person.getEmail());
		currentPerson.setPhone(person.getPhone());

		persons.put(id, currentPerson);
		return new ResponseEntity<PersonDTO>(currentPerson, HttpStatus.OK);
	}
}
