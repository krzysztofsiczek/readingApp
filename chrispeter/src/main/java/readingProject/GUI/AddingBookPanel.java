package readingProject.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import readingProject.Book;
import readingProject.StoreData;

public class AddingBookPanel extends JPanel {

	private static final long serialVersionUID = -5083032799636807398L;
	
	private BasicFrame basicFrame;
	private Listener buttonListener;

	private JPanel basicPanelMiddle;
	private JPanel basicPanelRight;
	private JButton submitButton;
	private JButton returnButton;
	
	private BasicTextArea bookTitleField;
	private BasicTextArea bookAuthorField;
	private BasicTextArea bookIsbnField;
	private BasicTextArea genreField;
	private BasicTextArea publicationYearField;
	
	private JLabel addBookTitleLabel;
	private JLabel addBookAuthorLabel;
	private JLabel addBookIsbnLabel;
	private JLabel addGenreLabel;
	private JLabel addPublicationYearLabel;

	private String plainTextPattern = "^[a-zA-Z0-9_ ]{0,50}$";
	private String isbnPattern = "^[0-9]{10}||[0-9]{13}$";
	private String yearsPattern = "([0-9]|[1-8][0-9]|9[0-9]|[1-8][0-9]{2}|9[0-8][0-9]|99[0-9]|1[0-9]{3}|200[0-9]|201[0-7])";

	private BasicCheckBox ifHasRead;
	private BasicCheckBox ifHasGot;
	private BasicCheckBox ifWantsToBuy;
	
	private BasicErrorLabel basicErrorLabel;

	private boolean isUserInputCorrect = false;
	private boolean isBookTitleCorrect = false;

	public AddingBookPanel(BasicFrame basicFrame) {
		super();
		this.basicFrame = basicFrame;
		LayoutManager borderLayout = new BorderLayout();
		setLayout(borderLayout);
		this.setBorder(BorderFactory.createEmptyBorder(60, 10, 40, 10));
		setBackground(Color.LIGHT_GRAY);
		addComponents();
	}

	private void addComponents() {
		addBookTitleLabel = new BasicLabel("Book title:");
		addBookAuthorLabel = new BasicLabel("Book author:");
		addBookIsbnLabel = new BasicLabel("ISBN:");
		addGenreLabel = new BasicLabel("Genre:");
		addPublicationYearLabel = new BasicLabel("Publication year:");

		bookTitleField = new BasicTextArea();
		bookTitleField.getDocument().addDocumentListener(new BookDocumentListener(bookTitleField));
		bookTitleField.addFocusListener(new BookFocusListener(bookTitleField));
		
		bookAuthorField = new BasicTextArea();
		bookAuthorField.getDocument().addDocumentListener(new BookDocumentListener(bookAuthorField));
		bookAuthorField.addFocusListener(new BookFocusListener(bookAuthorField));
		
		bookIsbnField = new BasicTextArea();
		bookIsbnField.getDocument().addDocumentListener(new BookDocumentListener(bookIsbnField));
		bookIsbnField.addFocusListener(new BookFocusListener(bookIsbnField));
		
		genreField = new BasicTextArea();
		genreField.getDocument().addDocumentListener(new BookDocumentListener(genreField));
		genreField.addFocusListener(new BookFocusListener(genreField));
		
		publicationYearField = new BasicTextArea();
		publicationYearField.getDocument().addDocumentListener(new BookDocumentListener(publicationYearField));
		publicationYearField.addFocusListener(new BookFocusListener(publicationYearField));

		basicPanelMiddle = new BasicPanelGrid(5, 2, 5, 5);
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

		basicPanelRight = new BasicPanelBox();
		submitButton = new JButton("Submit");
		returnButton = new JButton("Return");

		buttonListener = new Listener(basicFrame, submitButton, returnButton);

		submitButton.addActionListener(buttonListener);
		returnButton.addActionListener(buttonListener);
		basicErrorLabel = new BasicErrorLabel();
		basicErrorLabel.setForeground(Color.LIGHT_GRAY);

		basicPanelRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		basicPanelRight.add(Box.createVerticalStrut(45));
		basicPanelRight.add(basicErrorLabel);
		basicPanelRight.add(Box.createRigidArea(new Dimension(0, 18)));
		basicPanelRight.add(submitButton);
		basicPanelRight.add(Box.createRigidArea(new Dimension(0, 18)));
		basicPanelRight.add(returnButton);

		ifHasGot = new BasicCheckBox("I have the book.");
		ifHasRead = new BasicCheckBox("I have read the book.");
		ifWantsToBuy = new BasicCheckBox("I want to buy the book.");
		
		JPanel basicPanelBottom = new JPanel();
		basicPanelBottom.setBackground(Color.LIGHT_GRAY);
		basicPanelBottom.add(Box.createRigidArea(new Dimension(0, 30)));
		basicPanelBottom.add(ifHasGot);
		basicPanelBottom.add(ifHasRead);
		basicPanelBottom.add(ifWantsToBuy);
		
		this.add(basicPanelMiddle, BorderLayout.CENTER);
		this.add(basicPanelRight, BorderLayout.LINE_END);
		this.add(basicPanelBottom, BorderLayout.PAGE_END);
	}

