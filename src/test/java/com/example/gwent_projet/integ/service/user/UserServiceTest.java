package com.example.gwent_projet.integ.service.user;

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
// import com.example.gwent_projet.utils.consoleDisplay;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	UserRepository userRepository;

	private Long tableLength;

	private EasyRandom RNGenerator = new EasyRandom();

	// private consoleDisplay consoleDisplay = new consoleDisplay();
	
	private User user;

	// --------------------------------------------------------------------------------
	@BeforeEach
	public void initRepo() {
		/*
		consoleDisplay.separator();
		System.out.println("initRepo - List of all users");
		consoleDisplay.separator();
		*/
		// random index for table length
		Random random = new Random();
		tableLength = random.nextLong(20);
		// ensure that it is not null
		if (tableLength == 0l) {
			tableLength++;
		}

		// System.out.println("Table length: " + tableLength);

		// populate repository
		for (Long sweeper = 0l; sweeper < tableLength; sweeper++) {
			// new random user
			User tempUser = RNGenerator.nextObject(User.class);
			tempUser.setId(null);
			//tempUser.setRoles();
			
			tempUser = userRepository.save(tempUser);
			
			// get the first user of each instance of the repo
			// this will be used for our tests
			if (user == null) {
				user = tempUser;
			}
			
			/*
			User repoReturnValue = userRepository.findById(tempUser.getId()).orElse(null);
			System.out.println("Name: " + repoReturnValue.getUsername());
			System.out.println("Email: " + repoReturnValue.getEmail());
			System.out.println("Password: " + repoReturnValue.getPassword());
			System.out.println("Role: " + repoReturnValue.getRole());
			System.out.println("Id: " + repoReturnValue.getId());
			*/
		}
		/*
		System.out.println("Total repo size: " + userRepository.count());
		System.out.println("glb user: " + user.getId() + " " + user.getUsername());
		*/
	}
	
	@AfterEach
	public void cleanupRepo() {
		userRepository.deleteAll();
	}
	
	/*
	@AfterEach
	public void displayEnd() {
		System.out.println("----- end of task ------");
	}
	*/

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
		/*
		consoleDisplay.separator();
		System.out.println("User - Creation");
		consoleDisplay.separator();
		*/
		
		// create a new user
		UserCreationDTO creationUser = new UserCreationDTO("newUsername", "newEmail", "newPassword");
		// method to test
		// insert creationUser in repository and save the result in a DTO
		UserDTO newUser = userService.createUser(creationUser);
		
		// assertions
		// assert that data is not empty and corresponding fields are matching
		assertThat(newUser).isNotNull();
		assertThat(newUser.username).isNotNull();
		assertThat(newUser.email).isNotNull();
		assertThat(newUser.username).isEqualTo(creationUser.getUsername());
		assertThat(newUser.email).isEqualTo(creationUser.getEmail());

		// formula is:
		// -- ID of the first user + (total size of present repository - the user we just created) 
		// -- = the last user in repository
		User repoReturnValue = userRepository.findById(user.getId() + (userRepository.count() - 1)).orElse(null);
		
		// then test all fields with userAssertions
		userCreationAssertions(repoReturnValue, creationUser);
	}

	// --------------------------------------------------------------------------------

	@Test
	@DisplayName ("User - Retrieve All")
	public void getAllUsersTest() {
		/*
		consoleDisplay.separator();
		System.out.println("User - Retrieve All");
		consoleDisplay.separator();
		*/

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
		/*
		consoleDisplay.separator();
		System.out.println("User - Retrieve One");
		consoleDisplay.separator();
		*/

		// get last user in repository
		UserDTO searchResult = userService.getUserById(user.getId());

		userDTOAssertions(searchResult, userService.getUserById(user.getId()));
	}


	// --------------------------------------------------------------------------------
	@Test
	@DisplayName ("User - Deletion")
	public void deleteUserByIdTest() {
		/*
		consoleDisplay.separator();
		System.out.println("User - Deletion");
		consoleDisplay.separator();
		*/

		// get last user in repository
		Long repoSizeBefore = userRepository.count();
		User searchResultBefore = userRepository.findById(user.getId()).orElse(null);

		// method to test
		userService.deleteUserById(user.getId());

		Long repoSizeAfter = userRepository.count();
		User searchResultAfter = userRepository.findById(user.getId()).orElse(null);

		// assertions
		assertThat(searchResultBefore).isNotEqualTo(searchResultAfter);
		assertThat(repoSizeBefore).isNotEqualTo(repoSizeAfter);
	}

	// --------------------------------------------------------------------------------
	@Test
	@DisplayName ("User - Update")
	public void updateUserTest() {
		/*
		consoleDisplay.separator();
		System.out.println("User - Update");
		consoleDisplay.separator();
		 */
		User updatedUserBefore = userRepository.findById(user.getId()).orElse(null);

		// new user to test the update method
		UserCreationDTO updatedUser = new UserCreationDTO("testUsername", "testEmail", "testPassword");

		// method to be tested
		userService.updateUser(user.getId(), updatedUser);

		User updatedUserAfter = userRepository.findById(user.getId()).orElse(null);

		// assertions
		assertThat(updatedUserBefore.getUsername()).isNotEqualTo(updatedUserAfter.getUsername());
		assertThat(updatedUserBefore.getEmail()).isNotEqualTo(updatedUserAfter.getEmail());
	}
}
