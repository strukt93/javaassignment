package entities;

import java.util.ArrayList;

import engine.Initializer;

/*
 * This class represents administrators in the system.
 * */
public class Administrator extends User {

	public Administrator(String username, String password, String name, String emailAddress, String contactNumber,
			String address) {
		super(username, password, name, emailAddress, contactNumber, address, 0);
	}

	/*
	 * This method constructs a report of all the items that are on sale and
	 * returns it. It is called from the GUI.
	 */
	public ArrayList<Item> getItemsOnSale() {
		ArrayList<Item> allItems = Initializer.getItems();
		return allItems;
	}

	/*
	 * This method is called from the GUI and constructs a report of items that
	 * have been bought then returns it.
	 */
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

	/*
	 * This method is called from the GUI, it creates a report of the success
	 * fees that have been deducted from successful purchases then returns it.
	 */
	public ArrayList<String> getSuccessFees() {
		ArrayList<SuccessFee> successFees = Initializer.getSuccessFees();
		ArrayList<String> parsedMessages = new ArrayList<String>();
		for (SuccessFee successFee : successFees) {
			String message = successFee.getSuccessFee() + " RM was deducted from " + successFee.getItemName() + ".";
			parsedMessages.add(message);
		}
		return parsedMessages;
	}
}
