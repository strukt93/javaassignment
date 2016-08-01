package engine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import entities.Administrator;
import entities.BoughtItem;
import entities.Buyer;
import entities.FeeAccount;
import entities.Item;
import entities.Seller;

public class Initializer {

	public static ArrayList<Buyer> getBuyers() {
		try {
			BufferedReader buyersFile = new BufferedReader(new FileReader("files/admins.txt"));
			ArrayList<Buyer> buyers = initializeBuyers(buyersFile);
			return buyers;
		} catch (FileNotFoundException e) {

		}
		return null;
	}

	public static ArrayList<Seller> getSellers() {
		try {
			BufferedReader sellersFile = new BufferedReader(new FileReader("files/sellers.txt"));
			ArrayList<Seller> sellers = initializeSellers(sellersFile);
			return sellers;
		} catch (FileNotFoundException e) {

		}
		return null;
	}

	public static ArrayList<FeeAccount> getFeeAccounts() {
		try {
			BufferedReader feeAccountsFile = new BufferedReader(new FileReader("files/fee_accounts.txt"));
			ArrayList<FeeAccount> feeAccounts = initializeFeeAccounts(feeAccountsFile);
			return feeAccounts;
		} catch (FileNotFoundException e) {

		}
		return null;
	}

	public static ArrayList<Administrator> getAdmins() {
		try {
			BufferedReader adminsFile = new BufferedReader(new FileReader("files/admins.txt"));
			ArrayList<Administrator> admins = initializeAdmins(adminsFile);
			return admins;
		} catch (FileNotFoundException e) {

		}
		return null;
	}

	public static ArrayList<Item> getItems() {
		try {
			BufferedReader itemsFile = new BufferedReader(new FileReader("files/items.txt"));
			ArrayList<Item> items = initializeItems(itemsFile);
			return items;
		} catch (FileNotFoundException e) {

		}
		return null;
	}

	public static ArrayList<BoughtItem> getBoughtItems() {
		try {
			BufferedReader boughtItemsFile = new BufferedReader(new FileReader("files/items.txt"));
			ArrayList<BoughtItem> boughtItems = initializeBoughtItems(boughtItemsFile);
			return boughtItems;
		} catch (FileNotFoundException e) {

		}
		return null;
	}

	private static ArrayList<Administrator> initializeAdmins(BufferedReader adminsFile) {
		ArrayList<Administrator> admins = new ArrayList<Administrator>();
		try {
			String line;
			while ((line = adminsFile.readLine()) != null) {
				String[] values = line.split(",");
				Administrator admin = new Administrator(values[0], values[1], values[2], values[3], values[4],
						values[5]);
				admins.add(admin);

			}
			adminsFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return admins;
	}

	private static ArrayList<Buyer> initializeBuyers(BufferedReader buyersFile) {
		ArrayList<Buyer> buyers = new ArrayList<Buyer>();
		try {
			String line;
			while ((line = buyersFile.readLine()) != null) {
				String[] values = line.split(",");
				Buyer buyer = new Buyer(values[1], values[2], values[3], values[4], values[5], values[6]);
				buyers.add(buyer);
			}
			buyersFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buyers;
	}

	private static ArrayList<Seller> initializeSellers(BufferedReader sellersFile) {
		ArrayList<Seller> sellers = new ArrayList<Seller>();
		try {
			String line;
			while ((line = sellersFile.readLine()) != null) {
				String[] values = line.split(",");
				Seller seller = new Seller(values[0], values[1], values[2], values[3], values[4], values[5],
						getFeeAccountBySellerUsername(values[0]));
				sellers.add(seller);
			}
			sellersFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sellers;
	}

	private static ArrayList<FeeAccount> initializeFeeAccounts(BufferedReader feeAccountsFile) {
		ArrayList<FeeAccount> accounts = new ArrayList<FeeAccount>();
		try {
			String line;
			while ((line = feeAccountsFile.readLine()) != null) {
				String[] values = line.split(",");
				FeeAccount account = new FeeAccount(values[0], Double.parseDouble(values[1]));
				accounts.add(account);

			}
			feeAccountsFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	private static ArrayList<Item> initializeItems(BufferedReader itemsFile) {
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			String line;
			while ((line = itemsFile.readLine()) != null) {
				String[] values = line.split(",");
				Item item = new Item(values[0], values[1], values[2], values[3], Integer.parseInt(values[4]),
						Boolean.parseBoolean(values[5]));
				items.add(item);
			}
			itemsFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return items;
	}

	private static ArrayList<BoughtItem> initializeBoughtItems(BufferedReader boughtItemsFile) {
		ArrayList<BoughtItem> boughtItems = new ArrayList<BoughtItem>();
		try {
			String line;
			while ((line = boughtItemsFile.readLine()) != null) {
				String[] values = line.split(",");
				BoughtItem boughtItem = new BoughtItem(values[0], values[1], values[2]);
				boughtItems.add(boughtItem);
			}
			boughtItemsFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return boughtItems;
	}

	public static Item getItemByName(String itemName) {
		ArrayList<Item> allItems = getItems();
		for (Item item : allItems) {
			if (item.getName().equals(itemName)) {
				return item;
			}
		}
		return null;
	}

	public static Seller getSellerByUsername(String sellerUsername) {
		ArrayList<Seller> allSellers = getSellers();
		for (Seller seller : allSellers) {
			if (seller.getUsername().equals(sellerUsername)) {
				return seller;
			}
		}
		return null;
	}

	public static FeeAccount getFeeAccountBySellerUsername(String sellerUsername) {
		ArrayList<FeeAccount> allAccounts = getFeeAccounts();
		for (FeeAccount account : allAccounts) {
			if (account.getSellerUsername().equals(sellerUsername)) {
				return account;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(Initializer.getAdmins().get(0).getCommaSeparatedData());

	}
}
