package readingProject.GUI;

import java.awt.Font;
import javax.swing.JLabel;

public class BasicLabel extends JLabel {

	private static final long serialVersionUID = -4250306060272363185L;

	public BasicLabel(String text) {
		super(text);
		setFont(new Font("georgia", Font.PLAIN, 20));
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
	}
	
	public BasicLabel(String text, int size) {
		super(text);
		setFont(new Font("georgia", Font.PLAIN, size));
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
	}
}
