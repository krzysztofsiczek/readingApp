package readingProject.GUI;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BasicTable extends JPanel {

	private static final long serialVersionUID = 4714465175669410754L;

	public BasicTable() {
        super(new GridLayout(1,0));

        String[] columns = {"Book title", "Book author", "Genre", "Publication year"};
        Object[][] bookData = { };

        final JTable bookTable = new JTable(bookData, columns);
        bookTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        bookTable.setFillsViewportHeight(true);

       /* if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }
*/
        JScrollPane scrollPane = new JScrollPane(bookTable);
        add(scrollPane);
    }
}
