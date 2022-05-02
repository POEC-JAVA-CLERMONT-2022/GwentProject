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
		
		// define a list to simulate the deleteById method on
		List<User> repoList = new ArrayList<>();
		repoList.add(user);
		
		// define what the deleteById method should do when invoked
		// this will:
		// -- execute a method that returns an Anwser<List<User>> on deleteById invocation
		// -- the method removes the item at the index of "user", then returns that updated list
		// -- then that list is thrown back to us.
		Mockito.lenient().doAnswer(new Answer<List<User>>() {
			public List<User> answer(InvocationOnMock invocation) throws Throwable {
				repoList.remove(repoList.indexOf(user));
				return repoList;
			}
		}).when(mockUserRepository).deleteById(user.getId());
		
		// assert that there is something in the list
		assertThat(repoList.isEmpty()).isFalse();
		
		// method to test
		userService.deleteUserById(user.getId());
		
		// assertions
		// assert that there is nothing in the list
		assertThat(repoList.isEmpty()).isTrue();
		verify(mockUserRepository).deleteById(user.getId());

		// TODO: test when ID doesn't exist
	}
}
