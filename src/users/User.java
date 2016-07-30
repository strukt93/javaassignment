package users;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

	public String getAddress() {
		return address;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getName() {
		return name;
	}

	public void editEmailAddress(String newEmailAddress, int type) {
		ArrayList<String> allUsers = getUsersFromFile(type);
		for (int i = 0; i < allUsers.size(); i++) {
			String user = allUsers.get(i);
			if (user.split(",")[0].equals(this.getUsername())) {
				allUsers.remove(i);
			}
		}
	}

	public ArrayList<String> getUsersFromFile(int type) {
		BufferedReader reader = getBufferedReader(type);
		String line;
		ArrayList<String> users = new ArrayList<String>();
		try {
			while ((line = reader.readLine()) != null)
				users.add(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}

	public BufferedReader getBufferedReader(int type) {
		BufferedReader reader;
		try {
			switch (type) {
			case 1:
				reader = new BufferedReader(new FileReader("files/admins.txt"));
				break;
			case 2:
				reader = new BufferedReader(new FileReader("files/buyers.txt"));
				break;
			case 3:
				reader = new BufferedReader(new FileReader("files/sellers.txt"));
				break;
			default:
				reader = null;
				break;
			}
		} catch (FileNotFoundException e) {
			reader = null;
		}
		return reader;
	}

	public String getCommaSeparatedData() {
		return getUsername() + "," + getPassword() + "," + getName() + "," + getEmailAddress() + ","
				+ getContactNumber() + "," + getAddress();
	}

}
