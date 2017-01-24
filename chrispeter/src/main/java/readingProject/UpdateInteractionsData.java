package readingProject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UpdateInteractionsData implements UpdateData {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	private Interactions interactionToBeUpdated;
	private Books books;
	private Users users;
	private boolean newHasRead, newHasGot, newWantsToBuy;

	public UpdateInteractionsData(Books books, Users users, boolean newHasGot, boolean newHasRead,
			boolean newWantsToBuy) {
		this.books = books;
		this.users = users;
		this.newHasGot = newHasGot;
		this.newHasRead = newHasRead;
		this.newWantsToBuy = newWantsToBuy;
	}

	public void updateData() {
		getSessionFactoryInstance();
		startSession();
		updateDataInDatabase();
		closeSession();
	}

	private void getSessionFactoryInstance() {
		sessionFactory = SessionFactoryInstance.getSessionFactoryInstance();
	}

	private void startSession() {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	private void updateDataInDatabase() {
		BooksUsersId bookUserId = new BooksUsersId();
		bookUserId.setBooks(books);
		bookUserId.setUsers(users);
		interactionToBeUpdated = (Interactions) session.get(Interactions.class, bookUserId);
		interactionToBeUpdated.setHasGot(newHasGot);
		interactionToBeUpdated.setHasRead(newHasRead);
		interactionToBeUpdated.setWantsToBuy(newWantsToBuy);
		session.update(interactionToBeUpdated);
	}

	private void closeSession() {
		transaction.commit();
		session.close();
	}
}
