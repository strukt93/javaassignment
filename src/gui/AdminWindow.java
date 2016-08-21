package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import entities.Administrator;

@SuppressWarnings("serial")
public class AdminWindow extends MainWindow {
	Administrator admin;

	public AdminWindow(Administrator admin) {
		this.admin = admin;
		initialize();
		setItemsOnSaleButtonListener();
		setBoughtItemsButtonListener();
		setSuccessFeesButtonListener();
		configureButtons();
		addComponentsAndView();
	}

	public void initialize() {
		welcomeMessage = new JLabel("Welcome, " + admin.getUsername());
		button1 = new JButton("Edit Account Details");
		button2 = new JButton("Show Items on Sale");
		button3 = new JButton("Show Bought Items");
		button4 = new JButton("Show Success Fees");
		createComponents();
	}

	public void setItemsOnSaleButtonListener() {
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> allItems = admin.getItemsOnSale();
				generateInfoBox(allItems, "Items On Sale");
			}
		});
	}

	public void setBoughtItemsButtonListener() {
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> allItems = admin.getBoughtItems();
				generateInfoBox(allItems, "Bought Items");
			}
		});
	}

	public void setSuccessFeesButtonListener() {
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> successFees = admin.getSuccessFees();
				generateInfoBox(successFees, "Success Fees");
			}
		});
	}

	public void generateInfoBox(ArrayList<String> data, String title) {
		String message = "";
		for (String s : data) {
			message = message + s + System.lineSeparator();
		}
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void validateAndApplyEdits(String name, String emailAddress, String password, String contactNumber,
			String address) {
		if (name.length() != 0) {
			admin.editName(name, 1);
		}
		if (emailAddress.length() != 0) {
			admin.editEmailAddress(emailAddress, 1);
		}
		if (password.length() != 0) {
			admin.editPassword(password, 1);
		}
		if (contactNumber.length() != 0) {
			admin.editContactNumber(contactNumber, 1);
		}
		if (address.length() != 0) {
			admin.editAddress(address, 1);
		}
	}
}
