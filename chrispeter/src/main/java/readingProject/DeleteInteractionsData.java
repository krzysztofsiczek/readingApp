package readingProject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DeleteInteractionsData implements DeleteData {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	private Integer bookId;
	private Books bookToBeDeleted;
	private Integer userId;
	private Users currentUser;
	private Interactions interactionToBeDeleted;

	public DeleteInteractionsData() {
	}

	@Override
	public void delete(Integer bookId) {
		this.bookId = bookId;
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
		userId = UserInstance.getUserId();
		currentUser = (Users) session.get(Users.class, userId);
		bookToBeDeleted = (Books) session.get(Books.class, bookId);

		interactionToBeDeleted = new Interactions();
		interactionToBeDeleted.setUsers(currentUser);
		interactionToBeDeleted.setBooks(bookToBeDeleted);
		
		session.delete(interactionToBeDeleted);
	}

	private void closeSession() {
		transaction.commit();
		session.close();
	}
}
