package readingProject;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class CheckingBookPanel extends JPanel implements ItemListener {

	final static String START = "Select one of the options.";
	final static String OWNEDBOOKS = "All my books.";
	final static String READBOOKS = "Books I have read.";
	final static String WANTTOBUYBOOKS = "Book I want to buy in the future.";

	LayoutManager flowLayout = new FlowLayout(1, 15, 10);
	JPanel allCards;

	public CheckingBookPanel() {
		super();
		LayoutManager borderLayout = new BorderLayout();
		setLayout(borderLayout);
		this.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));
		setBackground(Color.LIGHT_GRAY);
		addComponents();
	}

	private void addComponents() {

		JPanel comboBoxPanel = new JPanel(flowLayout);

		String comboBoxItems[] = { START, OWNEDBOOKS, READBOOKS, WANTTOBUYBOOKS };
		JComboBox comboBox = new JComboBox(comboBoxItems);
		comboBox.setEditable(false);
		comboBoxPanel.setBackground(Color.LIGHT_GRAY);
		comboBox.addItemListener(this);
		comboBoxPanel.add(comboBox);
		JButton returnButton = new JButton("Return");
		comboBoxPanel.add(returnButton);

		JPanel card1 = new BasicPanelGrid(1, 1);
		card1.add(new BasicTextArea());

		JPanel card2 = new BasicPanelGrid(1, 1);
		card2.add(new BasicTextArea());

		JPanel card3 = new BasicPanelGrid(1, 1);
		card3.add(new BasicTextArea());

		JPanel card4 = new BasicPanelGrid(1, 1);
		card4.add(new BasicTextArea());

		allCards = new JPanel(new CardLayout());
		allCards.add(card1, START);
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
}