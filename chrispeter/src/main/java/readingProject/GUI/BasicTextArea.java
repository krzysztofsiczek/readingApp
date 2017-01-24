package readingProject.GUI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;

public class BasicTextArea extends JTextArea {

	private static final long serialVersionUID = -2521498711181370595L;
	private static final int MAX_CHARACTERS = 50;
	private AbstractDocument abstractDocument;
	private Document document;

	public BasicTextArea() {
		super();
		setBackground(Color.WHITE);
		setLineWrap(true);
		setWrapStyleWord(true);
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		this.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(15, 10, 5, 10)));
		document = this.getDocument();
		if (document instanceof AbstractDocument) {
			abstractDocument = (AbstractDocument) document;
			abstractDocument.setDocumentFilter(new DocumentSizeFilter(MAX_CHARACTERS));
		}
	}

	public BasicTextArea(int length) {
		super();
		setBackground(Color.WHITE);
		setWrapStyleWord(true);
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		this.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 5, 5, 10)));
		document = this.getDocument();
		if (document instanceof AbstractDocument) {
			abstractDocument = (AbstractDocument) document;
			abstractDocument.setDocumentFilter(new DocumentSizeFilter(length));
		}
	}

	public BasicTextArea(String text) {
		super();
		append(text);
		setBackground(Color.WHITE);
		setLineWrap(true);
		setWrapStyleWord(true);
	}

}
