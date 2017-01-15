package readingProject.GUI;

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
		
		setVisible(true);
	}
}