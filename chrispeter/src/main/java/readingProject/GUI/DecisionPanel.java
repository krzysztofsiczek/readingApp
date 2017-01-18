package readingProject.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class DecisionPanel extends JPanel {

	private static final long serialVersionUID = -139708262772011188L;
	private BasicFrame basicFrame;
	private BasicTextArea quoteLabel;
	private JPanel choicePanel;

	public DecisionPanel(BasicFrame basicFrame) {
		super();
		this.basicFrame = basicFrame;
		LayoutManager borderLayout = new BorderLayout();
		setBackground(Color.LIGHT_GRAY);
		setLayout(borderLayout);
		addComponents();
	}

	private void addComponents() {

		JLabel addBookLabel = new JLabel("Add a book", JLabel.CENTER);
		addBookLabel.setFont(new Font("georgia", Font.ITALIC, 22));
		addBookLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		JLabel checkBookLabel = new JLabel("Check a book", JLabel.CENTER);
		checkBookLabel.setFont(new Font("georgia", Font.ITALIC, 24));
		checkBookLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

		JPanel basicPanelLeft = new BasicPanelGrid(1, 1);
		JPanel basicPanelRight = new BasicPanelGrid(1, 1);

		basicPanelLeft.add(addBookLabel);
		basicPanelRight.add(checkBookLabel);

		DecisionListener decisionListener = new DecisionListener(basicFrame, basicPanelLeft, basicPanelRight);
		basicPanelLeft.addMouseListener(decisionListener);
		basicPanelRight.addMouseListener(decisionListener);

		choicePanel = new JPanel();
		LayoutManager boxLayout = new BoxLayout(choicePanel, BoxLayout.X_AXIS);
		choicePanel.setLayout(boxLayout);
		choicePanel.setBackground(Color.GRAY);

		choicePanel.add(basicPanelLeft);
		choicePanel.add(Box.createRigidArea(new Dimension(80, 0)));
		choicePanel.add(basicPanelRight);

		quoteLabel = new BasicTextArea();
		quoteLabel.setText("\"Life is to be lived, not controlled.\"");
		quoteLabel.setWrapStyleWord(true);
		quoteLabel.setLineWrap(true);
		quoteLabel.setOpaque(false);
		quoteLabel.setEditable(false);
		quoteLabel.setFocusable(false);
		quoteLabel.setBackground(UIManager.getColor("Label.background"));
		quoteLabel.setFont(UIManager.getFont("Label.font"));
		quoteLabel.setBorder(UIManager.getBorder("Label.border"));

		this.add(choicePanel, BorderLayout.CENTER);
		this.add(quoteLabel, BorderLayout.PAGE_END);
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
