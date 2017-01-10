package readingProject;

import java.awt.Font;
import javax.swing.JLabel;

public class BasicLabel extends JLabel {

	public BasicLabel(String text) {
		super(text);
		setFont(new Font("georgia", Font.PLAIN, 20));
		setHorizontalAlignment(JLabel.CENTER);
	}
}
