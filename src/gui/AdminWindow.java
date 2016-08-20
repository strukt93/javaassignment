package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import entities.Administrator;

public class AdminWindow extends JFrame {
	Administrator admin;
	JPanel innerContainer;
	JLabel welcomeMessage;

	public AdminWindow(Administrator admin) {
		this.admin = admin;

		welcomeMessage = new JLabel("Welcome, " + admin.getUsername());
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipadx = 30;
		gbc.ipady = 30;
		gbc.insets = new Insets(10, 10, 10, 10);
		innerContainer = new JPanel(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		innerContainer.add(new JButton("Test1"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		innerContainer.add(new JButton("Test1"), gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		innerContainer.add(new JButton("Test1"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		innerContainer.add(new JButton("Test1"), gbc);
		add(welcomeMessage);
		add(innerContainer);

		setTitle("Main Window");
		setLocation(500, 250);
		setSize(600, 400);
		setVisible(true);
	}
}
