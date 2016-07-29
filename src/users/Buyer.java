package users;

import other.Item;

public class Buyer extends User {

	public Buyer(String username, String password, String name, String emailAddress, String contactNumber,
			String address) {
		super(username, password, name, emailAddress, contactNumber, address);
	}

	public String getCommaSeparatedData() {
		return getUsername() + "," + getPassword() + "," + getName() + "," + getEmailAddress() + ","
				+ getContactNumber() + "," + getAddress();
	}

	public void buyItem(Item item) {

	}

}
