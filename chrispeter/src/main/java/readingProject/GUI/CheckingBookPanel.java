package readingProject.GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import readingProject.DeleteBookData;
import readingProject.DeleteData;
import readingProject.DeleteInteractionsData;
import readingProject.UpdateBookData;
import readingProject.UpdateInteractionsData;
import readingProject.Users;

public class CheckingBookPanel extends JPanel implements ItemListener {

	private static final long serialVersionUID = 7035455204607828345L;
	private final static String INSTRUCTIONS = "Instructions";
	private final static String MYBOOKS = "All my books   ";
	private final static String INSTRUCTIONSTEXT = "In \"All my books\" card you can check and edit all books you have saved in the application. In order to edit your books, simply change data directly in the table and then click \"save\". However, remember that data have to stay consistent. For example, it is not possible to enter a word in the column \"publication year\". To delete a book, select the appropriate row in the table and then click \"remove\". Remember that once a book is removed, if you change your mind, you will need to add it again using \"Add a book\" panel. Enjoy :)";
	private static final Integer INSTRUCTIONMAXLENGTH = INSTRUCTIONSTEXT.length();

	private BasicButton saveButton;
	private BasicButton deleteButton;
	private BasicButton returnButton;

	private BasicFrame basicFrame;
	private Listener buttonListener;
	private JPanel allCards;
	private BookDataTablePanel allMyBooks;
	private Integer bookIdForBookToBeDeleted;

	public CheckingBookPanel(BasicFrame basicFrame) {
		super();
		this.basicFrame = basicFrame;
		LayoutManager borderLayout = new BorderLayout();
		setLayout(borderLayout);
		this.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));
		setBackground(Color.LIGHT_GRAY);
		addComponents();
	}

	private void addComponents() {
		LayoutManager flowLayout = new FlowLayout(1, 15, 10);
		JPanel comboBoxPanel = new JPanel(flowLayout);
		String comboBoxItems[] = { MYBOOKS, INSTRUCTIONS };
		JComboBox<Object> comboBox = new JComboBox<Object>(comboBoxItems);
		comboBox.setEditable(false);
		comboBox.addItemListener(this);
		comboBoxPanel.setBackground(Color.LIGHT_GRAY);
		comboBoxPanel.add(comboBox);

		saveButton = new BasicButton("Save");
		deleteButton = new BasicButton("Delete");
		returnButton = new BasicButton("Return");
		buttonListener = new Listener();
		saveButton.addActionListener(buttonListener);
		deleteButton.addActionListener(buttonListener);
		returnButton.addActionListener(buttonListener);
		comboBoxPanel.add(saveButton);
		comboBoxPanel.add(deleteButton);
		comboBoxPanel.add(returnButton);

		JPanel card1 = new BasicPanelGrid(1, 1);
		allMyBooks = new BookDataTablePanel();
		card1.add(allMyBooks);

		JPanel card2 = new BasicPanelGrid(1, 1);
		BasicTextArea instructionsTextArea = new BasicTextArea(INSTRUCTIONMAXLENGTH);
		instructionsTextArea.setText(INSTRUCTIONSTEXT);
		instructionsTextArea.setLineWrap(true);
		instructionsTextArea.setBackground(Color.LIGHT_GRAY);
		instructionsTextArea.setForeground(Color.DARK_GRAY);
		instructionsTextArea.setEditable(false);
		instructionsTextArea.setFocusable(false);
		instructionsTextArea.setFont(new Font("georgia", Font.BOLD + Font.ITALIC, 16));
		instructionsTextArea.setBorder(UIManager.getBorder("Label.border"));

		BasicPanelBox instructionsPanel = new BasicPanelBox();
		instructionsPanel.add(Box.createRigidArea(new Dimension(10, 30)));
		instructionsPanel.add(instructionsTextArea);
		card2.add(instructionsPanel);

		allCards = new JPanel(new CardLayout());
		allCards.add(card1, MYBOOKS);
		allCards.add(card2, INSTRUCTIONS);

		this.add(comboBoxPanel, BorderLayout.PAGE_START);
		this.add(allCards, BorderLayout.CENTER);
	}

	public void itemStateChanged(ItemEvent evt) {
		bookIdForBookToBeDeleted = null;
		CardLayout cardLayout = (CardLayout) (allCards.getLayout());
		cardLayout.show(allCards, (String) evt.getItem());
	}

	public class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					if (saveButton == (JButton) e.getSource()) {
						int numberOfRowsForCurrentTable = allMyBooks.getBookDataTable().getRowCount();
						for (int i = 0, numberOfRows = numberOfRowsForCurrentTable; i < numberOfRows; i++) {

							Integer bookId = (Integer) allMyBooks.getBookDataTable().getValueAt(i, 0);
							Users users = (Users) allMyBooks.getBookDataTable().getValueAt(i, 8);

							boolean newHasRead = (boolean) allMyBooks.getBookDataTable().getValueAt(i, 5);
							boolean newHasGot = (boolean) allMyBooks.getBookDataTable().getValueAt(i, 6);
							boolean newWantsToBuy = (boolean) allMyBooks.getBookDataTable().getValueAt(i, 7);

							UpdateInteractionsData updateInteractionsData = new UpdateInteractionsData(bookId, users,
									newHasRead, newHasGot, newWantsToBuy);
							updateInteractionsData.updateData();

							String newBookTitle = (String) allMyBooks.getBookDataTable().getValueAt(i, 1);
							String newBookAuthor = (String) allMyBooks.getBookDataTable().getValueAt(i, 2);
							String newGenre = (String) allMyBooks.getBookDataTable().getValueAt(i, 3);
							Integer newPublicationYear = (Integer) allMyBooks.getBookDataTable().getValueAt(i, 4);

							UpdateBookData updateBookData = new UpdateBookData(bookId, newBookTitle, newBookAuthor,
									newGenre, newPublicationYear);
							updateBookData.updateData();
						}

						DecisionPanel decisionPanel = new DecisionPanel(basicFrame);
						basicFrame.getContentPane().removeAll();
						basicFrame.add(decisionPanel);
						basicFrame.validate();

					} else if (deleteButton == (JButton) e.getSource()) {
						bookIdForBookToBeDeleted = ReadAppListSelectionHandler.getBookIdForCurrentRow();
						if (bookIdForBookToBeDeleted != null) {
							DeleteData deleteInteractionsData = new DeleteInteractionsData();
							deleteInteractionsData.delete(bookIdForBookToBeDeleted);
							DeleteData deleteBookData = new DeleteBookData();
							deleteBookData.delete(bookIdForBookToBeDeleted);

							CheckingBookPanel checkingBookPanel = new CheckingBookPanel(basicFrame);
							basicFrame.getContentPane().removeAll();
							basicFrame.add(checkingBookPanel);
							basicFrame.validate();
						}

					} else if (returnButton == (JButton) e.getSource()) {
						DecisionPanel decisionPanel = new DecisionPanel(basicFrame);
						basicFrame.getContentPane().removeAll();
						basicFrame.add(decisionPanel);
						basicFrame.validate();
					}
				}

			});
		}
	}
}
