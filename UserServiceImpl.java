package com.FirstEmployeesProject.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.FirstEmployeesProject.Entity.User;
import com.FirstEmployeesProject.Repository.UserRepository;
import com.FirstEmployeesProject.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

	Map<Object, Object> response;

	@Autowired
	private UserRepository repository;

	@Override
	public ResponseEntity<Object> getAllUser() {
		List<User> data = repository.findAllOrderByCreatedAtDesc();
		response = new HashMap<>();
		if (data == null || data.isEmpty()) {
			response.put("status", "Failed");
			response.put("message", "User details not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		} else {
			response.put("userDetails", data);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@Override
	public ResponseEntity<Object> getUserById(long id) {
		User data = repository.findByIdAndStatus(id, "active");
		response = new HashMap<>();
		if (data == null) {
			response.put("status", "Failed");
			response.put("message", "User details not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		} else {
			response.put("userDetails", data);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@Override
	public ResponseEntity<Object> saveUser(User user) {
		user.setStatus("active");
		User savedUser = repository.save(user);
		response = new HashMap<>();
		response.put("status", "Success");
		response.put("message", "User created successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Override
	public ResponseEntity<Object> deleteUserById(long userId) {
		Optional<User> user = repository.findById(userId);
		if (user.isEmpty()) {
			response = new HashMap<>();
			response.put("status", "failed");
			response.put("message", "user not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		} else {
			repository.deleteById(userId);
			response = new HashMap<>();
			response.put("status", "success");
			response.put("message", "user found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

		}

	}

	@Override
	public ResponseEntity<Object> updateUserById(long userId, User updatedUser) {
		Optional<User> user = repository.findById(userId);
		response = new HashMap<>();
		if (user.isEmpty()) {

			response.put("status", "failed");
			response.put("message", "user not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		} else {

			user.get().setName(updatedUser.getName());
			user.get().setEmail(updatedUser.getEmail());
			user.get().setStatus(updatedUser.getStatus());
			repository.save(user.get());

			response.put("status", "success");
			response.put("message", "User details are updated succsss");
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}

	}

}
