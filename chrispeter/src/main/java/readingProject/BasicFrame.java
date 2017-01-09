package readingProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

public class BasicFrame extends JFrame {

	public BasicFrame() {
		super();
	}

	public BasicFrame(String name) {
		super(name);
	}

	public void createBasicFrame() {
		BasicFrame basicFrame = new BasicFrame("ReadApp");
		basicFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		basicFrame.setSize(1000, 500);
		basicFrame.setLocation(100, 100);
		basicFrame.setResizable(false);

		LayoutManager borderLayout = new BorderLayout(10, 10);
		JPanel backgroundPanel = new JPanel();
		basicFrame.setContentPane(backgroundPanel);
		backgroundPanel.setBackground(Color.GRAY);
		backgroundPanel.setLayout(borderLayout);

		JPanel basicPanel = new JPanel();
		basicPanel.setBackground(Color.GRAY);

		LayoutManager boxLayout = new BoxLayout(basicPanel, BoxLayout.X_AXIS);
		basicPanel.setLayout(boxLayout);

		JLabel addBookLabel = new JLabel("Add a book", JLabel.CENTER);
		addBookLabel.setFont(new Font("georgia", Font.ITALIC, 22));
		JLabel checkBookLabel = new JLabel("Check a book", JLabel.CENTER);
		checkBookLabel.setFont(new Font("georgia", Font.ITALIC, 24));
				
		JPanel basicPanelLeft = new BasicPanel();
		basicPanelLeft.setLayout(new GridLayout(1, 1));
		basicPanelLeft.add(addBookLabel);

		JPanel basicPanelRight = new BasicPanel();
		basicPanelRight.setLayout(new GridLayout(1, 1));
		basicPanelRight.add(checkBookLabel);

		basicPanel.add(basicPanelLeft);
		basicPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		basicPanel.add(basicPanelRight);

		backgroundPanel.add(basicPanel, BorderLayout.CENTER);
		backgroundPanel.setBorder(BorderFactory.createEmptyBorder(100, 200, 100, 200));

		basicFrame.setVisible(true);

	}
	

}
