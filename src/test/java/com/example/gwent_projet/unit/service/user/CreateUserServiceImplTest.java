package com.example.gwent_projet.unit.service.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.gwent_projet.entity.user.User;
import com.example.gwent_projet.repository.UserRepository;
import com.example.gwent_projet.services.dto.user.UserCreationDTO;
import com.example.gwent_projet.services.dto.user.UserDTO;
import com.example.gwent_projet.services.impl.UserServiceImpl;

@SpringBootTest
public class CreateUserServiceImplTest {
	
	@InjectMocks
	UserServiceImpl userService;

	@Mock
	private UserRepository mockUserRepository;
	
	// --------------------------------------------------------------------------------
	
	@Test
	@DisplayName ("User - Creation")
	public void createUserTest() {
		System.out.println("--------------");
		System.out.println("User - Creation");
		System.out.println("--------------");

		// when .save is used on a mocked userRepository, return a new User
		when(mockUserRepository.save(Mockito.any(User.class))).thenReturn(new User(0, null, null, null));

		// method to test
		// create the testUser in the repository
		UserCreationDTO creationUser = new UserCreationDTO("newUsername", "newEmail", "newPassword");
		UserDTO newUser = userService.createUser(creationUser);
		
		// assertions
		// assert that data is not empty and corresponding fields are matching
		assertThat(newUser).isNotNull();
		assertThat(newUser.username).isNotNull();
		assertThat(newUser.email).isNotNull();
		assertThat(newUser.username).isEqualTo(creationUser.username);
		assertThat(newUser.email).isEqualTo(creationUser.email);
		// verify that the save method, when used once, saves an user
		Mockito.verify(mockUserRepository, times(1)).save(Mockito.any(User.class)); 
	}
}
