package readingProject;

public class CompleteBookTable {

	private Object[][] completeBookTable;

	public CompleteBookTable() {
	}

	public Object[][] getCompleteBookTable() {

		RetrieveData retrieveBookData = new RetrieveBookData();
		RetrieveData retrieveInteractions = new RetrieveInteractions();
		Object[][] interactionsData = retrieveInteractions.read();
		Object[][] bookData = retrieveBookData.read();

		int numberOfRows = bookData.length;
		completeBookTable = new Object[numberOfRows][10];

		int bookCount = 0;
		int interactionsCount = 0;

		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < 5; j++) {
				completeBookTable[i][j] = bookData[bookCount][j];
			}
			for (int k = 2, x = 5; k < 5; k++) {
				completeBookTable[i][x] = interactionsData[interactionsCount][k];
				x++;
			}
			for (int l = 0, y = 8; l < 2; l++) {
				completeBookTable[i][y] = interactionsData[interactionsCount][l];
				y++;
			}			
			bookCount++;
			interactionsCount++;
		}
		return completeBookTable;
	}
}
