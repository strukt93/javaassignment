package entities;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import engine.Initializer;

/*
 * This class represents the Seller type of users, it extends from the User class.
 * */
public class Seller extends User {
	private FeeAccount account;

	public Seller(String username, String password, String name, String emailAddress, String contactNumber,
			String address, int rating, FeeAccount account) {
		super(username, password, name, emailAddress, contactNumber, address, rating);
		this.account = account;
	}

	/*
	 * This method is called from the GUI when a Seller attempts to add a new
	 * Item, it returns false and stops if the Seller doesn't have enough
	 * credit, and returns true after adding the Item otherwise.
	 */
	public void addItem(Item item) {

		BufferedWriter writer = getBufferedWriter();
		try {
			writer.write(item.getCommaSeparatedData() + System.lineSeparator());
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * This method calls getListedItems() from the Initializer class to get the
	 * items listed by the current Seller.
	 */
	public ArrayList<String> getListedItems() {
		ArrayList<Item> allItems = Initializer.getItems();
		ArrayList<String> items = new ArrayList<String>();
		for (Item item : allItems) {
			if (item.getSellerUsername().equals(this.getUsername())) {
				String message = "";
				message = "You have listed " + item.getName() + " for " + item.getCost() + " RM";
				items.add(message);
			}
		}
		return items;
	}

	/*
	 * This method calls getBoughtItems() from the Initializer class, checks
	 * each BoughtItem for the Seller username, adds it to the collection of
	 * items if true, then returns all the items.
	 */
	public ArrayList<String> getSoldItems() {
		ArrayList<BoughtItem> boughtItems = Initializer.getBoughtItems();
		ArrayList<String> items = new ArrayList<String>();
		for (BoughtItem boughtItem : boughtItems) {
			if (boughtItem.getSellerUsername().equals(this.getUsername())) {
				Item item = Initializer.getItemByName(boughtItem.getItemName());
				String message = "";
				message = "You have sold " + item.getName() + " to " + boughtItem.getBuyerUsername() + " for "
						+ item.getCost() + " RM";
				items.add(message);
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

	/*
	 * This method allows a Seller to add funds to their FeeAccount.
	 */
	public void addFundsToFeeAccount(double funds) {
		account.updateFunds(funds);
	}

	/*
	 * This method is called by the system after a successful call of the
	 * buyItem() method in the Buyer class.
	 */
	public void deductSuccessFee(double successFee) {
		account.updateFunds(-successFee);
	}

	public boolean hasSufficientCredit(double successFee) {
		double totalFees = successFee;
		for (Item item : Initializer.getItemsBySellerUsername(this.getUsername())) {
			totalFees += item.getSuccessFee();
		}
		return getFeeAccountBalance() >= totalFees;
	}

	/*
	 * This method returns a BufferedWriter to allow adding new items to the
	 * file.
	 */
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
