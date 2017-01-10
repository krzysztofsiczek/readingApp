package readingProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class LoginPanel extends JPanel {
    private JFormattedTextField userName;
    private JPasswordField userPassword;
    private JButton loginButton;
 
    public LoginPanel(Listener listener) {
        super();
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.CENTER;
        gridBag.setConstraints(this, constraints);
        setLayout(gridBag);
        setBackground(Color.LIGHT_GRAY);
        createComponents();
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
 
        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(new BorderLayout());
        parentPanel.add(inputPanel, BorderLayout.CENTER);
        parentPanel.add(loginButton, BorderLayout.SOUTH);
 
        this.add(parentPanel);
    }
}