package readingProject.GUI;

import java.awt.Dimension;
import javax.swing.JButton;

public class BasicButton extends JButton {

	private static final long serialVersionUID = -2303622895488670284L;

	public BasicButton(String text) {
		super(text);
	}
	
	public BasicButton(String text, int width, int height) {
		super(text);
		setPreferredSize(new Dimension (width, height));
	}
	
}
