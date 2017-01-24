package readingProject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DeleteBookData implements DeleteData {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	private Integer bookId;
	private Books bookToBeDeleted;

	public DeleteBookData(Integer bookId) {
		this.bookId = bookId;
	}

	@Override
	public void delete(Integer bookId) {
		getSessionFactoryInstance();
		startSession();
		deleteDataFromDatabase();
		closeSession();
	}

	private void getSessionFactoryInstance() {
		sessionFactory = SessionFactoryInstance.getSessionFactoryInstance();
	}

	private void startSession() {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	private void deleteDataFromDatabase() {
		bookToBeDeleted = (Books) session.get(Books.class, bookId);
		session.delete(bookToBeDeleted);
	}

	private void closeSession() {
		transaction.commit();
		session.close();
	}
}
