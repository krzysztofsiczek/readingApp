package readingProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DecisionPanel extends JPanel {

	public DecisionPanel() {
		super();
		setBackground(Color.GRAY);
		LayoutManager boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
		setLayout(boxLayout);
		addComponents();
	}

	private void addComponents() {

		JLabel addBookLabel = new JLabel("Add a book", JLabel.CENTER);
		addBookLabel.setFont(new Font("georgia", Font.ITALIC, 22));
		JLabel checkBookLabel = new JLabel("Check a book", JLabel.CENTER);
		checkBookLabel.setFont(new Font("georgia", Font.ITALIC, 24));

		JPanel basicPanelLeft = new BasicPanelGrid(1, 1);
		basicPanelLeft.add(addBookLabel);

		JPanel basicPanelRight = new BasicPanelGrid(1, 1);
		basicPanelRight.add(checkBookLabel);

		this.add(basicPanelLeft);
		this.add(Box.createRigidArea(new Dimension(30, 0)));
		this.add(basicPanelRight);
	}
}