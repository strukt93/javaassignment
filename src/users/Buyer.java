package users;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import engine.Initializer;
import items.BoughtItem;
import items.Item;

public class Buyer extends User {

	public Buyer(String username, String password, String name, String emailAddress, String contactNumber,
			String address) {
		super(username, password, name, emailAddress, contactNumber, address);
	}

	public void buyItem(Item item) {
		BufferedWriter writer = getBoughtItemsFileWriter();
		try {
			writer.write(item.getName() + "," + this.getUsername() + System.lineSeparator());
			writer.close();
			item.setSold();
		} catch (IOException e) {

		}
	}

	public ArrayList<Item> viewBuyingRecords() {
		ArrayList<BoughtItem> allBoughtItems = Initializer.getBoughtItems();
		ArrayList<Item> boughtItems = new ArrayList<Item>();
		for (BoughtItem item : allBoughtItems) {
			if (item.getBuyerUsername().equals(this.getUsername())) {
				Item boughtItem = Initializer.getItemByName(item.getItemName());
				boughtItems.add(boughtItem);
			}
		}
		return boughtItems;
	}

	public BufferedWriter getBoughtItemsFileWriter() {
		File boughtItems = new File("files/bought_items.txt");
		try {
			FileWriter fileWriter = new FileWriter(boughtItems, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			return bufferedWriter;
		} catch (IOException e) {
			return null;
		}
	}

	public static void main(String[] args) {
		Buyer b = new Buyer("test123", "", "", "", "", "");
		Item i = new Item("item", "", "", 123);
		b.buyItem(i);
	}

}
