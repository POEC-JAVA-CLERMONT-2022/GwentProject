package com.example.gwent_projet;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.gwent_projet.dto.UserDTO;
import com.example.gwent_projet.entity.User;
import com.example.gwent_projet.repository.UserRepository;
import com.example.gwent_projet.services.UserService;
import com.example.gwent_projet.utils.consoleDisplay;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	private User globalUser = new User();

	private Long tableLength;
	
	private EasyRandom RNGenerator = new EasyRandom();

	private consoleDisplay consoleDisplay = new consoleDisplay();

	// --------------------------------------------------------------------------------

	@BeforeEach
	public void initUser() {
		globalUser = RNGenerator.nextObject(User.class);
		globalUser.setId(null);
		globalUser.setRole(0);
	}

	@BeforeEach
	public void initRepo() {
		// random index for table length
		Random random = new Random();
		tableLength = random.nextLong(50);
		
		
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
			
			userRepository.save(tempUser);
			
			User repoReturnValue = userRepository.findById(tempUser.getId()).orElse(null);
			
			System.out.println("Name: " + repoReturnValue.getUsername());
			System.out.println("Email: " + repoReturnValue.getEmail());
			System.out.println("Password: " + repoReturnValue.getPassword());
			System.out.println("Role: " + repoReturnValue.getRole());
			System.out.println("Id: " + repoReturnValue.getId());
			
		}
	}

	@AfterEach
	public void cleanupData() {
		userRepository.deleteAll();
	}

	// --------------------------------------------------------------------------------

	public void userAssertions(User repoReturnValue, User testUser) {
		// for all variables, test if equal to input value
		assertThat(repoReturnValue).isNotNull();
		assertThat(repoReturnValue.getRole()).isNotNull();
		assertThat(repoReturnValue.getRole()).isEqualTo(testUser.getRole());
		assertThat(repoReturnValue.getUsername()).isNotNull();
		assertThat(repoReturnValue.getUsername()).isEqualTo(testUser.getUsername());
		assertThat(repoReturnValue.getEmail()).isNotNull();
		assertThat(repoReturnValue.getEmail()).isEqualTo(testUser.getEmail());
		assertThat(repoReturnValue.getPassword()).isNotNull();
		assertThat(repoReturnValue.getPassword()).isEqualTo(testUser.getPassword());
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
		UserDTO newUser = userService.createUser(globalUser);

		// assertions
		// assert that data is not empty and corresponding fields are matching
		assertThat(newUser).isNotNull();
		assertThat(newUser.username).isNotNull();
		assertThat(newUser.email).isNotNull();
		assertThat(newUser.username).isEqualTo(globalUser.getUsername());
		assertThat(newUser.email).isEqualTo(globalUser.getEmail());

		System.out.println(newUser.username);
		System.out.println(newUser.email);
		System.out.println(globalUser.getId());
		
		
		// DB entry
		// get the user just saved at this ID from the repository
		User repoReturnValue = userRepository.findById(globalUser.getId()).orElse(null);
		// then test all fields with userAssertions
		userAssertions(repoReturnValue, globalUser);

		// clean up the repository
		cleanupData();
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

		// assert that both arrays are of the same size
		assertThat(users.size()).isEqualTo(userRepository.findAll().size());

		// for each entry in the list, check if it matches with its corresponding entry in the repo
		int shortSweeper = 0;
		for (Long longSweeper = 0l; longSweeper < users.size(); longSweeper++) {
			// Assertions.assertEquals(users.get(shortSweeper).username, userRepository.getById(longSweeper).getUsername());
			// Assertions.assertEquals(users.get(shortSweeper).email, userRepository.getById(longSweeper).getEmail());

			assertThat(users.get(shortSweeper).username).isEqualTo(userRepository.getById(longSweeper).getUsername());
			assertThat(users.get(shortSweeper).email).isEqualTo(userRepository.getById(longSweeper).getEmail());
			shortSweeper++;
		}
		// clean up the repository
		cleanupData();
	}

	// --------------------------------------------------------------------------------

	@Test
	@DisplayName ("User - Retrieve One")
	public void getUserByIdTest() {
		consoleDisplay.separator();
		System.out.println("User - Retrieve One");
		consoleDisplay.separator();


		System.out.println("Table length: " + tableLength);
		
		// method to test
		UserDTO searchResult = userService.getUserById(tableLength - 1);
		
		System.out.println("Name: " + searchResult.username);
		System.out.println("Email: " + searchResult.email);
		
		// get User object from repository
		User repoReturnValue = userRepository.findById(tableLength - 1).orElse(null);
		
		System.out.println("Name: " + repoReturnValue.getUsername());
		System.out.println("Email: " + repoReturnValue.getEmail());

		// assertions
		// assert that data is not empty and corresponding fields are matching
		assertThat(searchResult).isNotNull();
		assertThat(searchResult.username).isNotNull();
		assertThat(searchResult.email).isNotNull();
		assertThat(searchResult.username).isEqualTo(repoReturnValue.getUsername());
		assertThat(searchResult.email).isEqualTo(repoReturnValue.getEmail());
		
		// clean up the repository
		cleanupData();
	}


	// --------------------------------------------------------------------------------

	@Test
	@DisplayName ("User - Deletion")
	public void deleteUserByIdTest() {
		consoleDisplay.separator();
		System.out.println("User - Deletion");
		consoleDisplay.separator();

		// get User from repository
		User repoReturnValue = userRepository.findById(tableLength - 1).orElse(null);

		// assert that this is the same user
		assertThat((userRepository.findById(tableLength - 1).orElse(null)).getUsername()).isEqualTo(repoReturnValue.getUsername());
		assertThat((userRepository.findById(tableLength - 1).orElse(null)).getEmail()).isEqualTo(repoReturnValue.getEmail());
		
		// method to be tested
		// delete this user at this ID
		userService.deleteUserById(tableLength - 1);
		// get non existing User from repository
		User repoReturnValueNull = userRepository.findById(tableLength - 1).orElse(null);

		// assertions
		assertThat(repoReturnValueNull).isNull();
		assertThat(repoReturnValueNull).isNotEqualTo(repoReturnValue);
		
		// clean up the repository
		cleanupData();
	}

	// --------------------------------------------------------------------------------

	@Test
	@DisplayName ("User - Update")
	public void updateUserTest() {
		consoleDisplay.separator();
		System.out.println("User - Update");
		consoleDisplay.separator();
		
		// random index to search for, that is within table length boundaries
		Random random = new Random();
		Long RNGindex = random.nextLong(tableLength);

		// get User from repository
		User repoReturnValue = userRepository.findById(RNGindex).orElse(null);

		// assert that this is the same user
		assertThat(userRepository.findById(RNGindex).orElse(null)).isEqualTo(repoReturnValue);

		// new user to test the update method
		User testUser = new User(0, "updatedName", "updatedEmail", "updatedPassword");

		// method to be tested
		userService.updateUser(RNGindex, testUser);

		// test all fields with userAssertions
		userAssertions(repoReturnValue, testUser);
		
		// clean up the repository
		cleanupData();
	}
}
