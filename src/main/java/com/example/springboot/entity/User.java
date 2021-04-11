package com.example.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import javax.persistence.Column;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Size(min = 2, message = "First name should have at least 2 characters")
	@Column(name = "first_name")
	private String firstName;
	
	@NotBlank
	@Size(min = 5, message = "Last name should have at least 5 characters")
	@Column(name = "last_name")
	private String lastName;
	
	@NotBlank
	@Email
	@Column(name = "email")
	private String email;
	
	public User() {};
	
	public User(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	};
	
	public long getId() {
		return id;
	};

	public void setId(long id) {
		this.id = id;
	};

	public String getFirstName() {
		return firstName;
	};

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	};

	public String getLastName() {
		return lastName;
	};

	public void setLastName(String lastName) {
		this.lastName = lastName;
	};

	public String getEmail() {
		return email;
	};

	public void setEmail(String email) {
		this.email = email;
	};
	
	
}
