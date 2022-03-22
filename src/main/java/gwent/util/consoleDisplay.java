package gwent.util;

import java.util.Scanner;

public class consoleDisplay {
	private Scanner scan = new Scanner(System.in);

	// user input methods -----------------------
	public String readUserInput() {
		return scan.nextLine();
	}

	public Integer readUserInputInteger() {
		Boolean answerRight = false;
		Integer answer = -1;

		do {
			answer = scan.nextInt();
			answerRight = true;
			scan.nextLine();
		} while (!answerRight);

		return answer;
	}
	
	public Boolean userYN() {
		String condition = scan.nextLine();

		if (condition.equalsIgnoreCase("yes") ) {
			return true;
		} else if (condition.equalsIgnoreCase("no")) {
			System.out.println("Aborting.");
			return false;
		} else {
			System.out.println("Please write \"Yes\" or \"No\".");
		}
		return false;
	} 

	// console display methods ----------------
	public void printToConsole(String text, boolean breakLine) {
		if (breakLine) {
			System.out.println(text);
		} else {
			System.out.print(text);
		}
	}
	
	public void consoleLineBreak() {
		System.out.println();
	}

	public void closeScanner() {
		scan.close();
	}

	public void separator() {
		printToConsole("-----------------------", true);
	}
	
	// integrity check methods ---------------------------
	public Boolean checkIfEmpty(int size) {
		if (size == 0) {
			printToConsole("There are no accounts in the database.", true);
			return true;
		}
		return false;
	}

}
