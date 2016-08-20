package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FeeAccount {
	private String sellerUsername;
	private double balance;

	public FeeAccount(String sellerUsername, double balance) {
		this.sellerUsername = sellerUsername;
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public String getSellerUsername() {
		return sellerUsername;
	}

	private void addFunds(double funds) {
		this.balance += funds;
	}

	public void updateFunds(double funds) {
		ArrayList<String> allAccounts = getAccounts();
		this.addFunds(funds);
		allAccounts.add(this.getCommaSeparatedData());
		updateFeeAccountsFile(allAccounts);
	}

	private BufferedWriter getBufferedWriter() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("files/fee_accounts.txt")));
			return writer;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private BufferedReader getBufferedReader() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("files/fee_accounts.txt"));
			return reader;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private ArrayList<String> getFeeAccountsFromFile() {
		BufferedReader reader = getBufferedReader();
		String line;
		ArrayList<String> accounts = new ArrayList<String>();
		try {
			while ((line = reader.readLine()) != null)
				accounts.add(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	private void updateFeeAccountsFile(ArrayList<String> accounts) {
		BufferedWriter writer = getBufferedWriter();
		try {
			for (String account : accounts) {
				writer.write(account + System.lineSeparator());
			}
			writer.flush();
		} catch (IOException e) {

		}
	}

	private ArrayList<String> getAccounts() {
		ArrayList<String> allAccounts = getFeeAccountsFromFile();
		for (int i = 0; i < allAccounts.size(); i++) {
			String user = allAccounts.get(i);
			if (user.split(",")[0].equals(this.getSellerUsername())) {
				allAccounts.remove(i);
			}
		}
		return allAccounts;
	}

	public String getCommaSeparatedData() {
		return this.getSellerUsername() + "," + this.getBalance();
	}
}
