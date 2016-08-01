package engine;

import java.util.ArrayList;

import entities.BoughtItem;
import entities.Item;
import entities.SuccessFee;

public class Logger {
	public static ArrayList<String> getItemsOnSale() {
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

	public static ArrayList<String> getBoughtItems() {
		ArrayList<BoughtItem> boughtItems = Initializer.getBoughtItems();
		ArrayList<String> parsedMessages = new ArrayList<String>();
		for (BoughtItem item : boughtItems) {
			String message = item.getBuyerUsername() + " has bought " + item.getItemName() + " that was sold by "
					+ item.getSellerUsername() + ".";
			parsedMessages.add(message);
		}
		return parsedMessages;
	}

	public static ArrayList<String> getSuccessFees() {
		ArrayList<SuccessFee> successFees = Initializer.getSuccessFees();
		ArrayList<String> parsedMessages = new ArrayList<String>();
		for (SuccessFee successFee : successFees) {
			String message = successFee.getSuccessFee() + " was deducted from " + successFee.getItemName() + ".";
			parsedMessages.add(message);
		}
		return parsedMessages;
	}
}
