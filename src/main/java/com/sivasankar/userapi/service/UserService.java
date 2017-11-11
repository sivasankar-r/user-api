package com.sivasankar.userapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sivasankar.userapi.dao.UserRepository;
import com.sivasankar.userapi.framework.UserAlreadyExistException;
import com.sivasankar.userapi.framework.UserNotFoundException;
import com.sivasankar.userapi.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	public User getUser(String name) {
		return userRepository.findOne(name);
	}

	public void addUser(User user) throws UserAlreadyExistException {
		User existingUser = userRepository.findOne(user.getId());
		if(existingUser != null) {
			throw new UserAlreadyExistException("User already exist with same userId");
		}
		
		User existingUserByEmail = userRepository.findByEmailAndActive(user.getEmail(), true);
		if(existingUserByEmail != null) {
			throw new UserAlreadyExistException("An active user found with same email");
		}
		
		userRepository.save(user);
	}

	public void updateUser(User user) throws UserNotFoundException {
		User existingUser = userRepository.findOne(user.getId());
		if(existingUser == null) {
			throw new UserNotFoundException("User not found for the input userId");
		}
		userRepository.save(user);
	}

	public void deleteUser(String id) {
		userRepository.delete(id);
	}
}
