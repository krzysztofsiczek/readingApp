package readingProject;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import readingProject.AddingBookPanel.Listener;

public class CheckingBookPanel extends JPanel implements ItemListener {

	private static final long serialVersionUID = 7035455204607828345L;
	final static String START = "Select one of the options.";
	final static String OWNEDBOOKS = "All my books.";
	final static String READBOOKS = "Books I have read.";
	final static String WANTTOBUYBOOKS = "Book I want to buy in the future.";

	private BasicFrame basicFrame;
	private Listener buttonListener;

	LayoutManager flowLayout = new FlowLayout(1, 15, 10);
	JPanel allCards;

	public CheckingBookPanel(BasicFrame basicFrame) {
		super();
		this.basicFrame = basicFrame;
		LayoutManager borderLayout = new BorderLayout();
		setLayout(borderLayout);
		this.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));
		setBackground(Color.LIGHT_GRAY);
		buttonListener = new Listener(basicFrame);
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
		returnButton.addActionListener(buttonListener);
		comboBoxPanel.add(returnButton);

		JPanel card1 = new BasicPanelGrid(1, 1);
		card1.add(new BasicTextArea("Choose one of the options"));

		JPanel card2 = new BasicPanelGrid(1, 1);
		card2.add(new BasicTextArea("These are all your books."));

		JPanel card3 = new BasicPanelGrid(1, 1);
		card3.add(new BasicTextArea("These books were lucky enough that you have read them."));

		JPanel card4 = new BasicPanelGrid(1, 1);
		card4.add(new BasicTextArea("These books you would love to buy if given a chance."));

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

	public class Listener implements ActionListener {

		private BasicFrame basicFrame;

		public Listener(BasicFrame basicFrame) {
			this.basicFrame = basicFrame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					DecisionPanel decisionPanel = new DecisionPanel(basicFrame);
					basicFrame.getContentPane().removeAll();
					basicFrame.add(decisionPanel);
					basicFrame.validate();
				}
			});
		}
	}

}