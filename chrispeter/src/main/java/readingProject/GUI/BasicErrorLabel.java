package readingProject.GUI;

import java.awt.Font;
import javax.swing.JLabel;

public class BasicErrorLabel extends JLabel {

	public BasicErrorLabel() {
		super("Incorrect data.");
		setFont(new Font("georgia", Font.PLAIN, 12));
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
	}
}

