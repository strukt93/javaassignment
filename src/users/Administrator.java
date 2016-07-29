package users;

public class Administrator extends User {

	public Administrator(String username, String password, String name, String emailAddress) {
		super(username, password, name, emailAddress, "", "");
	}

	public String getCommaSeparatedData() {
		return getUsername() + "," + getPassword() + "," + getName() + "," + getEmailAddress();
	}

}
