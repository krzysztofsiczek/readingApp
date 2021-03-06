package readingProject.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.apache.commons.validator.routines.EmailValidator;

import readingProject.CheckData;
import readingProject.CheckWhetherUserExists;
import readingProject.StoreData;
import readingProject.StoreUserData;
import readingProject.Users;
import readingProject.Utilities.SHA256Hashing;
import readingProject.Utilities.ZonedTime;

public class RegistrationPanel extends JPanel {

	private static final long serialVersionUID = 8570423385856424239L;

	private BasicLabel userNameLabel;
	private BasicLabel userEmailLabel;
	private BasicLabel userPasswordLabel;
	private BasicTextArea userName;
	private BasicTextArea userEmail;
	private BasicPasswordField userPassword;
	private BasicLabel registrationWelcomeLabel;

	private BasicButton registerButton;
	private BasicButton returnButton;
	private Listener listener;
	private BasicFrame basicFrame;

	private BasicPanelGrid dataInputPanel;

	private boolean isDataCorrect = false;
	private boolean doesUserAlreadyExist = false;

	private Users userToBeAdded;

	private String plainTextPattern = "^[a-zA-Z0-9_ ]{0,30}$";

	private LocalDateTime userRegistrationDateTime;

	public RegistrationPanel(BasicFrame basicFrame) {
		super();
		this.basicFrame = basicFrame;
		LayoutManager borderLayout = new BorderLayout(20, 20);
		setLayout(borderLayout);
		setBackground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createEmptyBorder(80, 80, 80, 80));
		this.listener = new Listener();
		createComponents();
	}

	private void createComponents() {

		userNameLabel = new BasicLabel("User name:", 17);
		userEmailLabel = new BasicLabel("E-mail:", 17);
		userPasswordLabel = new BasicLabel("Password:", 17);

		userName = new BasicTextArea(30);
		userEmail = new BasicTextArea(30);
		userPassword = new BasicPasswordField();

		dataInputPanel = new BasicPanelGrid(3, 2, 5, 5);
		dataInputPanel.setBackground(Color.LIGHT_GRAY);

		dataInputPanel.add(userNameLabel);
		dataInputPanel.add(userName);
		dataInputPanel.add(userEmailLabel);
		dataInputPanel.add(userEmail);
		dataInputPanel.add(userPasswordLabel);
		dataInputPanel.add(userPassword);

		registerButton = new BasicButton("Register");
		registerButton.addActionListener(listener);

		returnButton = new BasicButton("Return");
		returnButton.setBackground(new Color(220, 220, 220));
		returnButton.setOpaque(true);
		returnButton.addActionListener(listener);

		registrationWelcomeLabel = new BasicLabel("Registration form:");
		registrationWelcomeLabel.setFont(new Font("georgia", Font.BOLD, 22));

		JPanel welcomePanel = new JPanel();
		LayoutManager borderLayout = new BorderLayout(5, 5);
		welcomePanel.setLayout(borderLayout);
		welcomePanel.setBackground(Color.LIGHT_GRAY);
		welcomePanel.add(registrationWelcomeLabel, BorderLayout.CENTER);
		welcomePanel.add(returnButton, BorderLayout.LINE_END);

		this.add(welcomePanel, BorderLayout.PAGE_START);
		this.add(dataInputPanel, BorderLayout.CENTER);
		this.add(registerButton, BorderLayout.PAGE_END);
	}

	public class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					if (returnButton == e.getSource()) {
						LoginPanel loginPanel = new LoginPanel(basicFrame);
						basicFrame.getContentPane().removeAll();
						basicFrame.add(loginPanel);
						basicFrame.validate();
					} else if (registerButton == e.getSource()) {
						validateRegistrationData();
						if (isDataCorrect == true) {
							String emailToBeAdded = userEmail.getText();
							CheckData checkWhetherUserExists = new CheckWhetherUserExists(emailToBeAdded);
							Integer exisitingUserId = checkWhetherUserExists.check();
							if (exisitingUserId != null) {
								doesUserAlreadyExist = true;
							}
							if (doesUserAlreadyExist == true) {
								JOptionPane.showMessageDialog(getParent(),
										"A user with this e-mail is already registered.", "User already exists",
										JOptionPane.WARNING_MESSAGE);
							} else {
								ZonedTime zonedTime = new ZonedTime();
								userRegistrationDateTime = zonedTime.checkCurrentDateTimeBeforePassingToDatabase();
								
								String passwordtoBeHashed = new String(userPassword.getPassword());
								String hashedPassword = null;
								
								SHA256Hashing encryptionMechanism = new SHA256Hashing(passwordtoBeHashed);
								
								try {
									hashedPassword = encryptionMechanism.encrypt();
								} catch (NoSuchAlgorithmException e1) {
									hashedPassword = new String(userPassword.getPassword());
									e1.printStackTrace();
								}
								
								userToBeAdded = new Users();
								userToBeAdded.setUserName(userName.getText());
								userToBeAdded.setEmail(userEmail.getText());
								userToBeAdded.setPassword(hashedPassword);
								userToBeAdded.setUserSince(userRegistrationDateTime);

								StoreData storeUserData = new StoreUserData(userToBeAdded);
								Integer newUserId = storeUserData.save();

								if (newUserId >= 0) {
									JOptionPane.showMessageDialog(getParent(), "Thank you for registering with us.",
											"User registered", JOptionPane.PLAIN_MESSAGE);
								}
								LoginPanel loginPanel = new LoginPanel(basicFrame);
								basicFrame.getContentPane().removeAll();
								basicFrame.add(loginPanel);
								basicFrame.validate();
							}
						} else {
							JOptionPane.showMessageDialog(getParent(), "Enter correct information.", "Incorrect data",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			});
		}
	}

	private void validateRegistrationData() {

		Pattern plainText = null;
		plainText = Pattern.compile(plainTextPattern);
		boolean isEmailValid;

		String userNameToBeCompared = userName.getText();
		String emailToBeCompared = userEmail.getText();
		String passwordToBeCompared = new String(userPassword.getPassword());

		Matcher userNameMatcher = plainText.matcher(userNameToBeCompared);

		if (userNameMatcher.matches()) {
			isEmailValid = EmailValidator.getInstance().isValid(emailToBeCompared);
			if (isEmailValid == true) {
				Matcher passwordMatcher = plainText.matcher(passwordToBeCompared);
				if (passwordMatcher.matches()) {
					isDataCorrect = true;
				}
			}
		}
	}
}
