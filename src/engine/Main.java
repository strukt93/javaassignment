package engine;

import java.util.ArrayList;
import entities.*;
import gui.LoginWindow;

public class Main {
	public static void main(String args[]) {
		// ArrayList<Administrator> admins = Initializer.getAdmins();
		// ArrayList<Buyer> buyers = Initializer.getBuyers();
		// ArrayList<Seller> sellers = Initializer.getSellers();
		// ArrayList<Item> items = Initializer.getItems();
		// ArrayList<BoughtItem> boughtItems = Initializer.getBoughtItems();
		// ArrayList<FeeAccount> feeAccounts = Initializer.getFeeAccounts();
		// ArrayList<SuccessFee> successFees = Initializer.getSuccessFees();
		// ArrayList<String> x = admins.get(0).getSuccessFees();
		LoginWindow l = new LoginWindow();
		l.setupAndShow();
	}
}