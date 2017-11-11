package com.sivasankar.userapi.dao;

import org.springframework.data.repository.CrudRepository;

import com.sivasankar.userapi.model.User;


public interface UserRepository extends CrudRepository<User, String>{
	public User findByEmailAndActive(String email, boolean active);
}