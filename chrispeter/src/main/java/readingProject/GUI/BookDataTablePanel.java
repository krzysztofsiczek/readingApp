package readingProject.GUI;

import java.awt.GridLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import readingProject.CompleteBookTable;

public class BookDataTablePanel extends JPanel implements TableModelListener {

	private static final long serialVersionUID = -2284688431812545991L;

	private JTable bookDataTable;
	private static final String plainTextPattern = "^[a-zA-Z0-9_ '.-/,:]{0,50}$";
	private static final String yearsPattern = "([0-9]|[1-8][0-9]|9[0-9]|[1-8][0-9]{2}|9[0-8][0-9]|99[0-9]|1[0-9]{3}|200[0-9]|201[0-7])";
	private int rowForChangedData;
	private int columnForChangedData;
	private TableModel modelForChangedData;
	private static BookDataTableModel BookDataTableModel;

	public BookDataTablePanel() {
		super(new GridLayout(1, 0));
		createTable();
		createAndSetUpScrollPane();
	}

	public JTable getBookDataTable() {
		return bookDataTable;
	}

	public void setBookDataTable(JTable bookDataTable) {
		this.bookDataTable = bookDataTable;
	}

	private void createTable() {
		bookDataTable = new JTable(new BookDataTableModel());
		bookDataTable.setFillsViewportHeight(true);
		bookDataTable.getTableHeader().setReorderingAllowed(false);
		bookDataTable.getTableHeader().setResizingAllowed(false);
		bookDataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		bookDataTable.getModel().addTableModelListener(this);

		bookDataTable.getColumnModel().getColumn(0).setMaxWidth(0);
		bookDataTable.getColumnModel().getColumn(0).setMinWidth(0);
		bookDataTable.getColumnModel().getColumn(0).setPreferredWidth(0);
		bookDataTable.getColumnModel().getColumn(0).setWidth(0);
		bookDataTable.getColumnModel().getColumn(1).setMinWidth(160);
		bookDataTable.getColumnModel().getColumn(2).setMinWidth(160);
		bookDataTable.getColumnModel().getColumn(3).setMinWidth(110);
		bookDataTable.getColumnModel().getColumn(4).setMinWidth(100);
		bookDataTable.getColumnModel().getColumn(5).setMinWidth(80);
		bookDataTable.getColumnModel().getColumn(6).setMinWidth(80);
		bookDataTable.getColumnModel().getColumn(7).setMinWidth(80);
	}

	private void createAndSetUpScrollPane() {
		JScrollPane scrollPane = new JScrollPane(bookDataTable);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
	}

	class BookDataTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 3374415168258792612L;

		public BookDataTableModel() {
		}

		private String[] columns = { "Book id", "Book title", "Book author", "Genre", "Publication", "Has got",
				"Has read", "To get" };

		CompleteBookTable completeBookTable = new CompleteBookTable();

		Object[][] bookData = completeBookTable.getCompleteBookTable();

		@Override
		public int getColumnCount() {
			return columns.length;
		}

		@Override
		public int getRowCount() {
			return bookData.length;
		}

		@Override
		public Object getValueAt(int row, int column) {
			return bookData[row][column];
		}

		public String getColumnName(int index) {
			return columns[index];
		}

		public Class<? extends Object> getColumnClass(int index) {
			return getValueAt(0, index).getClass();
		}

		public boolean isCellEditable(int row, int column) {
			if (column == 0) {
				return false;
			}
			return true;

		}

		public void setValueAt(Object value, int row, int column) {
			bookData[row][column] = value;
			fireTableCellUpdated(row, column);
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		rowForChangedData = e.getFirstRow();
		columnForChangedData = e.getColumn();
		modelForChangedData = (TableModel) e.getSource();
		String nameOfColumnForChangedData = modelForChangedData.getColumnName(columnForChangedData);
		Object changedData = modelForChangedData.getValueAt(rowForChangedData, columnForChangedData);
		validateChangedData(changedData, nameOfColumnForChangedData);
	}

	private void validateChangedData(Object changedData, String nameOfColumnForChangedData) {
		String changedDataToBeValidated = changedData.toString();
		Matcher matcher = null;
		Pattern patternForPlainText = Pattern.compile(plainTextPattern);
		Pattern patternForYears = Pattern.compile(yearsPattern);

		BookDataTablePanel.BookDataTableModel = new BookDataTableModel();

		if (nameOfColumnForChangedData.equals("Book title") || nameOfColumnForChangedData.equals("Book author")
				|| nameOfColumnForChangedData.equals("Genre")) {
			matcher = patternForPlainText.matcher(changedDataToBeValidated);
			if (!(matcher.matches())) {
				modelForChangedData.setValueAt(BookDataTableModel.getValueAt(rowForChangedData, columnForChangedData),
						rowForChangedData, columnForChangedData);
			}
		} else if (nameOfColumnForChangedData.equals("Publication year")) {
			matcher = patternForYears.matcher(changedDataToBeValidated);
			if (!(matcher.matches())) {
				modelForChangedData.setValueAt(BookDataTableModel.getValueAt(rowForChangedData, columnForChangedData),
						rowForChangedData, columnForChangedData);
			}
		}
	}
}
