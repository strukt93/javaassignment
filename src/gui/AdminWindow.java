package gui;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import engine.Initializer;
import entities.Administrator;
import entities.Item;

@SuppressWarnings("serial")
public class AdminWindow extends MainWindow {
	Administrator admin;
	JButton button2;
	JButton button3;
	JButton button4;

	public AdminWindow(Administrator admin) {
		this.admin = admin;
		initialize(admin.getUsername());
		initializeButtons("Edit Account Details", "Show Items On Sale", "Show Bought Items", "Show Success Fees");
		setEditAccountDetailsButtonListener(admin.getName(), admin.getEmailAddress(), admin.getPassword(),
				admin.getContactNumber(), admin.getAddress());
		setItemsOnSaleButtonListener();
		setBoughtItemsButtonListener();
		setSuccessFeesButtonListener();
		configureButtons();
		addComponentsAndView();
	}

	public void initializeButtons(String buttonOneText, String buttonTwoText, String buttonThreeText,
			String buttonFourText) {
		editAccountDetailsButton = new JButton(buttonOneText);
		button2 = new JButton(buttonTwoText);
		button3 = new JButton(buttonThreeText);
		button4 = new JButton(buttonFourText);
	}

	@Override
	public void configureButtons() {
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipadx = 30;
		gbc.ipady = 30;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		innerContainer.add(editAccountDetailsButton, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		innerContainer.add(button2, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		innerContainer.add(button3, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		innerContainer.add(button4, gbc);
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
				if (allItems.size() == 0) {
					JOptionPane.showMessageDialog(null, "No bought items", "Bought Items", JOptionPane.PLAIN_MESSAGE);
					return;
				}
				generateInfoBox(allItems, "Bought Items");
			}
		});
	}

	public void setSuccessFeesButtonListener() {
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> successFees = admin.getSuccessFees();
				if (successFees.size() == 0) {
					JOptionPane.showMessageDialog(null, "No success fees", "Success Fees", JOptionPane.PLAIN_MESSAGE);
					return;
				}
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
			innerCell.add(new JLabel("Price: " + item.getCost() + " RM"));
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
