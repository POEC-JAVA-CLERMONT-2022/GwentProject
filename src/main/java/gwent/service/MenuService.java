package gwent.service;

public enum MenuService {

	CREATE_ACCOUNT("1", "Create account"),
	EDIT_ACCOUNT("2", "Edit account"),
	DELETE_ACCOUNT("3", "Delete account"),
	LIST_ACCOUNT("4", "List all accounts"),
	EXIT(ActionConstants.EXIT_ID, ActionConstants.EXIT_ACTION);

	private String value;
	private String title;

	MenuService(String value, String title) {
		this.value = value;
		this.title = title;
	}
	
	public class ActionConstants {
	    public static final String EXIT_ACTION = "Exit";
	    public static final String EXIT_ID = "5";
	}
	
	public static boolean containsAction(String value) {
        MenuService[] elements = MenuService.values();

        for (MenuService menu : elements) {
            if (menu.getValue().equalsIgnoreCase(value)) {
                return true;
            }
        }

        return false;
    }

// getters & setters --------------------------------------------------------
    public String getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }
	
	
}
