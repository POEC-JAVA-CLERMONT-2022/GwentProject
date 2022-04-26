package com.example.gwent_projet.unit.service.user;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.gwent_projet.entity.user.User;
import com.example.gwent_projet.repository.UserRepository;
import com.example.gwent_projet.services.dto.user.UserDTO;
import com.example.gwent_projet.services.impl.UserServiceImpl;

@SpringBootTest
public class UpdateUserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	private UserRepository mockUserRepository;
	
	// --------------------------------------------------------------------------------
	
	@Test
	@DisplayName ("User - Update")
	public void updateUserTest() {
		
	}
	
}
