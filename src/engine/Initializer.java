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
import entities.SuccessFee;

/*
 * This class is the backbone of the system, it only contains static methods that are
 * responsible for reading data out of files and finding data given keys.
 */
public class Initializer {
	/*
	 * This method is responsible for reading buyers' information from their
	 * respective file.
	 */
	public static ArrayList<Buyer> getBuyers() {
		try {
			BufferedReader buyersFile = new BufferedReader(new FileReader("files/buyers.txt"));
			ArrayList<Buyer> buyers = initializeBuyers(buyersFile);
			return buyers;
		} catch (FileNotFoundException e) {

		}
		return null;
	}

	/*
	 * This method is responsible for reading sellers' information from their
	 * respective file.
	 */
	public static ArrayList<Seller> getSellers() {
		try {
			BufferedReader sellersFile = new BufferedReader(new FileReader("files/sellers.txt"));
			ArrayList<Seller> sellers = initializeSellers(sellersFile);
			return sellers;
		} catch (FileNotFoundException e) {

		}
		return null;
	}

	/*
	 * This method is responsible for reading fee accounts' information from
	 * their respective file.
	 */
	public static ArrayList<FeeAccount> getFeeAccounts() {
		try {
			BufferedReader feeAccountsFile = new BufferedReader(new FileReader("files/fee_accounts.txt"));
			ArrayList<FeeAccount> feeAccounts = initializeFeeAccounts(feeAccountsFile);
			return feeAccounts;
		} catch (FileNotFoundException e) {

		}
		return null;
	}

	/*
	 * This method is responsible for reading administrators' information from
	 * their respective file.
	 */
	public static ArrayList<Administrator> getAdmins() {
		try {
			BufferedReader adminsFile = new BufferedReader(new FileReader("files/admins.txt"));
			ArrayList<Administrator> admins = initializeAdmins(adminsFile);
			return admins;
		} catch (FileNotFoundException e) {

		}
		return null;
	}

	/*
	 * This method is responsible for reading items' information from their
	 * respective file.
	 */
	public static ArrayList<Item> getItems() {
		try {
			BufferedReader itemsFile = new BufferedReader(new FileReader("files/items.txt"));
			ArrayList<Item> items = initializeItems(itemsFile);
			return items;
		} catch (FileNotFoundException e) {

		}
		return null;
	}

	/*
	 * This method is responsible for reading bought items' information from
	 * their respective file.
	 */
	public static ArrayList<BoughtItem> getBoughtItems() {
		try {
			BufferedReader boughtItemsFile = new BufferedReader(new FileReader("files/bought_items.txt"));
			ArrayList<BoughtItem> boughtItems = initializeBoughtItems(boughtItemsFile);
			return boughtItems;
		} catch (FileNotFoundException e) {

		}
		return null;
	}

	/*
	 * This method is responsible for reading success fees' information from
	 * their respective file.
	 */
	public static ArrayList<SuccessFee> getSuccessFees() {
		try {
			BufferedReader successFeesFile = new BufferedReader(new FileReader("files/success_fees.txt"));
			ArrayList<SuccessFee> successFees = initializeSuccessFees(successFeesFile);
			return successFees;
		} catch (FileNotFoundException e) {

		}
		return null;
	}

