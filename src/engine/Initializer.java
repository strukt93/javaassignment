package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import items.BoughtItem;
import items.Item;
import users.Administrator;
import users.Buyer;
import users.Seller;

public class Initializer {
	private ArrayList<Buyer> buyers;
	private ArrayList<Seller> sellers;
	private ArrayList<Administrator> admins;
	private ArrayList<Item> items;
	private ArrayList<BoughtItem> boughtItems;
	private BufferedReader adminsFile;
	private BufferedReader buyersFile;
	private BufferedReader sellersFile;
	private BufferedReader itemsFile;
	private BufferedReader boughtItemsFile;

	public Initializer() {
		buyers = new ArrayList<Buyer>();
		sellers = new ArrayList<Seller>();
		admins = new ArrayList<Administrator>();
		items = new ArrayList<Item>();
		try {
			adminsFile = new BufferedReader(new FileReader("files/admins.txt"));
			buyersFile = new BufferedReader(new FileReader("files/buyers.txt"));
			sellersFile = new BufferedReader(new FileReader("files/sellers.txt"));
			itemsFile = new BufferedReader(new FileReader("files/items.txt"));
			boughtItemsFile = new BufferedReader(new FileReader("files/bought_items.txt"));
			initialize(adminsFile, buyersFile, sellersFile, itemsFile, boughtItemsFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Buyer> getBuyers() {
		return buyers;
	}

	public ArrayList<Seller> getSellers() {
		return sellers;
	}

	public int getSellersCount() {
		return sellers.size();
	}

	public ArrayList<Administrator> getAdmins() {
		return admins;
	}

	private void initialize(BufferedReader adminsFile, BufferedReader buyersFile, BufferedReader sellersFile,
			BufferedReader itemsFile, BufferedReader boughtItemsFile) {
		initializeAdmins(adminsFile);
		initializeBuyers(buyersFile);
		initializeSellers(sellersFile);
		initializeItems(itemsFile);
		initializeBoughtItems(boughtItemsFile);
	}

	private void initializeAdmins(BufferedReader adminsFile) {
		try {
			String line;
			while ((line = adminsFile.readLine()) != null) {
				String[] values = line.split(",");
				Administrator admin = new Administrator(values[0], values[1], values[2], values[3]);
				this.admins.add(admin);
			}
			adminsFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initializeBuyers(BufferedReader buyersFile) {
		try {
			String line;
			while ((line = buyersFile.readLine()) != null) {
				String[] values = line.split(",");
				Buyer buyer = new Buyer(values[1], values[2], values[3], values[4], values[5], values[6]);
				this.buyers.add(buyer);
			}
			buyersFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initializeSellers(BufferedReader sellersFile) {
		try {
			String line;
			while ((line = sellersFile.readLine()) != null) {
				String[] values = line.split(",");
				Seller seller = new Seller(values[1], values[2], values[3], values[4], values[5], values[6]);
				this.sellers.add(seller);
			}
			sellersFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initializeItems(BufferedReader itemsFile) {
		try {
			String line;
			while ((line = itemsFile.readLine()) != null) {
				String[] values = line.split(",");
				Item item = new Item(values[0], values[1], values[2], Integer.parseInt(values[3]));
				this.items.add(item);
			}
			itemsFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initializeBoughtItems(BufferedReader boughtItemsFile) {
		try {
			String line;
			while ((line = boughtItemsFile.readLine()) != null) {
				String[] values = line.split(",");
				BoughtItem boughtItem = new BoughtItem(values[0], values[1]);
				this.boughtItems.add(boughtItem);
			}
			boughtItemsFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}
}
