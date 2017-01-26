package readingProject.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import readingProject.CheckData;
import readingProject.CheckUserLoginData;
import readingProject.UserInstance;
import readingProject.Utilities.SHA256Hashing;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 2500782151894671531L;

	private JFormattedTextField userEmail;
	private JPasswordField userPassword;
	private BasicButton loginButton;
	private BasicButton registerButton;
	private BasicLabel askingToRegisterLabel;
	private Listener loginListener;
	private BasicFrame basicFrame;
	private boolean isLoginDataCorrect = false;
	private Integer userId;

	public LoginPanel(BasicFrame basicFrame) {
		super();
		this.basicFrame = basicFrame;
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.CENTER;
		gridBag.setConstraints(this, constraints);
		setLayout(gridBag);
		setBackground(Color.LIGHT_GRAY);
		this.loginListener = new Listener();
		createComponents();
	}

	private void createComponents() {
		JLabel email = new JLabel("User e-mail: ");
		JLabel password = new JLabel("Password: ");
		userEmail = new JFormattedTextField();
		userPassword = new JPasswordField();

		JPanel inputPanel = new BasicPanelGrid(2, 2);
		inputPanel.add(email);
		inputPanel.add(userEmail);
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
		buttonPanel.add(loginButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		buttonPanel.add(askingToRegisterLabel);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		buttonPanel.add(registerButton);

		BasicLabel welcomeLabel = new BasicLabel("Welcome to ReadApp! :)");

		JPanel parentPanel = new JPanel();
		parentPanel.setBackground(Color.LIGHT_GRAY);
		parentPanel.setPreferredSize(new Dimension(300, 200));
		parentPanel.setLayout(new BorderLayout(0, 20));
		parentPanel.add(welcomeLabel, BorderLayout.NORTH);
		parentPanel.add(inputPanel, BorderLayout.CENTER);
		parentPanel.add(buttonPanel, BorderLayout.SOUTH);

		this.add(parentPanel);
	}

	public class Listener implements ActionListener {

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
						try {
							validateLoginData();
						} catch (NoSuchAlgorithmException e1) {
							e1.printStackTrace();
						}
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

	private void validateLoginData() throws NoSuchAlgorithmException {
		String userEmailToBeCompared = userEmail.getText();
		String passwordToBeComparedBeforeHashin = new String(userPassword.getPassword());

		SHA256Hashing encryptionMechanism = new SHA256Hashing(passwordToBeComparedBeforeHashin);
		String passwordToBeCompared = encryptionMechanism.encrypt();

		CheckData checkUserLoginData = new CheckUserLoginData(userEmailToBeCompared, passwordToBeCompared);
		userId = checkUserLoginData.check();
		if (userId != null) {
			isLoginDataCorrect = true;
			UserInstance.setUserId(userId);
		}
	}
}
