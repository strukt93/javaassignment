package entities;

public class BoughtItem {
	private String itemName;
	private String buyerUsername;
	private String sellerUsername;

	public BoughtItem(String itemName, String buyerUserName, String sellerUsername) {
		this.itemName = itemName;
		this.buyerUsername = buyerUserName;
		this.sellerUsername = sellerUsername;
	}

	public String getItemName() {
		return itemName;
	}

	public String getBuyerUsername() {
		return buyerUsername;
	}

	public String getSellerUsername() {
		return sellerUsername;
	}

	public String getCommaSeparatedData() {
		return getItemName() + "," + getBuyerUsername() + "," + getSellerUsername();
	}
}
