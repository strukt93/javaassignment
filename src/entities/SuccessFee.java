package entities;

public class SuccessFee {
	private String itemName;
	private double successFee;

	public SuccessFee(String itemName, double successFee) {
		this.itemName = itemName;
		this.successFee = successFee;
	}

	public String getItemName() {
		return itemName;
	}

	public double getSuccessFee() {
		return successFee;
	}

}
