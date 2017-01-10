package readingProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

public class Listener implements ActionListener {

	private final BasicFrame frame;
	private LoginPanel loginPanel;
	private DecisionListener decisionListener;

	public Listener(BasicFrame frame) {
		this.frame = frame;
	}

	public void setPanel(LoginPanel loginPanel) {
		this.loginPanel = loginPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DecisionPanel decisionPanel = new DecisionPanel(decisionListener);
				frame.getContentPane().removeAll();
				frame.add(decisionPanel);
				frame.validate();
			}
		});
	}
}
