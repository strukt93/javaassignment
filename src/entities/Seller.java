package entities;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import engine.Initializer;

public class Seller extends User {
	private FeeAccount account;

	public Seller(String username, String password, String name, String emailAddress, String contactNumber,
			String address, int rating, FeeAccount account) {
		super(username, password, name, emailAddress, contactNumber, address, rating);
		this.account = account;
	}

	public boolean addItem(Item item) {
		if (!hasSufficientCredit()) {
			return false;
		} else {
			BufferedWriter writer = getBufferedWriter();
			try {
				System.out.println(item.getCommaSeparatedValues());
				writer.write(item.getCommaSeparatedValues() + System.lineSeparator());
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
	}

	public ArrayList<Item> getListedItems() {
		ArrayList<Item> allItems = Initializer.getItems();
		ArrayList<Item> items = new ArrayList<Item>();
		for (Item item : allItems) {
			if (item.getSellerUsername().equals(this.getUsername())) {
				items.add(item);
			}
		}
		return items;
	}

	public double getFeeAccountBalance() {
		return getFeeAccount().getBalance();
	}

	private FeeAccount getFeeAccount() {
		return account;
	}

	public void addFundsToFeeAccount(double funds) {
		account.updateFunds(funds);
	}

	public void deductSuccessFee(double successFee) {
		account.updateFunds(-successFee);
	}

	public boolean hasSufficientCredit() {
		return getFeeAccountBalance() > 10;
	}

	private BufferedWriter getBufferedWriter() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("files/items.txt"), true));
			return writer;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
