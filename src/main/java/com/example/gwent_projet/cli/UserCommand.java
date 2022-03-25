package com.example.gwent_projet.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gwent_projet.dto.CreateUser;
import com.example.gwent_projet.models.User;
import com.example.gwent_projet.services.MenuService;
import com.example.gwent_projet.services.UserService;
import com.example.gwent_projet.utils.consoleDisplay;

@Service
public class UserCommand {
	// command input for testing
	@Autowired
	private UserService userService;
	
	consoleDisplay consoleDisplay = new consoleDisplay();
	public List<User> adminList = new ArrayList<>();
	int createAccountIndex = 0; // needs to be separate
	int index = 0;

	public void createUser() {
		Pattern emailValidation = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"  + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher;

		consoleDisplay consoleDisplay = new consoleDisplay();
		CreateUser createUser = new CreateUser();
		
		printTitle("Create an account");

		// enter user name
		consoleDisplay.printToConsole("Enter an username for your user: ", true);
		createUser.username = consoleDisplay.readUserInput();

		// enter email
		do {
			consoleDisplay.printToConsole("Enter the email of your user: ", true);
			createUser.email = consoleDisplay.readUserInput();
			matcher = emailValidation.matcher(createUser.email);

			// validate email input with regex
			if (!matcher.matches()) {
				consoleDisplay.printToConsole("Please enter a valid email.", true);
			}
			//System.out.println(inputEmail +" : "+ matcher.matches());
		} while (!matcher.matches());

		// enter password
		// come back to this later
		consoleDisplay.printToConsole("Enter a password for your user: ", true);
		createUser.password = consoleDisplay.readUserInput();

		// confirm
		consoleDisplay.printToConsole("Create user " + createUser.username + " with these settings? (Yes/No)", true);
		// check for yes or no answer
		if (consoleDisplay.userYN() == true) {
			try {
				// create new user
				userService.createUser(createUser);

				// book existence check
				/* if () {
					consoleDisplay.printToConsole("Created user " + adminList.get(createAccountIndex).getUsername() + ".", true);
					createAccountIndex++;
				} else {
					consoleDisplay.printToConsole("Unexpected error, please try again.", true);
				} */
			} catch (Exception IndexOutOfBoundsException) {
				consoleDisplay.printToConsole(IndexOutOfBoundsException.getMessage(), true);
				return;
			}
		}
	}

	public void deleteAccount() {
		// exit if the list is empty
		printTitle("Delete an account");
		if (consoleDisplay.checkIfEmpty(adminList.size())) {
			return;
		}
		consoleDisplay.printToConsole("Enter the ID of the user to delete: ", true);

		// subtract one to account for displaced index
		index = consoleDisplay.readUserInputInteger() - 1;

		// exit if the user types zero
		// TBD: move to ConsoleDisplay
		if (index == -1) {
			consoleDisplay.printToConsole("Zero is not a valid ID.", true);
			// reinitialize to zero
			index = 0;
			return;
		}
		// exit if index exceeds array length
		// TBD: move to ConsoleDisplay
		if (index + 1 > adminList.size()) {
			consoleDisplay.printToConsole("No such user in database.", true);
			// reinitialize to zero
			index = 0;
			return;
		}

		for (int sweeper = 0; sweeper < adminList.size(); sweeper++) {
			if (sweeper == index) {
				String nameTemp = adminList.get(sweeper).getUsername();
				consoleDisplay.printToConsole("Do you wish to delete " + adminList.get(sweeper).getUsername() + " ?", true);
				if (consoleDisplay.userYN() == true) {
					try {
						adminList.remove(index);
						consoleDisplay.printToConsole("User \"" + nameTemp + "\" was deleted.", true);
					} catch (Exception IndexOutOfBoundsException) {
						consoleDisplay.printToConsole("Error while attempting to delete. Please try again.", true);
						return;
					}
				}
			} else {
				consoleDisplay.printToConsole("No such user in database.", true);
				return;
			}
		}
	}
	// wip
	public void editAccount() {
		printTitle("Edit an account");

		if (consoleDisplay.checkIfEmpty(adminList.size())) {
			return;
		}

		consoleDisplay.printToConsole("Enter the ID of the account to edit: ", true);

		// subtract one to account for displaced index
		index = consoleDisplay.readUserInputInteger() - 1;

		// exit if the user types zero
		// TBD: move to ConsoleDisplay
		if (index == -1) {
			consoleDisplay.printToConsole("Zero is not a valid ID.", true);
			// reinitialize to zero
			index = 0;
			return;
		}
		// exit if index exceeds array length
		// TBD: move to ConsoleDisplay
		if (index + 1 > adminList.size()) {
			consoleDisplay.printToConsole("No such user in database.", true);
			// reinitialize to zero
			index = 0;
			return;
		}

		String tempUserChoice = "";

		for (int sweeper = 0; sweeper <  adminList.size(); sweeper++) {
			do {
				if (sweeper == index) {
					// ugly menu selection because apparently I'm not supposed to care about this
					consoleDisplay.printToConsole("Editing user n° " + (index + 1) + " named " + (adminList.get(sweeper).getUsername()), true);
					consoleDisplay.printToConsole("1. Edit username", true);
					consoleDisplay.printToConsole("2. Edit email", true);
					consoleDisplay.printToConsole("3. Edit password", true);
					consoleDisplay.printToConsole("4. Abort", true);
					index = consoleDisplay.readUserInputInteger();
					do {
						if (index == 1) {
							consoleDisplay.printToConsole("Enter new username: ", true);
							tempUserChoice = consoleDisplay.readUserInput();
							consoleDisplay.printToConsole("Replace \"" + adminList.get(sweeper).getUsername() + "\" with \"" + tempUserChoice + " ?", true);
							if (consoleDisplay.userYN() == true) {
								try {
									adminList.get(sweeper).setUsername(tempUserChoice);
									consoleDisplay.printToConsole("Username replaced.", true);
									return;
								} catch (Exception IndexOutOfBoundsException) {
									consoleDisplay.printToConsole("Error while attempting to modify. Please try again.", true);
									// reinitialize to zero
									index = 0;
									return;
								}
							} else {
								// reinitialize to zero
								index = 0;
								return;
							}
						} else if (index == 2) {
							consoleDisplay.printToConsole("Enter new email: ", true);
							tempUserChoice = consoleDisplay.readUserInput();
							consoleDisplay.printToConsole("Replace \"" + adminList.get(sweeper).getEmail() + "\" with \"" + tempUserChoice + " ?", true);
							if (consoleDisplay.userYN() == true) {
								try {
									adminList.get(sweeper).setEmail(tempUserChoice);
									consoleDisplay.printToConsole("Email replaced.", true);
									return;
								} catch (Exception IndexOutOfBoundsException) {
									consoleDisplay.printToConsole("Error while attempting to modify. Please try again.", true);
									// reinitialize to zero
									index = 0;
									return;
								}
							} else {
								// reinitialize to zero
								index = 0;
								return;
							}
						} else if (index == 3) {
							consoleDisplay.printToConsole("Enter new password: ", true);
							tempUserChoice = consoleDisplay.readUserInput();
							consoleDisplay.printToConsole("Replace \"" + adminList.get(sweeper).getPassword() + "\" with \"" + tempUserChoice + " ?", true);
							if (consoleDisplay.userYN() == true) {
								try {
									adminList.get(sweeper).setPassword(tempUserChoice);
									consoleDisplay.printToConsole("Password replaced.", true);
									return;
								} catch (Exception IndexOutOfBoundsException) {
									consoleDisplay.printToConsole("Error while attempting to modify. Please try again.", true);
									// reinitialize to zero
									index = 0;
									return;
								}
							} else {
								// reinitialize to zero
								index = 0;
								return;
							}
						} else if (index == 4) {
							consoleDisplay.printToConsole("Aborting.", true);
							index = 0;
							return;
						} else {
							consoleDisplay.printToConsole("Invalid input.", true);
						}
					} while (index != 4);
				} else {
					sweeper++;
				}
			} while (sweeper <=  adminList.size());
			// reinitialize to zero
			index = 0;
		}
	}

