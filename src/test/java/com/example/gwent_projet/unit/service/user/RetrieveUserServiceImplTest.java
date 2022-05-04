package com.example.gwent_projet.unit.service.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Optional;

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
public class RetrieveUserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	private UserRepository mockUserRepository;
	
	// --------------------------------------------------------------------------------
	
	@Test
	@DisplayName ("User - Retrieve One")
	public void getUserByIdTest() {
		// new user to test on
		User user = new User( "testUsername","testEmail","testPwd");
		user.setId(0l);
		
		// when findById is invoked, return "user"
		when(mockUserRepository.findById(user.getId())).thenReturn(Optional.of(user));
		
		// method to test
		UserDTO searchResult = userService.getUserById(user.getId());
		
		// assertions
		assertThat(searchResult).isNotNull();
		assertThat(searchResult.username).isEqualTo(user.getUsername());
		assertThat(searchResult.email).isEqualTo(user.getEmail());
		
		// verify that findById, when used once, returns the user with this id
		verify(mockUserRepository, times(1)).findById(user.getId());
	}
}
