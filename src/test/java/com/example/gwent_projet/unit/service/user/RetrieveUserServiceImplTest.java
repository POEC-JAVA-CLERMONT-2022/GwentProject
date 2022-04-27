package com.example.gwent_projet.unit.service.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.gwent_projet.repository.UserRepository;
import com.example.gwent_projet.services.impl.UserServiceImpl;

@SpringBootTest
public class RetrieveUserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	private UserRepository mockUserRepository;
	
	// --------------------------------------------------------------------------------
	
	@Test
	@DisplayName ("User - Retrieve One")
	public void getUserByIdTest() {
		
	}
}
