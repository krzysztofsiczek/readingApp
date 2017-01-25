package readingProject.GUI;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class ReadAppListSelectionHandler implements ListSelectionListener {

	private JTable bookDataTable;
	private static Integer bookIdForCurrentRow;

	ReadAppListSelectionHandler(JTable bookDataTable) {
		this.bookDataTable = bookDataTable;
	}

	@Override
	public void valueChanged(ListSelectionEvent listEvent) {
		ListSelectionModel currentSelectionModel = (ListSelectionModel) listEvent.getSource();
		int lastSelectedRowIndex = currentSelectionModel.getMaxSelectionIndex();
		bookIdForCurrentRow = (Integer) bookDataTable.getValueAt(lastSelectedRowIndex, 0);
	}

	public static Integer getBookIdForCurrentRow() {
		return bookIdForCurrentRow;
	}
}
