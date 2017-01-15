package readingProject.GUI;

import java.awt.Font;
import javax.swing.JLabel;

public class BasicEmptyLabel extends JLabel {

	public BasicEmptyLabel() {
		super();
		setFont(new Font("georgia", Font.PLAIN, 2));
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
	}
}
