package readingProject.GUI;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;

import exercises.DocumentSizeFilter;

public class BasicPasswordField extends JPasswordField {

	private static final long serialVersionUID = 2371448585083064679L;
	
	private static final int MAX_CHARACTERS = 30;
	private AbstractDocument abstractDocument;
	private Document document;

	public BasicPasswordField() {
		super();
		setBackground(Color.WHITE);
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		this.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 5, 5, 10)));
		document = this.getDocument();
		if (document instanceof AbstractDocument) {
			abstractDocument = (AbstractDocument) document;
			abstractDocument.setDocumentFilter(new DocumentSizeFilter(MAX_CHARACTERS));
		}
	}
}
