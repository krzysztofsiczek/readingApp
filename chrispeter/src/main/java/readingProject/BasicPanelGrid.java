package readingProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class BasicPanelGrid extends JPanel {

	public BasicPanelGrid(int rows, int columns) {
		setBackground(Color.LIGHT_GRAY);
		setMaximumSize(new Dimension(300, 200));
		setMinimumSize(new Dimension(150, 200));
		setLayout(new GridLayout(rows, columns));
	}
}
