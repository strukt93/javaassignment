package entities;

public class Item {
	private String name;
	private String sellerUsername;
	private String description;
	private String type;
	private int cost;
	private boolean sold;

	public Item(String name, String sellerUsername, String description, String type, int cost, boolean sold) {
		this.name = name;
		this.sellerUsername = sellerUsername;
		this.description = description;
		this.type = type;
		this.cost = cost;
		this.sold = sold;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}

	public int getCost() {
		return cost;
	}

	public boolean isSold() {
		return sold;
	}

	public String getSellerUsername() {
		return sellerUsername;
	}

	public double getSuccessFee() {
		if (this.cost >= 5 && this.cost <= 100) {
			return cost * 0.05;
		}
		if (this.cost > 100 && this.cost <= 1000) {
			return cost * 0.1;
		}
		if (this.cost > 1000) {
			return cost * 0.15;
		}
		return 0;
	}

	public String getCommaSeparatedValues() {
		return getName() + "," + getSellerUsername() + "," + getDescription() + "," + getType() + "," + getCost() + ","
				+ isSold();
	}
}