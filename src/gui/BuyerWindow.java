package gui;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import engine.Initializer;
import entities.Buyer;
import entities.Item;

@SuppressWarnings("serial")
public class BuyerWindow extends MainWindow {
	Buyer buyer;
	JButton showItemsOnSaleButton;
	JButton showBoughtItemsButton;

	public BuyerWindow(Buyer buyer) {
		this.buyer = buyer;
		initialize(buyer.getUsername(), buyer.getRating());
		initializeButtons("Edit Account Details", "Show Items On Sale", "Show Bought Items");
		setEditAccountDetailsButtonListener(buyer.getName(), buyer.getEmailAddress(), buyer.getPassword(),
				buyer.getContactNumber(), buyer.getAddress());
		setItemsOnSaleButtonListener();
		setBoughtItemsButtonListener();
		configureButtons();
		addComponentsAndView();
	}

	public void initializeButtons(String buttonOneText, String buttonTwoText, String buttonThreeText) {
		editAccountDetailsButton = new JButton(buttonOneText);
		showItemsOnSaleButton = new JButton(buttonTwoText);
		showBoughtItemsButton = new JButton(buttonThreeText);
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
		innerContainer.add(showItemsOnSaleButton, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		innerContainer.add(showBoughtItemsButton, gbc);
	}

	public void setItemsOnSaleButtonListener() {
		showItemsOnSaleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] cats = (String[]) Initializer.getAvailableCategories().toArray(new String[0]);
				@SuppressWarnings({ "rawtypes", "unchecked" })
				JComboBox jcb = new JComboBox(cats);
				int result = JOptionPane.showConfirmDialog(null, jcb, "Select Item Category",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					if (jcb.getSelectedIndex() == 0) {
						ArrayList<Item> allItems = Initializer.getItems();
						if (allItems.size() == 0) {
							JOptionPane.showMessageDialog(null, "There are no items on sale", "Sold Items",
									JOptionPane.PLAIN_MESSAGE);
							return;
						}
						generateItemsFrame(allItems, "All Items On Sale");
					} else {
						ArrayList<Item> items = Initializer.getItemsByCategory(jcb.getSelectedItem().toString());
						generateItemsFrame(items,
								"Items In The " + jcb.getSelectedItem().toString() + " Category On Sale");
					}
				}

			}
		});
	}

	public void setBoughtItemsButtonListener() {
		showBoughtItemsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> boughtItems = buyer.viewBuyingRecords();
				if (boughtItems.size() == 0) {
					JOptionPane.showMessageDialog(null, "You have no bought items", "Bought Items",
							JOptionPane.PLAIN_MESSAGE);
					return;
				}
				String message = "";
				for (String s : boughtItems) {
					message = message + s + System.lineSeparator();
				}
				JOptionPane.showMessageDialog(null, message, "Bought Items", JOptionPane.PLAIN_MESSAGE);
			}
		});
	}

	public void generateItemsFrame(ArrayList<Item> items, String title) {
		JFrame frame = new JFrame(title);
		JPanel panel1 = new JPanel(new GridLayout(items.size(), 1));
		JScrollPane pane = new JScrollPane(panel1);
		for (Item item : items) {
			JPanel innerCell = new JPanel(new GridLayout(7, 1));
			innerCell.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
			innerCell.add(new JLabel("Name: " + item.getName()));
			innerCell.add(new JLabel("Price: " + item.getCost() + " RM"));
			innerCell.add(new JLabel("Description: " + item.getDescription()));
			innerCell.add(new JLabel("Category: " + item.getType()));
			innerCell.add(new JLabel("Method: " + item.getMethod()));
			innerCell.add(new JLabel("Seller Name: " + item.getSellerUsername() + ", Rating: "
					+ Initializer.getSellerByUsername(item.getSellerUsername()).getRating()));
			JButton buyButton = generateBuyButton(item);
			innerCell.add(buyButton);
			panel1.add(innerCell);
		}
		frame.add(pane);
		frame.setLocation(350, 200);
		frame.setSize(600, 400);
		frame.setVisible(true);
	}

	public JButton generateBuyButton(final Item item) {
		JButton buyButton = new JButton("Buy Now!");
		buyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int buttonClicked = generateBuyPrompt();
				if (buttonClicked != -1) {
					buyer.buyItem(item);
					JOptionPane.showMessageDialog(null, "You have successfully purchased " + item.getName(),
							"Successful Purchase", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		return buyButton;
	}

	public int generateBuyPrompt() {
		String[] paymentMethods = { "Credit Card", "Online Banking" };
		return JOptionPane.showOptionDialog(null, "Please choose your payment method", "Item Purchase",
				JOptionPane.PLAIN_MESSAGE, 0, null, paymentMethods, null);
	}

	@Override
	public void validateAndApplyEdits(String name, String emailAddress, String password, String contactNumber,
			String address) {
		if (name.length() != 0) {
			buyer.editName(name, 2);
		}
		if (emailAddress.length() != 0) {
			buyer.editEmailAddress(emailAddress, 2);
		}
		if (password.length() != 0) {
			buyer.editPassword(password, 2);
		}
		if (contactNumber.length() != 0) {
			buyer.editContactNumber(contactNumber, 2);
		}
		if (address.length() != 0) {
			buyer.editAddress(address, 2);
		}
		buyer = Initializer.getBuyerByUsername(buyer.getUsername());
		setEditAccountDetailsButtonListener(buyer.getName(), buyer.getEmailAddress(), buyer.getPassword(),
				buyer.getContactNumber(), buyer.getAddress());
	}
}
