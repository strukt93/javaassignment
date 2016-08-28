package engine;

import java.util.ArrayList;

import entities.Administrator;
import entities.Buyer;
import entities.Seller;

/*
 * This class is only responsible for the authentication of users when they attempt to login.
 * It's static because we only need to access it's methods, no need to create objects out of it.
 */
public class Authentication {

	public static Administrator adminLogin(String username, String password) {
		ArrayList<Administrator> admins = Initializer.getAdmins();
		for (Administrator admin : admins) {
			if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
				return admin;
			}
		}
		return null;
	}

	public static Buyer buyerLogin(String username, String password) {
		ArrayList<Buyer> buyers = Initializer.getBuyers();
		for (Buyer buyer : buyers) {
			if (buyer.getUsername().equals(username) && buyer.getPassword().equals(password)) {
				return buyer;
			}
		}
		return null;
	}

	public static Seller sellerLogin(String username, String password) {
		ArrayList<Seller> sellers = Initializer.getSellers();
		for (Seller seller : sellers) {
			if (seller.getUsername().equals(username) && seller.getPassword().equals(password)) {
				return seller;
			}
		}
		return null;
	}
}
