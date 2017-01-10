package readingProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DecisionPanel extends JPanel {

	private static final long serialVersionUID = -139708262772011188L;
	private BasicFrame basicFrame;

	public DecisionPanel(BasicFrame basicFrame) {
		super();
		this.basicFrame = basicFrame;
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
		JPanel basicPanelRight = new BasicPanelGrid(1, 1);

		basicPanelLeft.add(addBookLabel);
		basicPanelRight.add(checkBookLabel);

		DecisionListener decisionListener = new DecisionListener(basicFrame, basicPanelLeft, basicPanelRight);
		basicPanelLeft.addMouseListener(decisionListener);
		basicPanelRight.addMouseListener(decisionListener);

		this.add(basicPanelLeft);
		this.add(Box.createRigidArea(new Dimension(30, 0)));
		this.add(basicPanelRight);
	}

	public class DecisionListener implements MouseListener {

		private BasicFrame basicFrame;
		JPanel basicPanelLeft;
		JPanel basicPanelRight;

		public DecisionListener(BasicFrame basicFrame, JPanel basicPanelLeft, JPanel basicPanelRight) {
			this.basicFrame = basicFrame;
			this.basicPanelLeft = basicPanelLeft;
			this.basicPanelRight = basicPanelRight;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					AddingBookPanel addingBookPanel = new AddingBookPanel(basicFrame);
					CheckingBookPanel checkingBookPanel = new CheckingBookPanel(basicFrame);
					basicFrame.getContentPane().removeAll();
					if (basicPanelLeft == (JPanel) e.getSource()) {
						basicFrame.add(addingBookPanel);
					} else if (basicPanelRight == (JPanel) e.getSource()) {
						basicFrame.add(checkingBookPanel);
					}
					basicFrame.validate();
				}
			});
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}
}
