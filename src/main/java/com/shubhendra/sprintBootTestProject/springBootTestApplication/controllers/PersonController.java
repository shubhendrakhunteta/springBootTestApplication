package com.shubhendra.sprintBootTestProject.springBootTestApplication.controllers;

import java.util.HashMap;
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
		persons.put(01, new PersonDTO(01, "Fname1", "Lname1", 20));
		persons.put(02, new PersonDTO(02, "Fname2", "Lname2", 25));
		persons.put(03, new PersonDTO(03, "Fname3", "Lname3", 30));
		persons.put(04, new PersonDTO(04, "Fname4", "Lname4", 20));
	}

	@GetMapping(value = "/getPerson/{id}")
	@ResponseBody()
	public PersonDTO getPerson(@PathVariable("id") int id) {

		return persons.get(id);
	}
	
	@PostMapping(value = "/addPerson/", produces = "application/json")
	public ResponseEntity<?> addPerson(@RequestBody PersonDTO person, UriComponentsBuilder ucBuilder) {
		persons.put(person.getPersonId(), person);
		System.out.println(persons.keySet());
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/getPerson/{id}").buildAndExpand(person.getPersonId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
}
