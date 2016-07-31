package items;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Item {
	private String name;
	private String sellerUsername;
	private String description;
	private String type;
	private int cost;
	private String createdAt;
	private boolean sold;

	public Item(String name, String sellerUsername, String description, String type, int cost) {
		this.name = name;
		this.sellerUsername = sellerUsername;
		this.description = description;
		this.type = type;
		this.cost = cost;
		createdAt = new SimpleDateFormat("yyyy:MM:dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		this.sold = false;
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

	public void setSold() {
		this.sold = true;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getSellerUsername() {
		return sellerUsername;
	}

}
