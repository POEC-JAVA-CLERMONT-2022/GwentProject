package com.example.gwent_projet.unit.service.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.gwent_projet.entity.user.User;
import com.example.gwent_projet.repository.UserRepository;
import com.example.gwent_projet.services.dto.user.UserCreationDTO;
import com.example.gwent_projet.services.dto.user.UserDTO;
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
		// new user to test on
		User user = new User(0, "testUsername","testEmail","testPwd");
		user.setId(0l);
		
		List<User> repoList = new ArrayList<>();
		repoList.add(user);
		
		Mockito.lenient().doAnswer(new Answer<List<User>>() {
			public List<User> answer(InvocationOnMock invocation) throws Throwable {
				repoList.remove(repoList.indexOf(user));
				return repoList;
			}
		}).when(mockUserRepository).deleteById(user.getId());
		
		// when .save is used on a mocked userRepository, return a new User
		// when(mockUserRepository.save(any(User.class))).thenReturn(new User(0, null, null, null));
		// doReturn(true).when(mockUserRepository).deleteById(user.getId()); // impossible condition
		
		// method to test
		userService.deleteUserById(user.getId());
		
		// assertions
		// ?
		// assertThat(mockUserRepository.existsById(user.getId())).isFalse(); // useless?
		verify(mockUserRepository).deleteById(user.getId());

		// TODO: test when ID doesn't exist
	}
}
