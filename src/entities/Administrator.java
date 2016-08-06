package entities;

import java.util.ArrayList;

import engine.Initializer;

public class Administrator extends User {

	public Administrator(String username, String password, String name, String emailAddress, String contactNumber,
			String address) {
		super(username, password, name, emailAddress, contactNumber, address, 0);
	}

	public ArrayList<String> getItemsOnSale() {
		ArrayList<Item> allItems = Initializer.getItems();
		ArrayList<String> parsedMessages = new ArrayList<String>();
		for (Item item : allItems) {
			String message = item.getSellerUsername() + " is selling " + item.getName();
			if (item.isSold()) {
				message += ". (Already sold out now).";
			}
			parsedMessages.add(message);
		}
		return parsedMessages;
	}

	public ArrayList<String> getBoughtItems() {
		ArrayList<BoughtItem> boughtItems = Initializer.getBoughtItems();
		ArrayList<String> parsedMessages = new ArrayList<String>();
		for (BoughtItem item : boughtItems) {
			String message = item.getBuyerUsername() + " has bought " + item.getItemName() + " that was sold by "
					+ item.getSellerUsername() + ".";
			parsedMessages.add(message);
		}
		return parsedMessages;
	}

	public ArrayList<String> getSuccessFees() {
		ArrayList<SuccessFee> successFees = Initializer.getSuccessFees();
		ArrayList<String> parsedMessages = new ArrayList<String>();
		for (SuccessFee successFee : successFees) {
			String message = successFee.getSuccessFee() + " was deducted from " + successFee.getItemName() + ".";
			parsedMessages.add(message);
		}
		return parsedMessages;
	}
}
