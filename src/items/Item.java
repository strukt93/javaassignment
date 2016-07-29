package items;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Item {
	private String name;
	private String description;
	private String type;
	private int cost;
	private String createdAt;
	private boolean sold;

	public Item(String name, String description, String type, int cost) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.cost = cost;
		createdAt = new SimpleDateFormat("yyyy:MM:dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		this.sold = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
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

}
