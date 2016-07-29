package users;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import items.Item;

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
		BufferedWriter writer = getBoughtItemsFileWriter();
		try {
			writer.write(item.getName() + "," + this.getUsername());
			writer.close();
			item.setSold();
		} catch (IOException e) {

		}
	}

	public ArrayList<Item> viewBuyingRecords() {
		return null;
	}

	public BufferedWriter getBoughtItemsFileWriter() {
		File boughtItems = new File("files/bought_items.txt");
		try {
			FileWriter fileWriter = new FileWriter(boughtItems);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			return bufferedWriter;
		} catch (IOException e) {
			return null;
		}
	}

	public BufferedReader getBoughtItemsFileReader() {
		return null;
	}

	public static void main(String[] args) {

	}

}
