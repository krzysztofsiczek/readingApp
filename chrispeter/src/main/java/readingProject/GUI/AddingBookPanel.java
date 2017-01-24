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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import readingProject.Books;
import readingProject.Interactions;
import readingProject.StoreBookData;
import readingProject.UserInstance;
import readingProject.Users;

public class AddingBookPanel extends JPanel {

	private static final long serialVersionUID = -5083032799636807398L;

	private BasicFrame basicFrame;
	private Listener buttonListener;

	private JPanel basicPanelMiddle;
	private JPanel basicPanelRight;
	private BasicButton submitButton;
	private BasicButton returnButton;

	private BasicTextArea bookTitleField;
	private BasicTextArea bookAuthorField;
	private BasicTextArea genreField;
	private BasicTextArea publicationYearField;

	private JLabel addBookTitleLabel;
	private JLabel addBookAuthorLabel;
	private JLabel addGenreLabel;
	private JLabel addPublicationYearLabel;

	private static final String plainTextPattern = "^[a-zA-Z0-9_ '.-/,:]{0,50}$";
	private static final String yearsPattern = "([0-9]|[1-8][0-9]|9[0-9]|[1-8][0-9]{2}|9[0-8][0-9]|99[0-9]|1[0-9]{3}|200[0-9]|201[0-7])";

	private BasicRadioButton ifHasRead;
	private BasicRadioButton ifHasGot;
	private BasicRadioButton ifWantsToBuy;
	private BasicRadioButton invisibleButton;
	private int selectedCountHasBook = 0;
	private int selectedCountWantsToBuyBook = 0;
	private ButtonGroup radioButtonsGroup;

	private BasicErrorLabel basicErrorLabel;

	private boolean isUserInputCorrect = false;
	private boolean isBookTitleCorrect = false;

	public AddingBookPanel(BasicFrame basicFrame) {
		super();
		this.basicFrame = basicFrame;
		LayoutManager borderLayout = new BorderLayout();
		setLayout(borderLayout);
		this.setBorder(BorderFactory.createEmptyBorder(60, 5, 40, 5));
		setBackground(Color.LIGHT_GRAY);
		addComponents();
	}

	private void addComponents() {
		addBookTitleLabel = new BasicLabel("Book title:");
		addBookAuthorLabel = new BasicLabel("Book author:");
		addGenreLabel = new BasicLabel("Genre:");
		addPublicationYearLabel = new BasicLabel("Publication year:");

		bookTitleField = new BasicTextArea();
		bookTitleField.getDocument().addDocumentListener(new BookDocumentListener(bookTitleField));
		bookTitleField.addFocusListener(new BookFocusListener(bookTitleField));

		bookAuthorField = new BasicTextArea();
		bookAuthorField.getDocument().addDocumentListener(new BookDocumentListener(bookAuthorField));
		bookAuthorField.addFocusListener(new BookFocusListener(bookAuthorField));

		genreField = new BasicTextArea();
		genreField.getDocument().addDocumentListener(new BookDocumentListener(genreField));
		genreField.addFocusListener(new BookFocusListener(genreField));

		publicationYearField = new BasicTextArea();
		publicationYearField.getDocument().addDocumentListener(new BookDocumentListener(publicationYearField));
		publicationYearField.addFocusListener(new BookFocusListener(publicationYearField));

		basicPanelMiddle = new BasicPanelGrid(4, 2, 0, 5);
		basicPanelMiddle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		basicPanelMiddle.add(addBookTitleLabel);
		basicPanelMiddle.add(bookTitleField);

		basicPanelMiddle.add(addBookAuthorLabel);
		basicPanelMiddle.add(bookAuthorField);

		basicPanelMiddle.add(addGenreLabel);
		basicPanelMiddle.add(genreField);

		basicPanelMiddle.add(addPublicationYearLabel);
		basicPanelMiddle.add(publicationYearField);

		basicPanelRight = new BasicPanelBox();
		submitButton = new BasicButton("Submit");
		returnButton = new BasicButton("Return");

		buttonListener = new Listener(basicFrame, submitButton, returnButton);

		submitButton.addActionListener(buttonListener);
		returnButton.addActionListener(buttonListener);
		basicErrorLabel = new BasicErrorLabel();
		basicErrorLabel.setForeground(Color.LIGHT_GRAY);

		basicPanelRight.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		basicPanelRight.add(Box.createVerticalStrut(43));
		basicPanelRight.add(basicErrorLabel);
		basicPanelRight.add(Box.createRigidArea(new Dimension(0, 18)));
		basicPanelRight.add(submitButton);
		basicPanelRight.add(Box.createRigidArea(new Dimension(0, 18)));
		basicPanelRight.add(returnButton);

		radioButtonsGroup = new ButtonGroup();
		ifHasGot = new BasicRadioButton("I have the book.");
		ifHasRead = new BasicRadioButton("I have read the book.");
		ifWantsToBuy = new BasicRadioButton("I want to buy the book.");
		invisibleButton = new BasicRadioButton("");

		ifHasGot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedCountHasBook++;
				selectedCountWantsToBuyBook = 0;
				if (selectedCountHasBook > 1) {
					invisibleButton.setSelected(true);
					selectedCountHasBook = 0;
				}
			}
		});

		ifHasRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		ifWantsToBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedCountWantsToBuyBook++;
				selectedCountHasBook = 0;
				if (selectedCountWantsToBuyBook > 1) {
					invisibleButton.setSelected(true);
					selectedCountWantsToBuyBook = 0;
				}
			}
		});

		radioButtonsGroup.add(ifHasGot);
		radioButtonsGroup.add(ifWantsToBuy);
		radioButtonsGroup.add(invisibleButton);

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
							boolean isUserInteractionInputCorrect;
							isUserInteractionInputCorrect = validateUserInteractionsInput();
							if (isUserInteractionInputCorrect) {
								Books bookToBeAdded = new Books();
								bookToBeAdded.setBookTitle(bookTitleField.getText());
								bookToBeAdded.setBookAuthor(bookAuthorField.getText());
								bookToBeAdded.setGenre(genreField.getText());
								bookToBeAdded.setPublicationYear(Integer.parseInt(publicationYearField.getText()));

								StoreBookData storeBookData = new StoreBookData(bookToBeAdded);
								Users activeUser = UserInstance.getUserInstance();

								Interactions interactionToBeAdded = new Interactions();
								interactionToBeAdded.setBooks(bookToBeAdded);
								interactionToBeAdded.setUsers(activeUser);
								interactionToBeAdded.setHasGot(ifHasGot.isSelected());
								interactionToBeAdded.setHasRead(ifHasRead.isSelected());
								interactionToBeAdded.setWantsToBuy(ifWantsToBuy.isSelected());

								bookToBeAdded.addInteractions(interactionToBeAdded);
								storeBookData.save();

								JOptionPane.showMessageDialog(getParent(), "Thank you for adding a new book.",
										"Book added", JOptionPane.PLAIN_MESSAGE);
								DecisionPanel decisionPanel = new DecisionPanel(basicFrame);
								basicFrame.getContentPane().removeAll();
								basicFrame.add(decisionPanel);
							} else {
								JOptionPane.showMessageDialog(getParent(),
										"Enter correct information for your book. Remember that at least one of the check boxes needs to be checked.",
										"Incorrect data", JOptionPane.WARNING_MESSAGE);
							}

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

	private boolean validateUserInteractionsInput() {
		if (ifHasRead.isSelected()) {
			return true;
		} else if (ifHasGot.isSelected()) {
			return true;
		} else if (ifWantsToBuy.isSelected()) {
			return true;
		}
		return false;
	}
}
