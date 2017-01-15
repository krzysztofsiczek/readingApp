package readingProject.GUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JCheckBox;

public class BasicCheckBox extends JCheckBox {

	private static final long serialVersionUID = -1856417371545620867L;

	public BasicCheckBox(String text) {
		super(text);
		setFont(new Font("georgia", Font.PLAIN, 14));
		setBackground(Color.LIGHT_GRAY);
	}
}
