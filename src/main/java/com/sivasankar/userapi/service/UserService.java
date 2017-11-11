package com.sivasankar.userapi.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

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

	public void updateUser(User user) throws UserNotFoundException, OperationNotSupportedException {
		User existingUser = userRepository.findOne(user.getId());
		if(existingUser == null) {
			throw new UserNotFoundException("User not found for the input userId");
		}
		
		if(!existingUser.getEmail().equals(user.getEmail())) {
			throw new OperationNotSupportedException("Can only update pincode or birth date. Email updation not allowed.");
		}
		
		if(!existingUser.getfName().equals(user.getfName()) || !existingUser.getlName().equals(user.getlName())) {
			throw new OperationNotSupportedException("Can only update pincode or birth date. First Name or Last Name updation not allowed");
		}
		userRepository.save(user);
	}

	public void deleteUser(String id) {
		userRepository.delete(id);
	}
}
