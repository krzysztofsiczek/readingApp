package readingProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BasicFrame extends JFrame {

	public BasicFrame() {
		super("ReadApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 500);
		setLocation(100, 100);

		LayoutManager borderLayout = new BorderLayout(10, 10);
		JPanel backgroundPanel = new JPanel();
		setContentPane(backgroundPanel);

		backgroundPanel.setBackground(Color.GRAY);
		backgroundPanel.setLayout(borderLayout);
		backgroundPanel.setBorder(BorderFactory.createEmptyBorder(80, 180, 80, 180));
		
		Listener listener = new Listener();
		LoginPanel loginPanel = new LoginPanel(listener);
		backgroundPanel.add(loginPanel, BorderLayout.CENTER);

//		backgroundPanel.removeAll();
		
		DecisionPanel decisionPanel = new DecisionPanel();
		backgroundPanel.add(decisionPanel, BorderLayout.CENTER);
	
		AddingBookPanel addingBookPanel = new AddingBookPanel();
		backgroundPanel.add(addingBookPanel, BorderLayout.CENTER);
		
		CheckingBookPanel checkingBookPanel = new CheckingBookPanel();
		backgroundPanel.add(checkingBookPanel, BorderLayout.CENTER);
		
		setVisible(true);
	}
}