	/*
	 * This method initializes new SuccessFee objects from the data returned
	 * from the file.
	 */
	private static ArrayList<SuccessFee> initializeSuccessFees(BufferedReader successFeesFile) {
		ArrayList<SuccessFee> successFees = new ArrayList<SuccessFee>();
		try {
			String line;
			while ((line = successFeesFile.readLine()) != null) {
				String[] values = line.split(",");
				SuccessFee successFee = new SuccessFee(values[0], Double.parseDouble(values[1]));
				successFees.add(successFee);

			}
			successFeesFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return successFees;
	}

	/*
	 * This method initializes new Administrator objects from the data returned
	 * from the file.
	 */
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

	/*
	 * This method initializes new Buyer objects from the data returned from the
	 * file.
	 */
	private static ArrayList<Buyer> initializeBuyers(BufferedReader buyersFile) {
		ArrayList<Buyer> buyers = new ArrayList<Buyer>();
		try {
			String line;
			while ((line = buyersFile.readLine()) != null) {
				String[] values = line.split(",");
				Buyer buyer = new Buyer(values[0], values[1], values[2], values[3], values[4], values[5],
						Integer.parseInt(values[6]));
				buyers.add(buyer);
			}
			buyersFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buyers;
	}

	/*
	 * This method initializes new Seller objects from the data returned from
	 * the file.
	 */
	private static ArrayList<Seller> initializeSellers(BufferedReader sellersFile) {
		ArrayList<Seller> sellers = new ArrayList<Seller>();
		try {
			String line;
			while ((line = sellersFile.readLine()) != null) {
				String[] values = line.split(",");
				Seller seller = new Seller(values[0], values[1], values[2], values[3], values[4], values[5],
						Integer.parseInt(values[6]), getFeeAccountBySellerUsername(values[0]));
				sellers.add(seller);
			}
			sellersFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sellers;
	}

	/*
	 * This method initializes new FeeAccount objects from the data returned
	 * from the file.
	 */
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

	/*
	 * This method initializes new Item objects from the data returned from the
	 * file.
	 */
	private static ArrayList<Item> initializeItems(BufferedReader itemsFile) {
		ArrayList<Item> items = new ArrayList<Item>();
		try {
			String line;
			while ((line = itemsFile.readLine()) != null) {
				String[] values = line.split(",");
				Item item = new Item(values[0], values[1], values[2], values[3], values[4], Integer.parseInt(values[5]),
						Boolean.parseBoolean(values[6]));
				items.add(item);
			}
			itemsFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return items;
	}

	/*
	 * This method initializes new BoughtItem objects from the data returned
	 * from the file.
	 */
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

	/*
	 * This method returns an Item after looking it up using it's name as the
	 * key.
	 */
	public static Item getItemByName(String itemName) {
		ArrayList<Item> allItems = getItems();
		for (Item item : allItems) {
			if (item.getName().equals(itemName)) {
				return item;
			}
		}
		return null;
	}

	/*
	 * This method returns a Seller after looking them up using their username
	 * as the key.
	 */
	public static Seller getSellerByUsername(String sellerUsername) {
		ArrayList<Seller> allSellers = getSellers();
		for (Seller seller : allSellers) {
			if (seller.getUsername().equals(sellerUsername)) {
				return seller;
			}
		}
		return null;
	}

	/*
	 * This method returns a FeeAccount after looking it up using it's owner
	 * Seller's username as the key.
	 */
	public static FeeAccount getFeeAccountBySellerUsername(String sellerUsername) {
		ArrayList<FeeAccount> allAccounts = getFeeAccounts();
		for (FeeAccount account : allAccounts) {
			if (account.getSellerUsername().equals(sellerUsername)) {
				return account;
			}
		}
		return null;
	}

	public static Administrator getAdminByUsername(String adminUsername) {
		ArrayList<Administrator> allAdmins = getAdmins();
		for (Administrator admin : allAdmins) {
			if (admin.getUsername().equals(adminUsername)) {
				return admin;
			}
		}
		return null;
	}

	public static Buyer getBuyerByUsername(String buyerUsername) {
		ArrayList<Buyer> allBuyers = getBuyers();
		for (Buyer buyer : allBuyers) {
			if (buyer.getUsername().equals(buyerUsername)) {
				return buyer;
			}
		}
		return null;
	}

	/*
	 * This method returns all the categories of listed items available in the
	 * system.
	 */
	public static ArrayList<String> getAvailableCategories() {
		ArrayList<String> availableCategories = new ArrayList<String>();
		ArrayList<Item> allItems = getItems();
		for (Item item : allItems) {
			if (!availableCategories.contains(item.getType())) {
				availableCategories.add(item.getType());
			}
		}
		return availableCategories;
	}
}
