package com.example.gwent_projet.unit.service.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.gwent_projet.entity.user.User;
import com.example.gwent_projet.repository.UserRepository;
import com.example.gwent_projet.services.impl.UserServiceImpl;

@SpringBootTest
public class DeleteUserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	private UserRepository mockUserRepository;
	
	// --------------------------------------------------------------------------------
	
	@Test
	@DisplayName ("User - Delete")
	public void deleteUserByIdTest() {
		System.out.println("--------------");
		System.out.println("User - Delete");
		System.out.println("--------------");

		// when .save is used on a mocked userRepository, return a new User
		when(mockUserRepository.save(Mockito.any(User.class))).thenReturn(new User(0, null, null, null));
		
		// new user to test on
		User user = new User(0, "","","");
		user.setId(0l);
		
		mockUserRepository.save(user);
		
		// assertion
		// check that user was created
		assertThat(mockUserRepository.getById(user.getId())).isNotNull();
		
		// method to test
		// delete used at its id
		userService.deleteUserById(user.getId());
		
		// assertions
		Mockito.verify(mockUserRepository).deleteById(user.getId());
		assertThat(mockUserRepository.getById(user.getId())).isNull();
		
		// TODO: test when ID doesn't exist
	}
}
