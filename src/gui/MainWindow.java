package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public abstract class MainWindow extends JFrame {
	JPanel innerContainer;
	JPanel welcomeContainer;
	JPanel logoutContainer;
	JLabel welcomeMessage;
	JButton editAccountDetailsButton;
	JButton logoutButton;
	GridBagConstraints gbc;

	public void setEditAccountDetailsButtonListener(final String nameIn, final String emailAddressIn,
			final String passwordIn, final String contactNumberIn, final String addressIn) {
		for (ActionListener al : editAccountDetailsButton.getActionListeners()) {
			editAccountDetailsButton.removeActionListener(al);
		}
		editAccountDetailsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField name = new JTextField(5);
				name.setText(nameIn);
				JTextField emailAddress = new JTextField(5);
				emailAddress.setText(emailAddressIn);
				JPasswordField password = new JPasswordField(5);
				password.setText(passwordIn);
				JTextField contactNumber = new JTextField(5);
				contactNumber.setText(contactNumberIn);
				JTextField address = new JTextField(5);
				address.setText(addressIn);

				JPanel myPanel = new JPanel(new GridLayout(6, 2));
				myPanel.add(new JLabel("Name:"));
				myPanel.add(name);
				myPanel.add(new JLabel("Email Address:"));
				myPanel.add(emailAddress);
				myPanel.add(new JLabel("Password:"));
				myPanel.add(password);
				myPanel.add(new JLabel("Contact Number:"));
				myPanel.add(contactNumber);
				myPanel.add(new JLabel("Address:"));
				myPanel.add(address);

				int result = JOptionPane.showConfirmDialog(null, myPanel, "Edit Account Details",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					validateAndApplyEdits(name.getText(), emailAddress.getText(), new String(password.getPassword()),
							contactNumber.getText(), address.getText());
				}
			}
		});
	}

	public void setLogoutButtonListener() {
		logoutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public void configureButtons() {

	}

	public void initialize(String name, int rating) {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		welcomeContainer = new JPanel();
		logoutContainer = new JPanel();
		innerContainer = new JPanel(new GridBagLayout());
		gbc = new GridBagConstraints();
		logoutButton = new JButton("Logout");
		if (rating != -1) {
			welcomeMessage = new JLabel("<html>Welcome, " + name + "<br>Your rating is: " + rating + "</html>");
		} else {
			welcomeMessage = new JLabel("Welcome, " + name);
		}
	}

	public void addComponentsAndView() {
		setLogoutButtonListener();
		welcomeContainer.add(welcomeMessage);
		logoutContainer.add(logoutButton);
		add(welcomeContainer);
		add(innerContainer);
		add(logoutContainer);

		setTitle("Main Window");
		setLocation(350, 200);
		setSize(600, 400);
		setVisible(true);
	}

	public void generateInfoBox(ArrayList<String> data, String title) {
		String message = "";
		for (String s : data) {
			message = message + s + System.lineSeparator();
		}
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
	}

	public void validateAndApplyEdits(String name, String emailAddress, String password, String contactNumber,
			String address) {
		// This method is overridden in subclasses.
	}
}
