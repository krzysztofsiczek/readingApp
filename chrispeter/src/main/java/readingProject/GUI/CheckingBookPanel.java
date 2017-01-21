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

public class CheckingBookPanel extends JPanel implements ItemListener {

	private static final long serialVersionUID = 7035455204607828345L;
	private final static String INSTRUCTIONS = "Instructions";
	private final static String OWNEDBOOKS = "Book I own.";
	private final static String READBOOKS = "Books I have read.";
	private final static String WANTTOBUYBOOKS = "Books on my wish list.";
	private final static String INSTRUCTIONSTEXT = "In the following cards you will have your books according to three categories: books you personally own, books you have read and books that are on your wish list. Please, remember that if you delete a book from one category, it will not removed from other categories. In order to edit your books, simply change data directly in the table. However, remember that data have to stay consistent. For example, it is not possible to enter a word in the column \"publication year\".";
	private static final Integer INSTRUCTIONMAXLENGTH = INSTRUCTIONSTEXT.length();

	private BasicButton deleteButton;
	private BasicButton returnButton;

	private BasicFrame basicFrame;
	private Listener buttonListener;
	private JPanel allCards;

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
		String comboBoxItems[] = { INSTRUCTIONS, OWNEDBOOKS, READBOOKS, WANTTOBUYBOOKS };
		JComboBox<Object> comboBox = new JComboBox<Object>(comboBoxItems);
		comboBox.setEditable(false);
		comboBoxPanel.setBackground(Color.LIGHT_GRAY);
		comboBox.addItemListener(this);
		comboBoxPanel.add(comboBox);

		deleteButton = new BasicButton("Delete");
		returnButton = new BasicButton("Return");
		buttonListener = new Listener();
		deleteButton.addActionListener(buttonListener);
		returnButton.addActionListener(buttonListener);
		comboBoxPanel.add(deleteButton);
		comboBoxPanel.add(returnButton);

		JPanel card1 = new BasicPanelGrid(1, 1);
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
		card1.add(instructionsPanel);

		JPanel card2 = new BasicPanelGrid(1, 1);
		BookDataTable tableOwnedBooks = new BookDataTable();
		card2.add(tableOwnedBooks);

		JPanel card3 = new BasicPanelGrid(1, 1);
		BookDataTable tableReadBooks = new BookDataTable();
		card3.add(tableReadBooks);

		JPanel card4 = new BasicPanelGrid(1, 1);
		BookDataTable tablBooksToBeBought = new BookDataTable();
		card4.add(tablBooksToBeBought);

		allCards = new JPanel(new CardLayout());
		allCards.add(card1, INSTRUCTIONS);
		allCards.add(card2, OWNEDBOOKS);
		allCards.add(card3, READBOOKS);
		allCards.add(card4, WANTTOBUYBOOKS);

		this.add(comboBoxPanel, BorderLayout.PAGE_START);
		this.add(allCards, BorderLayout.CENTER);
	}

	public void itemStateChanged(ItemEvent evt) {
		CardLayout cardLayout = (CardLayout) (allCards.getLayout());
		cardLayout.show(allCards, (String) evt.getItem());
	}

	public class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					if (deleteButton == (JButton) e.getSource()) {
						// TODO background logic
						CheckingBookPanel checkingBookPanel = new CheckingBookPanel(basicFrame);
						basicFrame.getContentPane().removeAll();
						basicFrame.add(checkingBookPanel);
						basicFrame.validate();
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