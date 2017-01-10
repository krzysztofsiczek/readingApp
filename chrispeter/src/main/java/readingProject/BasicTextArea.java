package readingProject;

import java.awt.Color;
import javax.swing.JTextArea;

public class BasicTextArea extends JTextArea {

	public BasicTextArea() {
		super(50, 1);
		setBackground(Color.WHITE);
		setLineWrap(true);
		setWrapStyleWord(true);
	}
}
