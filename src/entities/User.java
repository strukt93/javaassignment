package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class User {

	private String username;
	private String password;
	private String name;
	private String emailAddress;
	private String contactNumber;
	private String address;
	private int rating;

	public User(String username, String password, String name, String emailAddress, String contactNumber,
			String address) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.emailAddress = emailAddress;
		this.contactNumber = contactNumber;
		this.address = address;
		rating = 0;
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

	public int getRating() {
		return rating;
	}

	public void increaseRating() {
		rating += 1;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	private void setName(String name) {
		this.name = name;
	}

	private void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	private void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	private void setAddress(String address) {
		this.address = address;
	}

	public void updateRating(int type) {
		ArrayList<String> users = getUsers(type);
		this.increaseRating();
		users.add(this.getCommaSeparatedData());
		updateUsersFile(users, type);
	}

	public void editAddress(String newAddress, int type) {
		ArrayList<String> users = getUsers(type);
		this.setAddress(newAddress);
		users.add(this.getCommaSeparatedData());
		updateUsersFile(users, type);
	}

	public void editEmailAddress(String newEmailAddress, int type) {
		ArrayList<String> users = getUsers(type);
		this.setEmailAddress(newEmailAddress);
		users.add(this.getCommaSeparatedData());
		updateUsersFile(users, type);
	}

	public void editPassword(String newPassword, int type) {
		ArrayList<String> users = getUsers(type);
		this.setPassword(newPassword);
		users.add(this.getCommaSeparatedData());
		updateUsersFile(users, type);
	}

	public void editName(String newName, int type) {
		ArrayList<String> users = getUsers(type);
		this.setName(newName);
		users.add(this.getCommaSeparatedData());
		updateUsersFile(users, type);
	}

	public void editContactNumber(String newContactNumber, int type) {
		ArrayList<String> users = getUsers(type);
		this.setContactNumber(newContactNumber);
		users.add(this.getCommaSeparatedData());
		updateUsersFile(users, type);
	}

	private ArrayList<String> getUsers(int type) {
		ArrayList<String> allUsers = getUsersFromFile(type);
		for (int i = 0; i < allUsers.size(); i++) {
			String user = allUsers.get(i);
			if (user.split(",")[0].equals(this.getUsername())) {
				allUsers.remove(i);
			}
		}
		return allUsers;
	}

	private ArrayList<String> getUsersFromFile(int type) {
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

	private BufferedReader getBufferedReader(int type) {
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

	private BufferedWriter getBufferedWriter(int type) {
		BufferedWriter writer;
		try {
			switch (type) {
			case 1:
				writer = new BufferedWriter(new FileWriter(new File("files/admins.txt")));
				break;
			case 2:
				writer = new BufferedWriter(new FileWriter(new File("files/buyers.txt")));
				break;
			case 3:
				writer = new BufferedWriter(new FileWriter(new File("files/sellers.txt")));
				break;
			default:
				writer = null;
				break;
			}
		} catch (IOException e) {
			writer = null;
		}
		return writer;
	}

	private void updateUsersFile(ArrayList<String> users, int type) {
		BufferedWriter writer = getBufferedWriter(type);
		try {
			for (String user : users) {
				System.out.println(user);
				writer.write(user + System.lineSeparator());
			}
			writer.flush();
		} catch (IOException e) {

		}
	}

	public String getCommaSeparatedData() {
		return getUsername() + "," + getPassword() + "," + getName() + "," + getEmailAddress() + ","
				+ getContactNumber() + "," + getAddress();
	}

}
