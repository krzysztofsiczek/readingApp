package readingProject.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

public class LoginPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 2500782151894671531L;
	private JFormattedTextField userName;
	private JPasswordField userPassword;
	private JButton loginButton;
	private Listener loginListener;
	private BasicFrame basicFrame;

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
		loginButton = new JButton("Log in");
		loginButton.addActionListener(loginListener);

		JPanel parentPanel = new JPanel();
		parentPanel.setLayout(new BorderLayout());
		parentPanel.add(inputPanel, BorderLayout.CENTER);
		parentPanel.add(loginButton, BorderLayout.SOUTH);

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
					DecisionPanel decisionPanel = new DecisionPanel(basicFrame);
					basicFrame.getContentPane().removeAll();
					basicFrame.add(decisionPanel);
					basicFrame.validate();
					;
				}
			});
		}
	}
}
