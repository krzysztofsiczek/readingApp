package readingProject;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class RetrieveBookData implements RetrieveData {

	private static final int NUMBEROFCOLUMNS = 4;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	private Object[][] dataToBeRetrieved;

	public RetrieveBookData() {
	}

	@Override
	public Object[][] read() {
		getSessionFactoryInstance();
		startSession();
		retrieveDataFromDatabase();
		closeSession();
		return dataToBeRetrieved;
	}

	private void getSessionFactoryInstance() {
		sessionFactory = SessionFactoryInstance.getSessionFactoryInstance();
	}

	private void startSession() {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	private void retrieveDataFromDatabase() {
		String retrievingBookData = "From Books B";
		List<Object> results = session.createQuery(retrievingBookData).getResultList();

		int count = 0;
		dataToBeRetrieved = new Object[results.size()][NUMBEROFCOLUMNS];

		for (Iterator<Object> iterator = results.iterator(); iterator.hasNext();) {
			Books book = (Books) iterator.next();
			dataToBeRetrieved[count][0] = book.getBookTitle();
			dataToBeRetrieved[count][1] = book.getBookAuthor();
			dataToBeRetrieved[count][2] = book.getGenre();
			dataToBeRetrieved[count][3] = book.getPublicationYear();
			count++;
		}
	}

	private void closeSession() {
		transaction.commit();
		session.close();
	}
}
