package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import engine.Authentication;
import entities.Administrator;
import entities.Buyer;
import entities.Seller;

@SuppressWarnings("serial")
public class LoginWindow extends JFrame {
	JButton loginButton;
	JPanel panel;
	JPanel container;
	JPanel radioButtons;
	JRadioButton admin;
	JRadioButton seller;
	JRadioButton buyer;
	JTextField usernameField;
	JPasswordField passwordField;
	JLabel usernameLabel;
	JLabel passwordlabel;
	JLabel message;
	int x = 0;

	public LoginWindow() {
		super("Login");
	}

	public void initialize() {
		loginButton = new JButton("Login");
		panel = new JPanel();
		container = new JPanel();
		radioButtons = new JPanel();
		message = new JLabel();
		admin = new JRadioButton("Admin");
		buyer = new JRadioButton("Buyer");
		seller = new JRadioButton("Seller");
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		usernameLabel = new JLabel("Username:");
		passwordlabel = new JLabel("Password:");
	}

	public void addComponents() {
		panel.add(usernameLabel);
		panel.add(usernameField);
		panel.add(passwordlabel);
		panel.add(passwordField);
		radioButtons.add(admin);
		radioButtons.add(buyer);
		radioButtons.add(seller);
		container.add(panel);
		container.add(radioButtons);
		container.add(loginButton);
		container.add(message);
		add(container);
	}

	public void setupAndShow() {
		initialize();
		setLoginButtonListener(loginButton);

		admin.setSelected(true);
		setRadioButtonListener(admin);
		setRadioButtonListener(buyer);
		setRadioButtonListener(seller);

		panel.setLayout(new GridLayout(0, 2));
		container.setLayout(new GridLayout(0, 1));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setForeground(Color.RED);

		addComponents();

		setSize(400, 400);
		setLocationRelativeTo(null);
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void setLoginButtonListener(final JButton b) {
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (admin.isSelected()) {
					Administrator admin = Authentication.adminLogin(usernameField.getText(),
							new String(passwordField.getPassword()));
					if (admin == null) {
						message.setText("Incorrect admin credentials");
					} else {
						setVisible(false);
						new AdminWindow(admin);
					}
				}
				if (buyer.isSelected()) {
					Buyer buyer = Authentication.buyerLogin(usernameField.getText(),
							new String(passwordField.getPassword()));
					if (buyer == null) {
						message.setText("Incorrect buyer credentials");
					} else {
						setVisible(false);
						new BuyerWindow(buyer);
					}
				}
				if (seller.isSelected()) {
					Seller seller = Authentication.sellerLogin(usernameField.getText(),
							new String(passwordField.getPassword()));
					if (seller == null) {
						message.setText("Incorrect seller credentials");
					} else {
						setVisible(false);
						new SellerWindow(seller);
					}
				}
			}
		});
	}

	public void setRadioButtonListener(final JRadioButton b) {
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
				if (component.equals(admin)) {
					buyer.setSelected(false);
					seller.setSelected(false);
				}
				if (component.equals(seller)) {
					buyer.setSelected(false);
					admin.setSelected(false);
				}
				if (component.equals(buyer)) {
					admin.setSelected(false);
					seller.setSelected(false);
				}
			}
		});
	}
}
