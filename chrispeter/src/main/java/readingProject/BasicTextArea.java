package readingProject;

import java.awt.Color;
import javax.swing.JTextArea;

public class BasicTextArea extends JTextArea {

	private static final long serialVersionUID = -2521498711181370595L;

	public BasicTextArea(String text) {
		super(50, 1);
		append(text);
		setBackground(Color.WHITE);
		setLineWrap(true);
		setWrapStyleWord(true);
	}
}
