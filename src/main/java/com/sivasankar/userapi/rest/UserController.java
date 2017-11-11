package com.sivasankar.userapi.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sivasankar.userapi.framework.UserNotFoundException;
import com.sivasankar.userapi.model.User;
import com.sivasankar.userapi.service.UserService;
import com.sivasankar.userapi.to.ServiceResponseTo;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "Application running...";
	}

	@RequestMapping("/users")
	public List<User> fetchUsers() {
		return userService.getAllUsers();
	}

	@RequestMapping("/users/{name}")
	public User fetchUser(@PathVariable String name) {
		return userService.getUser(name);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<ServiceResponseTo> addUser(@RequestBody User user) {
		ServiceResponseTo response = new ServiceResponseTo();
		response.setUserId(user.getId());
		HttpStatus status = null;
		try{
			userService.addUser(user);
			response.setResMsg("User Created Successfully");
			status = HttpStatus.CREATED;
		} catch(Exception e) {
			response.setResMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<ServiceResponseTo>(response, status);
	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	public ResponseEntity<ServiceResponseTo> updateUser(@RequestBody User user) {
		ServiceResponseTo response = new ServiceResponseTo();
		response.setUserId(user.getId());
		HttpStatus status = null;
		try {
			userService.updateUser(user);
			status = HttpStatus.OK;
		} catch (UserNotFoundException e) {
			response.setResMsg(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<ServiceResponseTo>(response, status);
	}

	@RequestMapping(value="/users", method = RequestMethod.DELETE)
	public void deleteUser(@RequestParam(name="id", required=true) String id) {
		userService.deleteUser(id);
	}
}