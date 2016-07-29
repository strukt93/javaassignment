package users;

import items.Item;

public class Seller extends User {

	public Seller(String username, String password, String name, String emailAddress, String contactNumber,
			String address) {
		super(username, password, name, emailAddress, contactNumber, address);
	}

	public String getCommaSeparatedData() {
		return getUsername() + "," + getPassword() + "," + getName() + "," + getEmailAddress() + ","
				+ getContactNumber() + "," + getAddress();
	}

	public void addItem(Item item) {
		
	}

}
