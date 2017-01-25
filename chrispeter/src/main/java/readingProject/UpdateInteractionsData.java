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
	private Integer bookId;
	private boolean newHasRead, newHasGot, newWantsToBuy;

	public UpdateInteractionsData(Integer bookId, Users users, boolean newHasRead, boolean newHasGot,
			boolean newWantsToBuy) {
		this.bookId = bookId;
		this.users = users;
		this.newHasRead = newHasRead;
		this.newHasGot = newHasGot;
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
		books = (Books) session.get(Books.class, bookId);
		BooksUsersId bookUserId = new BooksUsersId();
		bookUserId.setBooks(books);
		bookUserId.setUsers(users);
		interactionToBeUpdated = (Interactions) session.get(Interactions.class, bookUserId);
		interactionToBeUpdated.setHasRead(newHasRead);
		interactionToBeUpdated.setHasGot(newHasGot);
		interactionToBeUpdated.setWantsToBuy(newWantsToBuy);
		session.update(interactionToBeUpdated);
	}

	private void closeSession() {
		transaction.commit();
		session.close();
	}
}
