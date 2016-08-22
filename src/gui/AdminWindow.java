package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import engine.Initializer;
import entities.Administrator;
import entities.Item;

@SuppressWarnings("serial")
public class AdminWindow extends MainWindow {
	Administrator admin;

	public AdminWindow(Administrator admin) {
		this.admin = admin;
		initialize("Edit Account Details", "Show Items On Sale", "Show Bought Items", "Show Success Fees",
				admin.getUsername());
		setEditAccountDetailsButtonListener(admin.getName(), admin.getEmailAddress(), admin.getPassword(),
				admin.getContactNumber(), admin.getAddress());
		setItemsOnSaleButtonListener();
		setBoughtItemsButtonListener();
		setSuccessFeesButtonListener();
		configureButtons();
		addComponentsAndView();
	}

	public void setItemsOnSaleButtonListener() {
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Item> allItems = admin.getItemsOnSale();
				generateItemsFrame(allItems, "Items On Sale");
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

	public void generateItemsFrame(ArrayList<Item> items, String title) {
		JFrame frame = new JFrame(title);
		JPanel panel1 = new JPanel(new GridLayout(items.size(), 1));
		JScrollPane pane = new JScrollPane(panel1);
		for (Item item : items) {
			JPanel innerCell = new JPanel(new GridLayout(6, 1));
			innerCell.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
			innerCell.add(new JLabel("Name: " + item.getName()));
			innerCell.add(new JLabel("Price: " + item.getCost()));
			innerCell.add(new JLabel("Description: " + item.getDescription()));
			innerCell.add(new JLabel("Category: " + item.getType()));
			innerCell.add(new JLabel("Method: " + item.getMethod()));
			innerCell.add(new JLabel("Seller Name: " + item.getSellerUsername()));
			panel1.add(innerCell);
		}
		frame.add(pane);
		frame.setLocation(350, 200);
		frame.setSize(600, 400);
		frame.setVisible(true);
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
		admin = Initializer.getAdminByUsername(admin.getUsername());
		setEditAccountDetailsButtonListener(admin.getName(), admin.getEmailAddress(), admin.getPassword(),
				admin.getContactNumber(), admin.getAddress());
	}
}
