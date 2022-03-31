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
import com.example.gwent_projet.models.User;
import com.example.gwent_projet.repository.UserRepository;
import com.example.gwent_projet.services.UserService;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	// --------------------------------------------------------------------------------

	@BeforeEach
	public User initUser() {
		EasyRandom RNGenerator = new EasyRandom();
		User user = RNGenerator.nextObject(User.class);
		return user;
	}
	
	@BeforeEach
	public Long initRepo() {
		// random values to fill our objects
		EasyRandom RNGenerator = new EasyRandom();
		
		// random index for table length
		Random random = new Random();
		Long tableLength = random.nextLong(50);
		
		// populate repository
		for (int sweeper = 0; sweeper < tableLength; sweeper++) {
			User newUser = RNGenerator.nextObject(User.class);
			userRepository.save(newUser);
		}
		return tableLength;
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
		// initialize test data
		User testUser = initUser();
		
		// method to test
		// create the testUser in the repository
		UserDTO newUser = userService.createUser(testUser);
		
		// assertions
		// assert that data is not empty and corresponding fields are matching
		assertThat(newUser).isNotNull();
		assertThat(newUser.username).isNotNull();
		assertThat(newUser.email).isNotNull();
		assertThat(newUser.username).isEqualTo(testUser.getUsername());
		assertThat(newUser.email).isEqualTo(testUser.getEmail());
		
		// DB entry
		// get the user just saved at this ID from the repository
		User repoReturnValue = this.userRepository.findById(testUser.getId()).orElse(null);
		// then test all fields with userAssertions
		userAssertions(repoReturnValue, testUser);
	}
	
	// --------------------------------------------------------------------------------
	
	@Test
	@DisplayName ("User - Retrieve All")
	public void getAllUsersTest() {
		// get all users from the repository
		List<UserDTO> users = new ArrayList<UserDTO>();
		users.addAll(userService.getAllUsers());
		
		assertThat(users).isNotEmpty();
		
		// assert that both arrays are of the same size
		assertThat(users.size()).isEqualTo(userRepository.findAll().size());
		
		// for each entry in the list, check if it matches with its corresponding entry in the repo
		int shortSweeper = 0;
		for (Long longSweeper = 0l; longSweeper < users.size(); longSweeper++) {
			Assertions.assertEquals(users.get(shortSweeper).username, userRepository.getById(longSweeper).getUsername());
			Assertions.assertEquals(users.get(shortSweeper).email, userRepository.getById(longSweeper).getEmail());
			
			// assertThat(users.get(shortSweeper).username).isEqualTo(userRepository.getById(longSweeper).getUsername());
			// assertThat(users.get(shortSweeper).email).isEqualTo(userRepository.getById(longSweeper).getEmail());
			shortSweeper++;
		}
	}
	
	// --------------------------------------------------------------------------------
	
	@Test
	@DisplayName ("User - Retrieve One")
	public void getUserByIdTest() {
		// get the table length
		Long tableLength = initRepo();
		
		// random index to search for, that is within table length boundaries
		Random random = new Random();
		Long RNGindex = random.nextLong(tableLength);
		
		// method to test
		UserDTO searchResult = userService.getUserById(RNGindex);
		// get User object from repository
		User repoReturnValue = this.userRepository.findById(RNGindex).orElse(null);
		
		// assertions
		// assert that data is not empty and corresponding fields are matching
		assertThat(searchResult).isNotNull();
		assertThat(searchResult.username).isNotNull();
		assertThat(searchResult.email).isNotNull();
		assertThat(searchResult.username).isEqualTo(repoReturnValue.getUsername());
		assertThat(searchResult.email).isEqualTo(repoReturnValue.getEmail());
	}

	
	// --------------------------------------------------------------------------------
	
	@Test
	@DisplayName ("User - Deletion")
	public void deleteUserByIdTest() {
		// get the table length
		Long tableLength = initRepo();
		
		// random index to search for, that is within table length boundaries
		Random random = new Random();
		Long RNGindex = random.nextLong(tableLength);
		
		// get User from repository
		User repoReturnValue = this.userRepository.findById(RNGindex).orElse(null);
		// delete this user at this ID
		userService.deleteUserById(RNGindex);
		// get non existing User from repository
		User repoReturnValueNull = this.userRepository.findById(RNGindex).orElse(null);
		
		// assertions
		assertThat(repoReturnValueNull).isNull();
		// wip........
		
	}
	
	// --------------------------------------------------------------------------------
	
	@Test
	@DisplayName ("User - Update")
	public void updateUserTest() {
		// wip......
	}
}
