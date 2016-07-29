package items;

public class BoughtItem {
	private String itemName;
	private String buyerUsername;

	public BoughtItem(String itemName, String buyerUserName) {
		this.itemName = itemName;
		this.buyerUsername = buyerUserName;
	}

	public String getItemName() {
		return itemName;
	}

	public String getBuyerUsername() {
		return buyerUsername;
	}
}
