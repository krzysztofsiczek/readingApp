package readingProject.GUI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import exercises.DocumentSizeFilter;

public class BasicTextPane extends JTextPane {

	private static final long serialVersionUID = -2521498711181370595L;
	private static final int MAX_CHARACTERS = 60;
	private AbstractDocument abstractDocument;
	private StyledDocument styledDocument;

	public BasicTextPane() {
		super();
		setBackground(Color.WHITE);
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		this.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		styledDocument = this.getStyledDocument();
		if (styledDocument instanceof AbstractDocument) {
			abstractDocument = (AbstractDocument) styledDocument;
			abstractDocument.setDocumentFilter(new DocumentSizeFilter(MAX_CHARACTERS));
		}

		SimpleAttributeSet center = new SimpleAttributeSet();
	//	StyleConstants.set(center, StyleConstants.ALIGN_CENTER);
		setParagraphAttributes(center, true);
	}
}
