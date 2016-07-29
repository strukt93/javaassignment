package users;

public abstract class User {

	private String username;
	private String password;
	private String name;
	private String emailAddress;
	private String contactNumber;
	private String address;

	public User(String username, String password, String name, String emailAddress, String contactNumber,
			String address) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.emailAddress = emailAddress;
		this.contactNumber = contactNumber;
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
