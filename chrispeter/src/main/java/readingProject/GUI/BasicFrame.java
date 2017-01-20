package readingProject.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BasicFrame extends JFrame {

	private static final long serialVersionUID = 2517433524273385317L;
	JPanel backgroundPanel;
	
	public BasicFrame() {
		super("ReadApp");
		setBasicFrameSettings();
		createAndSetUpContentPane();
		createAndAddLoginPanel();
		displayApp();
	}

	private void setBasicFrameSettings() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 500);
		setLocation(100, 100);
	}

	private void createAndSetUpContentPane() {
		backgroundPanel = new JPanel();
		LayoutManager borderLayout = new BorderLayout(10, 10);
		backgroundPanel.setBackground(Color.GRAY);
		backgroundPanel.setLayout(borderLayout);
		backgroundPanel.setBorder(BorderFactory.createEmptyBorder(60, 160, 60, 160));
		this.setContentPane(backgroundPanel);
	}

	private void createAndAddLoginPanel() {
		LoginPanel loginPanel = new LoginPanel(this);
		backgroundPanel.add(loginPanel);		
	}

	private void displayApp() {
		setVisible(true);	
	}
}
