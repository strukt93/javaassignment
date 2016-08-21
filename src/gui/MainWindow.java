package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	JPanel innerContainer;
	JPanel welcomeContainer;
	JPanel logoutContainer;
	JLabel welcomeMessage;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton logoutButton;
	GridBagConstraints gbc;

	public void setEditAccountDetailsButtonListener() {
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField name = new JTextField(5);
				JTextField emailAddress = new JTextField(5);
				JPasswordField password = new JPasswordField(5);
				JTextField contactNumber = new JTextField(5);
				JTextField address = new JTextField(5);

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
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipadx = 30;
		gbc.ipady = 30;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		innerContainer.add(button1, gbc);
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

	public void createComponents() {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		welcomeContainer = new JPanel();
		logoutContainer = new JPanel();
		innerContainer = new JPanel(new GridBagLayout());
		gbc = new GridBagConstraints();
		logoutButton = new JButton("Logout");
	}

	public void addComponentsAndView() {
		setEditAccountDetailsButtonListener();
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

	public void validateAndApplyEdits(String name, String emailAddress, String password, String contactNumber,
			String address) {
		// This method is overridden in subclasses.
	}
}
