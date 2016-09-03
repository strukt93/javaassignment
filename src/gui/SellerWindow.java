package gui;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import engine.Initializer;
import entities.Item;
import entities.Seller;

@SuppressWarnings("serial")
public class SellerWindow extends MainWindow {
	Seller seller;
	JButton addItemButton;
	JButton showSoldItemsButton;
	JButton showListedItemsButton;
	JButton addCreditButton;

	public SellerWindow(Seller seller) {
		this.seller = seller;
		initialize(seller.getUsername(), seller.getRating());
		initializeButtons("Edit Account Details", "Add New Item", "Show Sold Items", "Show Listed Items",
				"Add Credit To Fee Account");
		setEditAccountDetailsButtonListener(seller.getName(), seller.getEmailAddress(), seller.getPassword(),
				seller.getContactNumber(), seller.getAddress());
		setAddItemButtonListener();
		setShowSoldItemsButtonListener();
		setShowListedItemsButtonListener();
		setAddCreditButtonListener();
		configureButtons();
		addComponentsAndView();
	}

	public void initializeButtons(String buttonOneText, String buttonTwoText, String buttonThreeText,
			String buttonFourText, String buttonFiveText) {
		editAccountDetailsButton = new JButton(buttonOneText);
		addItemButton = new JButton(buttonTwoText);
		showSoldItemsButton = new JButton(buttonThreeText);
		showListedItemsButton = new JButton(buttonFourText);
		addCreditButton = new JButton(buttonFiveText);
	}

	public void setAddItemButtonListener() {
		addItemButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// if (!seller.hasSufficientCredit()) {
				// JOptionPane.showMessageDialog(null, "You don't have
				// sufficient credit in your fee account.",
				// "Add New Item", JOptionPane.ERROR_MESSAGE);
				// return;
				// }
				JTextField itemName = new JTextField(5);
				JTextField category = new JTextField(5);
				JTextField price = new JTextField(5);
				JTextField description = new JTextField(5);
				JTextField method = new JTextField(5);

				JPanel myPanel = new JPanel(new GridLayout(6, 2));
				myPanel.add(new JLabel("Item Name:"));
				myPanel.add(itemName);
				myPanel.add(new JLabel("Item Category"));
				myPanel.add(category);
				myPanel.add(new JLabel("Item Price:"));
				myPanel.add(price);
				myPanel.add(new JLabel("Item Description:"));
				myPanel.add(description);
				myPanel.add(new JLabel("Method:"));
				myPanel.add(method);

				int result = JOptionPane.showConfirmDialog(null, myPanel, "Add New Item", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					if (itemName.getText().length() != 0 && category.getText().length() != 0
							&& price.getText().length() != 0 && description.getText().length() != 0
							&& method.getText().length() != 0) {
						if (isParsable(price.getText())) {
							if (!seller.hasSufficientCredit(Item.getSuccessFee(Double.parseDouble(price.getText())))) {
								JOptionPane.showMessageDialog(null,
										"You don't have sufficient credit in your fee account. Please add "
												+ Item.getSuccessFee(Double.parseDouble(price.getText())) + " RM",
										"Add New Item", JOptionPane.ERROR_MESSAGE);
								return;
							}
							String first = "" + category.getText().charAt(0);
							String itemCat = first.toUpperCase() + category.getText().substring(1);
							seller.addItem(new Item(itemName.getText(), seller.getUsername(), description.getText(),
									itemCat, method.getText(), Double.parseDouble(price.getText()), false));
							JOptionPane.showMessageDialog(null, "You have successfully added " + itemName.getText(),
									"Successful Item Addition", JOptionPane.PLAIN_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Please enter a numerical value for the Price.",
									"Error", JOptionPane.ERROR_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(null, "Please fill all the form fields before submission.",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});

	}

	public void setShowSoldItemsButtonListener() {
		showSoldItemsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> soldItems = seller.getSoldItems();
				if (soldItems.size() == 0) {
					JOptionPane.showMessageDialog(null, "You haven't sold any items", "Sold Items",
							JOptionPane.PLAIN_MESSAGE);
					return;
				}
				String message = "";
				for (String s : soldItems) {
					message = message + s + System.lineSeparator();
				}
				JOptionPane.showMessageDialog(null, message, "Bought Items", JOptionPane.PLAIN_MESSAGE);
			}
		});
	}

	public void setShowListedItemsButtonListener() {
		showListedItemsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> listedItems = seller.getListedItems();
				if (listedItems.size() == 0) {
					JOptionPane.showMessageDialog(null, "You haven't listed any items", "Listed Items",
							JOptionPane.PLAIN_MESSAGE);
					return;
				}
				String message = "";
				for (String s : listedItems) {
					message = message + s + System.lineSeparator();
				}
				JOptionPane.showMessageDialog(null, message, "Listed Items", JOptionPane.PLAIN_MESSAGE);
			}
		});
	}

	public void setAddCreditButtonListener() {
		addCreditButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField creditAmount = new JTextField(5);

				JPanel myPanel = new JPanel(new GridLayout(1, 2));
				myPanel.add(new JLabel("Credit Amount:"));
				myPanel.add(creditAmount);

				int result = JOptionPane.showConfirmDialog(null, myPanel, "Add Credit To Fee Account",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					if (isParsable(creditAmount.getText())) {
						seller.addFundsToFeeAccount(Math.abs(Integer.parseInt(creditAmount.getText())));
						JOptionPane.showMessageDialog(null,
								"You have successfully added " + Math.abs(Integer.parseInt(creditAmount.getText()))
										+ " RM to your fee account",
								"Successful Fund Addition", JOptionPane.PLAIN_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Please enter a numerical value for the amount.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	public boolean isParsable(String in) {
		try {
			Integer.parseInt(in);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
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
		innerContainer.add(addItemButton, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		innerContainer.add(showSoldItemsButton, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		innerContainer.add(showListedItemsButton, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		innerContainer.add(addCreditButton, gbc);
	}

	@Override
	public void validateAndApplyEdits(String name, String emailAddress, String password, String contactNumber,
			String address) {
		if (name.length() != 0) {
			seller.editName(name, 3);
		}
		if (emailAddress.length() != 0) {
			seller.editEmailAddress(emailAddress, 3);
		}
		if (password.length() != 0) {
			seller.editPassword(password, 3);
		}
		if (contactNumber.length() != 0) {
			seller.editContactNumber(contactNumber, 3);
		}
		if (address.length() != 0) {
			seller.editAddress(address, 3);
		}
		seller = Initializer.getSellerByUsername(seller.getUsername());
		setEditAccountDetailsButtonListener(seller.getName(), seller.getEmailAddress(), seller.getPassword(),
				seller.getContactNumber(), seller.getAddress());
	}
}
