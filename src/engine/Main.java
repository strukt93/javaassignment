package engine;

import entities.Seller;

public class Main {
	public static void main(String args[]) {
		Seller s = Initializer.getSellers().get(0);
		s.addFundsToFeeAccount(200);
		System.out.println(s.addItem(Initializer.getItems().get(0)));
	}
}