	private class Listener implements ActionListener {

		private BasicFrame basicFrame;
		private JButton submitButton;
		private JButton returnButton;

		public Listener(BasicFrame basicFrame, JButton submitButton, JButton returnButton) {
			this.basicFrame = basicFrame;
			this.submitButton = submitButton;
			this.returnButton = returnButton;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					if (submitButton == (JButton) e.getSource()) {
						if (isUserInputCorrect == true && isBookTitleCorrect == true) {
							JOptionPane.showMessageDialog(getParent(), "Thank you for adding a new book.", "Book added",
									JOptionPane.PLAIN_MESSAGE);

							Book bookToBeAdded = new Book();
							bookToBeAdded.setBookTitle(bookTitleField.getText());
							bookToBeAdded.setBookAuthor(bookAuthorField.getText());
							bookToBeAdded.setIsbn((Double.parseDouble(bookIsbnField.getText())));
							bookToBeAdded.setGenre(genreField.getText());
							bookToBeAdded.setPublicationYear(Integer.parseInt(publicationYearField.getText()));
					
							StoreData storingData = new StoreData(bookToBeAdded);
							
							AddingBookPanel addingBookPanel = new AddingBookPanel(basicFrame);
							basicFrame.getContentPane().removeAll();
							basicFrame.add(addingBookPanel);
						} else {
							JOptionPane.showMessageDialog(getParent(),
									"Enter correct information for your book. Remember that book title is required.",
									"Incorrect data", JOptionPane.WARNING_MESSAGE);
						}
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

	private class BookDocumentListener implements DocumentListener {

		private BasicTextArea textAreaToBeValidated;

		public BookDocumentListener(BasicTextArea textAreaToBeValidated) {
			super();
			this.textAreaToBeValidated = textAreaToBeValidated;
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			validateUserIntput(textAreaToBeValidated);
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			validateUserIntput(textAreaToBeValidated);
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
		}
	}

	private void validateUserIntput(BasicTextArea textAreaToBeValidated) {

		Pattern plainText = null;
		String textToBeCompared = textAreaToBeValidated.getText();

		if (bookTitleField == textAreaToBeValidated || bookAuthorField == textAreaToBeValidated
				|| genreField == textAreaToBeValidated) {
			plainText = Pattern.compile(plainTextPattern);
		} else if (bookIsbnField == textAreaToBeValidated) {
			plainText = Pattern.compile(isbnPattern);
		} else if (publicationYearField == textAreaToBeValidated) {
			plainText = Pattern.compile(yearsPattern);
		}

		Matcher matcher = plainText.matcher(textToBeCompared);

		if (matcher.matches()) {
			basicErrorLabel.setForeground(Color.LIGHT_GRAY);
			isUserInputCorrect = true;
			if (bookTitleField == textAreaToBeValidated) {
				isBookTitleCorrect = true;
			}
		} else {
			basicErrorLabel.setForeground(Color.RED);
			isUserInputCorrect = false;
		}
	}

	private class BookFocusListener implements FocusListener {

		private BasicTextArea textAreaToBeValidated;

		public BookFocusListener(BasicTextArea textAreaToBeValidated) {
			super();
			this.textAreaToBeValidated = textAreaToBeValidated;
		}

		@Override
		public void focusGained(FocusEvent e) {
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (isUserInputCorrect == false) {
				textAreaToBeValidated.setText("");
			}
		}
	}
}
