package engine;

import users.Administrator;

public class Main {
	public static void main(String args[]) {
		Administrator admin = Initializer.getAdmins().get(0);
		admin.editContactNumber("123123123", 1);
	}
}
