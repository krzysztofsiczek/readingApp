package readingProject.GUI;

import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class BasicPanelBox extends JPanel {

	private static final long serialVersionUID = 1749480406143284669L;

	public BasicPanelBox() {
		setBackground(Color.LIGHT_GRAY);
		LayoutManager boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(boxLayout);
	}
}
