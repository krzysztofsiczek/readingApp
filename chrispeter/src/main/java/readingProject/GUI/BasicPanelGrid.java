package readingProject.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class BasicPanelGrid extends JPanel {

	private static final long serialVersionUID = -1887724804609850470L;

	public BasicPanelGrid(int rows, int columns) {
		setBackground(Color.LIGHT_GRAY);
		setMaximumSize(new Dimension(300, 200));
		setMinimumSize(new Dimension(150, 200));
		setLayout(new GridLayout(rows, columns));
	}
	
	public BasicPanelGrid(int rows, int columns, int hgap, int vgap) {
		setBackground(Color.LIGHT_GRAY);
		setMaximumSize(new Dimension(300, 200));
		setMinimumSize(new Dimension(150, 200));
		setLayout(new GridLayout(rows, columns, hgap, vgap));
	}
}
