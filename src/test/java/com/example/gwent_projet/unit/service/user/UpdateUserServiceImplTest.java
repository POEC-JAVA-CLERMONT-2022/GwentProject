package com.example.gwent_projet.unit.service.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.gwent_projet.entity.user.User;
import com.example.gwent_projet.repository.UserRepository;
import com.example.gwent_projet.services.dto.user.UserCreationDTO;
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
		// new user to test on
		User user = new User();
		user.setId(0l);

		// when save is invoked, return a new User with these values
		when(mockUserRepository.save(any(User.class))).thenReturn(new User());
		// when getById is invoked, return this user
		when(mockUserRepository.getById(user.getId())).thenReturn(user);

		// method to test on
		UserCreationDTO newValues = new UserCreationDTO("updatedUsername", "updatedEmail", "updatedPwd");
		UserDTO updatedUser = userService.updateUser(user.getId(), newValues);

		// assertions
		assertThat(updatedUser).isNotNull();
		assertThat(updatedUser.username).isEqualTo(newValues.username);
		assertThat(updatedUser.email).isEqualTo(newValues.email);

		// verify that the save method, when used once, saves an user
		// verify that getById, when used once, returns user 0
		verify(mockUserRepository, times(1)).save(any(User.class)); 
		verify(mockUserRepository, times(1)).getById(user.getId());
	}
}
