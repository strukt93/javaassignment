package entities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import engine.Initializer;

/*
 * This class is the representation of the buyers in the system.
 * */
public class Buyer extends User {

	public Buyer(String username, String password, String name, String emailAddress, String contactNumber,
			String address, int rating) {
		super(username, password, name, emailAddress, contactNumber, address, rating);
	}

	/*
	 * This method gets called from the GUI when the Buyer attempts to buy an
	 * Item.
	 */
	public void buyItem(Item item) {
		BufferedWriter itemsWriter = getBoughtItemsFileWriter();
		BufferedWriter feesWriter = getSuccessFeesFileWriter();
		try {
			itemsWriter.write(item.getName() + "," + this.getUsername() + "," + item.getSellerUsername()
					+ System.lineSeparator());
			feesWriter.write(item.getName() + "," + item.getSuccessFee() + System.lineSeparator());
			itemsWriter.flush();
			feesWriter.flush();
			itemsWriter.close();
			feesWriter.close();
			Seller itemSeller = Initializer.getSellerByUsername(item.getSellerUsername());
			itemSeller.updateRating(3);
			itemSeller.deductSuccessFee(item.getSuccessFee());
			this.updateRating(2);
		} catch (IOException e) {

		}
	}

	/*
	 * This method gets called from the GUI when the Buyer attempts to view
	 * their old purchases.
	 */
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

	private BufferedWriter getBoughtItemsFileWriter() {
		File boughtItems = new File("files/bought_items.txt");
		try {
			FileWriter fileWriter = new FileWriter(boughtItems, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			return bufferedWriter;
		} catch (IOException e) {
			return null;
		}
	}

	private BufferedWriter getSuccessFeesFileWriter() {
		File successFees = new File("files/success_fees.txt");
		try {
			FileWriter fileWriter = new FileWriter(successFees, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			return bufferedWriter;
		} catch (IOException e) {
			return null;
		}
	}

}
