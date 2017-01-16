package readingProject.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import org.apache.commons.validator.routines.EmailValidator;

import readingProject.StoreData;

public class LoginPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 2500782151894671531L;

	private JFormattedTextField userName;
	private JPasswordField userPassword;
	private BasicButton loginButton;
	private BasicButton registerButton;
	private BasicLabel askingToRegisterLabel;
	private Listener loginListener;
	private BasicFrame basicFrame;
	private boolean isLoginDataCorrect;

	public LoginPanel(BasicFrame basicFrame) {
		super();
		this.basicFrame = basicFrame;
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.CENTER;
		gridBag.setConstraints(this, constraints);
		setLayout(gridBag);
		setBackground(Color.LIGHT_GRAY);
		this.loginListener = new Listener(basicFrame);
		createComponents();
	}

	@Override
	public void run() {
		basicFrame.getContentPane().removeAll();
		basicFrame.add(this, BorderLayout.CENTER);
		basicFrame.validate();
	}

	private void createComponents() {
		JLabel name = new JLabel("Name: ");
		JLabel password = new JLabel("Password: ");
		userName = new JFormattedTextField();
		userPassword = new JPasswordField();

		JPanel inputPanel = new BasicPanelGrid(2, 2);
		inputPanel.add(name);
		inputPanel.add(userName);
		inputPanel.add(password);
		inputPanel.add(userPassword);
		inputPanel.setBackground(Color.LIGHT_GRAY);
		loginButton = new BasicButton("Log in");
		loginButton.addActionListener(loginListener);
		loginButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		registerButton = new BasicButton("Register");
		registerButton.addActionListener(loginListener);
		registerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		registerButton.setBackground(new Color(220, 220, 220));
		registerButton.setOpaque(true);

		askingToRegisterLabel = new BasicLabel("Alternatively, you can:");
		askingToRegisterLabel.setFont(new Font("georgia", Font.ITALIC, 12));
		askingToRegisterLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		BasicPanelBox buttonPanel = new BasicPanelBox();
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		buttonPanel.add(loginButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		buttonPanel.add(askingToRegisterLabel);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		buttonPanel.add(registerButton);

		JPanel parentPanel = new JPanel();
		parentPanel.setLayout(new BorderLayout());
		parentPanel.add(inputPanel, BorderLayout.CENTER);
		parentPanel.add(buttonPanel, BorderLayout.SOUTH);

		this.add(parentPanel);
	}

	public class Listener implements ActionListener {

		private BasicFrame basicFrame;

		public Listener(BasicFrame basicFrame) {
			this.basicFrame = basicFrame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					if (registerButton == e.getSource()) {
						RegistrationPanel registrationPanel = new RegistrationPanel(basicFrame);
						basicFrame.getContentPane().removeAll();
						basicFrame.add(registrationPanel);
						basicFrame.validate();
					} else if (loginButton == e.getSource()) {
						validateLoginData();
						if (isLoginDataCorrect == true) {
							DecisionPanel decisionPanel = new DecisionPanel(basicFrame);
							basicFrame.getContentPane().removeAll();
							basicFrame.add(decisionPanel);
							basicFrame.validate();
						} else if (isLoginDataCorrect == false) {
							JOptionPane.showMessageDialog(getParent(), "Enter correct login information.",
									"Incorrect login data", JOptionPane.WARNING_MESSAGE);
						}

					}
				}
			});
		}
	}

	private void validateLoginData() {

		String userNameToBeCompared = userName.getText();
		String passwordToBeCompared = new String(userPassword.getPassword());

		StoreData checkingLoginData = new StoreData(userNameToBeCompared, passwordToBeCompared);
		isLoginDataCorrect = checkingLoginData.checkLoginData();
	}
}
