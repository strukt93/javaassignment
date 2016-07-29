package engine;

import java.util.ArrayList;

import users.Administrator;
import users.Buyer;
import users.Seller;

public class Authentication {
	static Initializer initializer = new Initializer();

	public static Administrator adminLogin(String username, String password) {
		ArrayList<Administrator> admins = initializer.getAdmins();
		for (Administrator admin : admins) {
			if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
				return admin;
			}
		}
		return null;
	}

	public static Buyer buyerLogin(String username, String password) {
		ArrayList<Buyer> buyers = initializer.getBuyers();
		for (Buyer buyer : buyers) {
			if (buyer.getUsername().equals(username) && buyer.getPassword().equals(password)) {
				return buyer;
			}
		}
		return null;
	}

	public static Seller sellerLogin(String username, String password) {
		ArrayList<Seller> sellers = initializer.getSellers();
		for (Seller seller : sellers) {
			if (seller.getUsername().equals(username) && seller.getPassword().equals(password)) {
				return seller;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Administrator a = Authentication.adminLogin("admin", "admin");
		System.out.println(a.getCommaSeparatedData());
	}
}
