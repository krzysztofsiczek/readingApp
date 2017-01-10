package readingProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BasicFrame extends JFrame {

	private static final long serialVersionUID = 2517433524273385317L;

	public BasicFrame() throws InterruptedException {
		super("ReadApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 500);
		setLocation(100, 100);

		LayoutManager borderLayout = new BorderLayout(10, 10);
		JPanel backgroundPanel = new JPanel();
		setContentPane(backgroundPanel);

		backgroundPanel.setBackground(Color.GRAY);
		backgroundPanel.setLayout(borderLayout);
		backgroundPanel.setBorder(BorderFactory.createEmptyBorder(60, 160, 60, 160));
		BasicLabel welcomeLabel = new BasicLabel("Welcome to ReadApp! :)");
		backgroundPanel.add(welcomeLabel, BorderLayout.CENTER);
		
/*		Listener listener = new Listener(this);
		
*/
//		backgroundPanel.add(loginPanel, BorderLayout.CENTER);
		
//		DecisionListener decisionListener = new DecisionListener(this);
//		DecisionPanel decisionPanel = new DecisionPanel(decisionListener);
		
/*		DecisionPanel decisionPanel = new DecisionPanel();
		backgroundPanel.add(decisionPanel, BorderLayout.CENTER);
	
		AddingBookPanel addingBookPanel = new AddingBookPanel();
		backgroundPanel.add(addingBookPanel, BorderLayout.CENTER);
		
		CheckingBookPanel checkingBookPanel = new CheckingBookPanel();
		backgroundPanel.add(checkingBookPanel, BorderLayout.CENTER);*/
		
		setVisible(true);
	}
}