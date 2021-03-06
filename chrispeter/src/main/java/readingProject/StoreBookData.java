package readingProject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StoreBookData implements StoreData {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	private Books bookToBeAdded;
	private Integer bookId;

	public StoreBookData(Books bookToBeAdded) {
		this.bookToBeAdded = bookToBeAdded;
	}

	@Override
	public Integer save() {
		getSessionFactoryInstance();
		startSession();
		insertDataIntoDatabase();
		closeSession();
		return bookId;
	}

	private void getSessionFactoryInstance() {
		sessionFactory = SessionFactoryInstance.getSessionFactoryInstance();
	}

	private void startSession() {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	private void insertDataIntoDatabase() {
		bookId = (Integer) session.save(bookToBeAdded);
	}

	private void closeSession() {
		transaction.commit();
		session.close();
	}
}
