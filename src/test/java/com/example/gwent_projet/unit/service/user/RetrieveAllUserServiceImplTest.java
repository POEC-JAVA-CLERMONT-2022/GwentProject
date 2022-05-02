package com.example.gwent_projet.unit.service.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
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
public class RetrieveAllUserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	private UserRepository mockUserRepository;

	// --------------------------------------------------------------------------------

	@Test
	@DisplayName ("User - Retrieve All")
	public void getAllUsersTest() {
		// handful of users to test on
		User user1 = new User(0, "testUsername1","testEmail1","testPwd1");
		user1.setId(0l);
		User user2 = new User(0, "testUsername2","testEmail2","testPwd2");
		user2.setId(1l);
		User user3 = new User(0, "testUsername3","testEmail3","testPwd3");
		user3.setId(2l);

		// populate new list
		List<User> repoList = new ArrayList<>();
		Collections.addAll(repoList, user1, user2, user3);

		// when findAll is invoked, return a list of users
		when(mockUserRepository.findAll()).thenReturn(repoList);

		// method to test
		List<UserDTO> users = new ArrayList<UserDTO>();
		users.addAll(userService.getAllUsers());

		// assertions
		assertThat(users).isNotNull();
		for (int sweeper = 0; sweeper < users.size(); sweeper++) {
			assertThat(users.get(sweeper).username).isEqualTo(repoList.get(sweeper).getUsername());
			assertThat(users.get(sweeper).email).isEqualTo(repoList.get(sweeper).getEmail());
		}
		
		// verify that findAll works when used once 
		verify(mockUserRepository, times(1)).findAll();
	}
}
