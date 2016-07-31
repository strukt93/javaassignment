package users;

import items.Item;

public class Seller extends User {
	private int rating;

	public Seller(String username, String password, String name, String emailAddress, String contactNumber,
			String address) {
		super(username, password, name, emailAddress, contactNumber, address);
		rating = 0;
	}

	public int getRating() {
		return rating;
	}

	public void inreaseRating() {
		rating += 1;
	}

	public void addItem(Item item) {

	}

}