	public void listAccount() {
		printTitle("List of all accounts");

		if (consoleDisplay.checkIfEmpty(adminList.size())) {
			return;
		}

		for (int sweeper = 0; sweeper <  adminList.size(); sweeper++) {
			// +1 so that the displayed index starts at 1
			// must account for this during operations.
			consoleDisplay.printToConsole((sweeper + 1) + ". " +  adminList.get(sweeper).getUsername(), true);
		}
	}

	// menu handling -------------------------------------------------------------------
	// main method that runs the menu
	public void run() {

		String answer;

		do {
			MenuService[] elements = MenuService.values(); 

			answer = printMenu(elements);
			consoleDisplay.consoleLineBreak();

			// handle the user action
			handleAction(answer);
		} while (!answer.equalsIgnoreCase(MenuService.ActionConstants.EXIT_ID)); // loop while until user wants to exit
	}

	// print the menu
	private String printMenu(MenuService[] menu) {
		boolean rightAnswer = false;
		String answer = "";
		int index = 1;

		printTitle("Admin account management");
		do {
			// print the option menu
			// for each enum listed in MenuService, print to console
			for (MenuService action : menu) {
				// consoleDisplay.printToConsole(index++ + ". " + action.toString(), true);
				// idk it was like this where i picked it up from but it prints the enum name itself instead of its value
				// this works
				consoleDisplay.printToConsole(index++ + ". " + action.getTitle(), true);
			}
			// reset index to 1
			index = 1;
			consoleDisplay.printToConsole("Select an action : ", false);

			// ask user answer
			answer = consoleDisplay.readUserInput();

			// if there is an input and it matches an entry in the enum, return true
			if (MenuService.containsAction(answer)) {
				rightAnswer = true;
			} else {
				consoleDisplay.printToConsole("Invalid input.", true);
			}
		} while (!rightAnswer);

		return answer;
	}

	// handle actions per user choice
	// gets the return value of "answer" from printMenu, and if true: execute the associated method
	private void handleAction(String answer) {

		if (answer.equalsIgnoreCase(MenuService.CREATE_ACCOUNT.getValue())) {
			createUser();
		}

		if (answer.equalsIgnoreCase(MenuService.DELETE_ACCOUNT.getValue())) {
			deleteAccount();
		}

		if (answer.equalsIgnoreCase(MenuService.EDIT_ACCOUNT.getValue())) {
			editAccount();
		}

		if (answer.equalsIgnoreCase(MenuService.LIST_ACCOUNT.getValue())) {
			listAccount();
		}
	}

	// print title between two separators
	public void printTitle(String title) {

		// consoleDisplay.consoleLineBreak(); 
		consoleDisplay.separator();
		consoleDisplay.printToConsole(title, true);
		consoleDisplay.separator();
		// consoleDisplay.consoleLineBreak();
	}


}
