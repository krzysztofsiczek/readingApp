package readingProject;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class StoreInteractionsData implements StoreData {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	private Interactions interactions;
	private Interaction interactionToBeAdded;

	public StoreInteractionsData(Interactions interactions) {
		this.interactions = interactions;
	}

	@Override
	public void save() {
		getSessionFactoryInstance();
		startSession();
		insertDataIntoDatabase();
		closeSession();
	}

	private void getSessionFactoryInstance() {
		sessionFactory = SessionFactoryInstance.getSessionFactoryInstance();
	}

	private void startSession() {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	private void insertDataIntoDatabase() {
//		interactions.setUsers(users);
//		interactions.setBooks_collection(books);
		interactions.setHasGot(true);
		interactions.setHasRead(true);
		interactions.setWantsToBuy(true);
		session.persist(interactions);
	}

/*	private void retrieveBookId() {
		String bookTitleToBeCompared = bookToBeAdded.getBookTitle();
		String checkingBookId = "SELECT B.bookId From Books B WHERE B.bookTitle= :bookTitle";
		Query<Integer> query = session.createQuery(checkingBookId);
		query.setParameter("bookTitle", bookTitleToBeCompared);
		List<Integer> result = query.getResultList();
		if (!(result.isEmpty())) {
			if (result.size() == 1){
				bookId = result.get(0);
			} else {
				bookId = result.get(result.size() - 1);
			}
		}
	}*/

	private void closeSession() {
		transaction.commit();
		session.close();
	}
}
