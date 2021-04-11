package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.springboot.repository.UserRepository;
import java.util.List;
import javax.validation.Valid;

import com.example.springboot.entity.User;
import com.example.springboot.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	// Get all users
	@GetMapping
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	};
	
	// Get user by id
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		return ResponseEntity.ok(user);
	};
	
	// Post user
	@PostMapping
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	};
	
	// Put user
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") long userId, @Valid @RequestBody User newUser) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setEmail(newUser.getEmail());
		User updateUser = userRepository.save(user);
		return ResponseEntity.ok(updateUser);
	};
	
	// Delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
		userRepository.delete(user);
		return ResponseEntity.ok().build();
	};
}
