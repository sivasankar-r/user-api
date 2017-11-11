package com.sivasankar.userapi.rest;

import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.core.Is.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sivasankar.userapi.model.User;
import com.sivasankar.userapi.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService service;

	@Test
	@Ignore
	public void testFetchUsers() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testFetchUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddUser() throws Exception {
		User user = createUser("123", "sivasankar-r@outlook.com");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsBytes(user));
		mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("userId", is(user.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("resMsg", is("User Created Successfully")));
				
	}

	@Test
	public void testUpdateUser() throws Exception {
		User user = createUser("123", "sivasankar-r@outlook.com");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsBytes(user));
		mvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("userId", is(user.getId())));
	}

	@Test
	@Ignore
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	 private static User createUser(String id, String email) {
	        User user = new User();
	        user.setId(id);
	        user.setEmail(email);
	        
	        return user;
	    }
}
