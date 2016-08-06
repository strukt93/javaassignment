package engine;

public class Main {
	public static void main(String args[]) {
		System.out.println(Initializer.getBuyers().get(0).getCommaSeparatedData());
		Initializer.getBuyers().get(0).buyItem(Initializer.getItems().get(0));
	}
}