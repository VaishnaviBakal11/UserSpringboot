package com.FirstEmployeesProject.Service;

import org.springframework.http.ResponseEntity;

import com.FirstEmployeesProject.Entity.User;

public interface UserService {

	ResponseEntity<Object> getAllUser();

	ResponseEntity<Object> getUserById(long userId);

	ResponseEntity<Object> saveUser(User user);

	ResponseEntity<Object> deleteUserById(long userId);

	ResponseEntity<Object> updateUserById(long userId, User updatedUser);

}
