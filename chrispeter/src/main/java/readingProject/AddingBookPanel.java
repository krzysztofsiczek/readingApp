package readingProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AddingBookPanel extends JPanel {

	private static final long serialVersionUID = -5083032799636807398L;
	private BasicFrame basicFrame;
	private Listener buttonListener;

	public AddingBookPanel(BasicFrame basicFrame) {
		super();
		this.basicFrame = basicFrame;
		LayoutManager borderLayout = new BorderLayout();
		setLayout(borderLayout);
		this.setBorder(BorderFactory.createEmptyBorder(60, 10, 60, 10));
		setBackground(Color.LIGHT_GRAY);
		addComponents();
	}

	private void addComponents() {

		JLabel addBookTitleLabel = new BasicLabel("Book title:");
		JLabel addBookAuthorLabel = new BasicLabel("Book author:");
		JLabel addBookIsbnLabel = new BasicLabel("ISBN:");
		JLabel addGenreLabel = new BasicLabel("Genre:");
		JLabel addPublicationYearLabel = new BasicLabel("Publication year:");
		JTextField bookTitleField = new JTextField();
		JTextField bookAuthorField = new JTextField();
		JTextField bookIsbnField = new JTextField();
		JTextField genreField = new JTextField();
		JTextField publicationYearField = new JTextField();

		JPanel basicPanelMiddle = new BasicPanelGrid(5, 2);
		basicPanelMiddle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		basicPanelMiddle.add(addBookTitleLabel);
		basicPanelMiddle.add(bookTitleField);

		basicPanelMiddle.add(addBookAuthorLabel);
		basicPanelMiddle.add(bookAuthorField);
		basicPanelMiddle.add(addBookIsbnLabel);
		basicPanelMiddle.add(bookIsbnField);
		basicPanelMiddle.add(addGenreLabel);
		basicPanelMiddle.add(genreField);
		basicPanelMiddle.add(addPublicationYearLabel);
		basicPanelMiddle.add(publicationYearField);

		JPanel basicPanelRight = new BasicPanelBox();
		JButton submitButton = new JButton("Submit");
		JButton deleteButton = new JButton("Delete");
		JButton returnButton = new JButton("Return");

		buttonListener = new Listener(basicFrame, submitButton, deleteButton, returnButton);

		submitButton.addActionListener(buttonListener);
		deleteButton.addActionListener(buttonListener);
		returnButton.addActionListener(buttonListener);

		basicPanelRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		basicPanelRight.add(Box.createVerticalStrut(62));
		basicPanelRight.add(submitButton);
		basicPanelRight.add(Box.createRigidArea(new Dimension(0, 18)));
		basicPanelRight.add(deleteButton);
		basicPanelRight.add(Box.createRigidArea(new Dimension(0, 18)));
		basicPanelRight.add(returnButton);

		this.add(basicPanelMiddle, BorderLayout.CENTER);
		this.add(basicPanelRight, BorderLayout.LINE_END);
	}

	public class Listener implements ActionListener {

		private BasicFrame basicFrame;
		private JButton submitButton;
		private JButton deleteButton;
		private JButton returnButton;

		public Listener(BasicFrame basicFrame, JButton submitButton, JButton deleteButton, JButton returnButton) {
			this.basicFrame = basicFrame;
			this.submitButton = submitButton;
			this.deleteButton = deleteButton;
			this.returnButton = returnButton;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					if (submitButton == (JButton) e.getSource()) {
						//TODO background logic 						
						AddingBookPanel addingBookPanel = new AddingBookPanel(basicFrame);
						basicFrame.getContentPane().removeAll();
						basicFrame.add(addingBookPanel);
					} else if (deleteButton == (JButton) e.getSource()) {
						//TODO background logic 
						AddingBookPanel addingBookPanel = new AddingBookPanel(basicFrame);
						basicFrame.getContentPane().removeAll();
						basicFrame.add(addingBookPanel);
					} else if (returnButton == (JButton) e.getSource()) {
						DecisionPanel decisionPanel = new DecisionPanel(basicFrame);
						basicFrame.getContentPane().removeAll();
						basicFrame.add(decisionPanel);
					}
					basicFrame.validate();
				}
			});
		}
	}
}
