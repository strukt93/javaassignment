package entities;

/*
 * This class represents the items in the system.
 * */
public class Item {
	private String name;
	private String sellerUsername;
	private String description;
	private String type;
	private String method;
	private double cost;
	private boolean sold;

	public Item(String name, String sellerUsername, String description, String type, String method, double cost,
			boolean sold) {
		this.name = name;
		this.sellerUsername = sellerUsername;
		this.description = description;
		this.type = type;
		this.method = method;
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

	public double getCost() {
		return cost;
	}

	public boolean isSold() {
		return sold;
	}

	public String getSellerUsername() {
		return sellerUsername;
	}

	public String getMethod() {
		return method;
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

	public static double getSuccessFee(double inputCost) {
		if (inputCost >= 5 && inputCost <= 100) {
			return inputCost * 0.05;
		}
		if (inputCost > 100 && inputCost <= 1000) {
			return inputCost * 0.1;
		}
		if (inputCost > 1000) {
			return inputCost * 0.15;
		}
		return 0;
	}

	public String getCommaSeparatedData() {
		return getName() + "," + getSellerUsername() + "," + getDescription() + "," + getType() + "," + getMethod()
				+ "," + getCost() + "," + isSold();
	}
}
