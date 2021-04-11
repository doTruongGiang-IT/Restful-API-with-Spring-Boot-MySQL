package com.example.springboot.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.springboot.entity.User;

public class RestClient {

	private static final String GET_ALL_USERS_API = "http://localhost:8080/api/v1/users";
	private static final String GET_USER_BY_ID_API = "http://localhost:8080/api/v1/users/{id}";
	private static final String CREATE_USER_API = "http://localhost:8080/api/v1/users";
	private static final String UPDATE_USER_API = "http://localhost:8080/api/v1/users/{id}";
	private static final String DELETE_USER_API = "http://localhost:8080/api/v1/users/{id}";
	
	static RestTemplate restTemplate = new RestTemplate();
	
	public static void main(String[] args) {
		callGetAllUsersApi();
		callGetUserByIdApi();
		callCreateUserApi();
		callUpdateUserApi();
		callDeleteUserApi();
	};
	
	private static void callGetAllUsersApi() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<String> result = restTemplate.exchange(GET_ALL_USERS_API, HttpMethod.GET, entity, String.class);
		System.out.println(result);
	};
	
	private static void callGetUserByIdApi() {
		Map<String, Integer> param = new HashMap<>();
		param.put("id", 4);
		User user = restTemplate.getForObject(GET_USER_BY_ID_API, User.class, param);
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getEmail());
	};
	
	private static void callCreateUserApi() {
		User user = new User("luu", "duc hoa", "hoachac@gmail.com");
		ResponseEntity<User> newUser =  restTemplate.postForEntity(CREATE_USER_API, user, User.class);
		System.out.println(newUser.getBody());
	};
	
	private static void callUpdateUserApi() {
		Map<String, Integer> param = new HashMap<>();
		param.put("id", 4);
		User user = new User("le nguyen", "anh duc", "ducle@gmail.com");
		restTemplate.put(UPDATE_USER_API, user,  param);
	};
	
	private static void callDeleteUserApi() {
		Map<String, Integer> param = new HashMap<>();
		param.put("id", 3);
		restTemplate.delete(DELETE_USER_API, param);
	};

}
