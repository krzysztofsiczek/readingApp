package readingProject.GUI;

import java.awt.GridLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import readingProject.RetrieveBookData;
import readingProject.RetrieveData;
import readingProject.StoreBookData;

public class BookDataTable extends JPanel implements TableModelListener {

	private static final long serialVersionUID = -2284688431812545991L;

	private JTable bookDataTable;
	private String plainTextPattern = "^[a-zA-Z0-9_ '.]{0,50}$";
	private String yearsPattern = "([0-9]|[1-8][0-9]|9[0-9]|[1-8][0-9]{2}|9[0-8][0-9]|99[0-9]|1[0-9]{3}|200[0-9]|201[0-7])";
	private int rowForChangedData;
	private int columnForChangedData;
	private TableModel modelForChangedData;

	public BookDataTable() {
		super(new GridLayout(1, 0));
		createTable();
		createAndSetUpScrollPane();
	}

	private void createTable() {
		bookDataTable = new JTable(new BookDataTableModel());
		bookDataTable.setFillsViewportHeight(true);
		bookDataTable.getTableHeader().setReorderingAllowed(false);
		bookDataTable.getTableHeader().setResizingAllowed(false);
		bookDataTable.getModel().addTableModelListener(this);
	}

	private void createAndSetUpScrollPane() {
		JScrollPane scrollPane = new JScrollPane(bookDataTable);
		add(scrollPane);
	}

	class BookDataTableModel extends AbstractTableModel {

		private static final long serialVersionUID = 3374415168258792612L;

		private String[] columns = { "Book title", "Book author", "Genre", "Publication year" };

		RetrieveData retrieveBookData = new RetrieveBookData();
		
		Object[][] bookData = retrieveBookData.read();
			
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

		boolean dataCorrect = validateChangedData(changedData, nameOfColumnForChangedData);
		System.out.println(dataCorrect);
		if (dataCorrect) {
			// TODO introduce it to db

		}
	}

	private boolean validateChangedData(Object changedData, String nameOfColumnForChangedData) {
		String changedDataToBeValidated = changedData.toString();
		Matcher matcher = null;
		Pattern patternForPlainText = Pattern.compile(plainTextPattern);
		Pattern patternForYears = Pattern.compile(yearsPattern);

		if (nameOfColumnForChangedData.equals("Book title") || nameOfColumnForChangedData.equals("Book author")
				|| nameOfColumnForChangedData.equals("Genre")) {
			matcher = patternForPlainText.matcher(changedDataToBeValidated);
			if (matcher.matches()) {
				return true;
			} else {
				modelForChangedData.setValueAt("", rowForChangedData, columnForChangedData);
				return false;
			}
		} else if (nameOfColumnForChangedData.equals("Publication year")) {
			matcher = patternForYears.matcher(changedDataToBeValidated);
			if (matcher.matches()) {
				return true;
			} else {
				modelForChangedData.setValueAt(0, rowForChangedData, columnForChangedData);
				return false;
			}
		}
		return false;
	}
}
