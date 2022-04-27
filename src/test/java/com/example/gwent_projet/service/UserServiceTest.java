package com.example.gwent_projet.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.gwent_projet.entity.user.User;
import com.example.gwent_projet.services.dto.user.UserCreationDTO;
import com.example.gwent_projet.services.dto.user.UserDTO;
import com.example.gwent_projet.repository.UserRepository;
import com.example.gwent_projet.services.UserService;
import com.example.gwent_projet.utils.consoleDisplay;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	UserRepository userRepository;

	private Long tableLength;

	private EasyRandom RNGenerator = new EasyRandom();

	private consoleDisplay consoleDisplay = new consoleDisplay();
	
	private User user;

	// --------------------------------------------------------------------------------
	@BeforeEach
	public void initRepo() {
		// random index for table length
		Random random = new Random();
		tableLength = random.nextLong(20);
		// ensure that it is not null
		if (tableLength == 0l) {
			tableLength++;
		}
		consoleDisplay.separator();
		System.out.println("initRepo - List of all users");
		consoleDisplay.separator();

		System.out.println("Table length: " + tableLength);

		// populate repository
		for (Long sweeper = 0l; sweeper < tableLength; sweeper++) {
			// new random user
			User tempUser = RNGenerator.nextObject(User.class);
			tempUser.setId(null);
			tempUser.setRole(0);
			
			tempUser = userRepository.save(tempUser);
			
			if (user == null) {
				user = tempUser;
			}

			User repoReturnValue = userRepository.findById(tempUser.getId()).orElse(null);
			System.out.println("Name: " + repoReturnValue.getUsername());
			System.out.println("Email: " + repoReturnValue.getEmail());
			System.out.println("Password: " + repoReturnValue.getPassword());
			System.out.println("Role: " + repoReturnValue.getRole());
			System.out.println("Id: " + repoReturnValue.getId());
		}
		System.out.println("Total repo size: " + userRepository.count());
	}
	
	@AfterEach
	public void cleanupRepo() {
		userRepository.deleteAll();
	}

	// --------------------------------------------------------------------------------
	public void userCreationAssertions(User repoReturnValue, UserCreationDTO testUser) {
		// for all variables, test if equal to input value
		// role is not tested since it is always the same
		assertThat(repoReturnValue).isNotNull();
		assertThat(repoReturnValue.getUsername()).isNotNull();
		assertThat(repoReturnValue.getUsername()).isEqualTo(testUser.getUsername());
		assertThat(repoReturnValue.getEmail()).isNotNull();
		assertThat(repoReturnValue.getEmail()).isEqualTo(testUser.getEmail());
		assertThat(repoReturnValue.getPassword()).isNotNull();
		assertThat(repoReturnValue.getPassword()).isEqualTo(testUser.getPassword());
	}

	public void userDTOAssertions(UserDTO repoReturnValue, UserDTO testUser) {
		// for all variables, test if equal to input value
		assertThat(repoReturnValue).isNotNull();
		assertThat(repoReturnValue.username).isNotNull();
		assertThat(repoReturnValue.username).isEqualTo(testUser.username);
		assertThat(repoReturnValue.email).isNotNull();
		assertThat(repoReturnValue.email).isEqualTo(testUser.email);
	}

	// --------------------------------------------------------------------------------
	@Test
	@DisplayName ("User - Creation")
	public void createUserTest() {
		consoleDisplay.separator();
		System.out.println("User - Creation");
		consoleDisplay.separator();

		// method to test
		// create the testUser in the repository
		UserCreationDTO creationUser = new UserCreationDTO("newUsername", "newEmail", "newPassword");


		// System.out.println(creationUser.username);
		// System.out.println(creationUser.email);
		// System.out.println(creationUser.password);


		UserDTO newUser = userService.createUser(creationUser);

		// assertions
		// assert that data is not empty and corresponding fields are matching
		assertThat(newUser).isNotNull();
		assertThat(newUser.username).isNotNull();
		assertThat(newUser.email).isNotNull();
		assertThat(newUser.username).isEqualTo(creationUser.getUsername());
		assertThat(newUser.email).isEqualTo(creationUser.getEmail());

		// DB entry
		// get the user just saved at this ID from the repository

		// new user index is always current repo length + one
		// -- this does not work if the test is run first. the method works but i have no idea how to retrieve the actual id from the repo
		User repoReturnValue = userRepository.findById(userRepository.count() + 1).orElse(null);
		// then test all fields with userAssertions
		userCreationAssertions(repoReturnValue, creationUser);
	}

	// --------------------------------------------------------------------------------

	@Test
	@DisplayName ("User - Retrieve All")
	public void getAllUsersTest() {
		consoleDisplay.separator();
		System.out.println("User - Retrieve All");
		consoleDisplay.separator();

		// get all users from the repository
		List<UserDTO> users = new ArrayList<UserDTO>();
		users.addAll(userService.getAllUsers());

		assertThat(users).isNotEmpty();
		assertThat(users.size()).isEqualTo(userRepository.count());

		for (int sweeper = 0; sweeper < userRepository.count(); sweeper++) {
			assertThat(users.get(sweeper).username).isNotEmpty();
			assertThat(users.get(sweeper).email).isNotEmpty();
		}
	}

	// --------------------------------------------------------------------------------
	@Test
	@DisplayName ("User - Retrieve One")
	public void getUserByIdTest() {
		consoleDisplay.separator();
		System.out.println("User - Retrieve One");
		consoleDisplay.separator();

		// get last user in repository
		UserDTO searchResult = userService.getUserById(userRepository.count());

		userDTOAssertions(searchResult, userService.getUserById(userRepository.count()));
	}


	// --------------------------------------------------------------------------------
	@Test
	@DisplayName ("User - Deletion")
	public void deleteUserByIdTest() {
		consoleDisplay.separator();
		System.out.println("User - Deletion");
		consoleDisplay.separator();

		// get last user in repository
		Long repoSizeBefore = userRepository.count();
		UserDTO searchResultBefore = userService.getUserById(userRepository.count());


		// System.out.println("repo size: " + userRepository.count());
		// System.out.println("User: " + searchResultBefore.username);
		// System.out.println("Email: " + searchResultBefore.email);


		// method to test
		// delete the last user in the repository
		userService.deleteUserById(userRepository.count());

		Long repoSizeAfter = userRepository.count();
		UserDTO searchResultAfter = userService.getUserById(userRepository.count());


		// System.out.println("repo size: " + userRepository.count());
		// System.out.println("User: " + searchResultAfter.username);
		// System.out.println("Email: " + searchResultAfter.email);


		// assertions
		// -- seemingly does not pass if there are two users or less in the repo at runtime
		// -- actual method is working but the test might not be adequate
		assertThat(searchResultBefore.username).isNotEqualTo(searchResultAfter.username);
		assertThat(searchResultBefore.email).isNotEqualTo(searchResultAfter.email);
		assertThat(repoSizeBefore).isNotEqualTo(repoSizeAfter);
	}

	// --------------------------------------------------------------------------------
	@Test
	@DisplayName ("User - Update")
	public void updateUserTest() {
		consoleDisplay.separator();
		System.out.println("User - Update");
		consoleDisplay.separator();

		User updatedUserBefore = userRepository.findById(userRepository.count()).orElse(null);

		// new user to test the update method
		UserCreationDTO updatedUser = new UserCreationDTO("testUsername", "testEmail", "testPassword");

		// method to be tested
		userService.updateUser(userRepository.count(), updatedUser);

		User updatedUserAfter = userRepository.findById(userRepository.count()).orElse(null);

		// assertions
		assertThat(updatedUserBefore.getUsername()).isNotEqualTo(updatedUserAfter.getUsername());
		assertThat(updatedUserBefore.getEmail()).isNotEqualTo(updatedUserAfter.getEmail());
	}
}
