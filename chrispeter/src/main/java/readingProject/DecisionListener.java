package readingProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

public class DecisionListener implements MouseListener {

	private final BasicFrame frame;
	private DecisionPanel decisionPanel;

	public DecisionListener(BasicFrame frame) {
		this.frame = frame;
	}

	public void setPanel(DecisionPanel decisionPanel) {
		this.decisionPanel = decisionPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked");
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				AddingBookPanel addingBookPanel = new AddingBookPanel();
				CheckingBookPanel checkingBookPanel = new CheckingBookPanel();
				addingBookPanel = new AddingBookPanel();
				checkingBookPanel = new CheckingBookPanel();
				frame.getContentPane().removeAll();
				// if depending on source TODO
				frame.add(addingBookPanel);
//				frame.add(checkingBookPanel);
				frame.validate();
			}
		});
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
