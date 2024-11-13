package com.FirstEmployeesProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FirstEmployeesProject.Entity.User;
import com.FirstEmployeesProject.Service.UserService;

@RestController
@RequestMapping
public class Controller {
	@Autowired
	private UserService userservice;

	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllUser() {
		return userservice.getAllUser();

	}

	@GetMapping("/getUser/{userId}")
	public ResponseEntity<Object> getUserById(@PathVariable long userId) {
		return userservice.getUserById(userId);
	}

	@PostMapping("/addUser")
	public  ResponseEntity<Object> createUser(@RequestBody User user) {
		return userservice.saveUser(user);
	}

	@PutMapping("/putUser/{userId}")
	public ResponseEntity<Object> updateUserById(@PathVariable long userId,@RequestBody User updatedUser)
	{
		return userservice.updateUserById(userId, updatedUser);
	}

	@DeleteMapping("/deleteUser/{Id}")
	public ResponseEntity<Object> deleteUserById(@PathVariable long Id)
	{
		return userservice.deleteUserById(Id);
	}

}
