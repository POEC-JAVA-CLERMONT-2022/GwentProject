package gwent.main;

import gwent.service.UserService;
import gwent.util.consoleDisplay;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UserService userService = new UserService();
		consoleDisplay consoleDisplay = new consoleDisplay();
		
		// execute method "run" that prints the menu and handles actions per user choice
		userService.run();
		// line break because Java sucks at handling line breaks
		consoleDisplay.consoleLineBreak();
		
	}

}
