package readingProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddingBookPanel extends JPanel {

	public AddingBookPanel() {
		super();
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
		basicPanelRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		basicPanelRight.add(Box.createVerticalStrut(87));
		basicPanelRight.add(submitButton);
		
		this.add(basicPanelMiddle, BorderLayout.CENTER);
		this.add(basicPanelRight, BorderLayout.LINE_END);
	}
}