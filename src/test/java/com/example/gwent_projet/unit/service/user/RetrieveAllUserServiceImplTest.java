package com.example.gwent_projet.unit.service.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

	private EasyRandom RNGenerator = new EasyRandom();
	Random random = new Random();

	// --------------------------------------------------------------------------------

	@Test
	@DisplayName ("User - Retrieve All")
	public void getAllUsersTest() {
		System.out.println("--------------");
		System.out.println("User - Retrieve All");
		System.out.println("--------------");

		// when .save is used on a mocked userRepository, return a new User
		when(mockUserRepository.save(Mockito.any(User.class))).thenReturn(new User(0, null, null, null));
		
		// random length for new table
		Long tableLength = random.nextLong(20);
		// ensure that it is not null
		if (tableLength == 0l) {
			tableLength++;
		}

		// populate list
		for (Long sweeper = 0l; sweeper < tableLength; sweeper++) {
			User tempUser = RNGenerator.nextObject(User.class);
			tempUser.setId(sweeper);
			tempUser.setRole(0);

			System.out.println(tempUser.getUsername());
			System.out.println(tempUser.getEmail());
			System.out.println(tempUser.getPassword());
			System.out.println(tempUser.getRole());
			System.out.println(tempUser.getId());

			mockUserRepository.save(tempUser);

			System.out.println("--------------");

			System.out.println(mockUserRepository.getById(sweeper).getUsername());
			System.out.println(mockUserRepository.getById(sweeper).getEmail());
			System.out.println(mockUserRepository.getById(sweeper).getPassword());
			System.out.println(mockUserRepository.getById(sweeper).getRole());
			System.out.println(mockUserRepository.getById(sweeper).getId());

		}

		// get all users from the mocked repository
		List<UserDTO> expected = userService.getAllUsers();

		System.out.println(expected.get(0).getUsername());
		System.out.println(expected.get(0).getEmail());

		// assertions
		// check that the repository has users
		verify(mockUserRepository).findAll();
		assertThat(expected).isNotEmpty();
		assertThat(expected.size()).isEqualTo(mockUserRepository.count());
		// assert each entry individually
		int i = 0;
		for (Long sweeper = 0l; sweeper < tableLength; sweeper++) {
			assertThat(mockUserRepository.getById(sweeper).getUsername()).isNotEmpty();
			assertThat(mockUserRepository.getById(sweeper).getEmail()).isNotEmpty();
			assertThat(mockUserRepository.getById(sweeper).getUsername()).isEqualTo(expected.get(i).username);
			assertThat(mockUserRepository.getById(sweeper).getEmail()).isEqualTo(expected.get(i).email);
			i++;
		}
	}
}
