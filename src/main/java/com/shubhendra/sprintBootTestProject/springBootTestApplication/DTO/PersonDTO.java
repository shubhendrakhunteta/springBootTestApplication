package com.shubhendra.sprintBootTestProject.springBootTestApplication.DTO;

public class PersonDTO {

	private int personId;
	private String firstName;
	private String lastName;
	private int age;

	public PersonDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonDTO(int personId, String firstName, String lastName, int age) {
		super();
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
