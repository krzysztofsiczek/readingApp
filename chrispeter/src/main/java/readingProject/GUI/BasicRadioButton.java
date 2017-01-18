package readingProject.GUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;

public class BasicRadioButton extends JRadioButton {

	private static final long serialVersionUID = 7040374258952093388L;

	public BasicRadioButton(String text) {
		super(text);
		setFont(new Font("georgia", Font.PLAIN, 14));
		setBackground(Color.LIGHT_GRAY);
	}
}
