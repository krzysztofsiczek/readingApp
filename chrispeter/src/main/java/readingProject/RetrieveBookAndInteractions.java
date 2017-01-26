package readingProject;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RetrieveBookAndInteractions implements RetrieveData {

	private static final int NUMBEROFCOLUMNS = 9;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	private Users currentUser;
	private Object[][] dataToBeRetrieved;

	public RetrieveBookAndInteractions() {
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

	@SuppressWarnings("unchecked")
	private void retrieveDataFromDatabase() {
		Integer userId = UserInstance.getUserId();
		String checkingUsers = "From Users U WHERE U.userId= :userId";
		Query<Users> query = session.createQuery(checkingUsers);
		query.setParameter("userId", userId);
		List<Users> result = query.getResultList();

		if (!(result.isEmpty())) {
			Iterator<Users> iterator = result.iterator();
			currentUser = iterator.next();
		}

		Set<Interactions> interactions = currentUser.getInteractions();

		int count = 0;
		dataToBeRetrieved = new Object[interactions.size()][NUMBEROFCOLUMNS];

		for (Iterator<Interactions> iterator = interactions.iterator(); iterator.hasNext();) {
			Interactions interactionsToBeRetrieved = iterator.next();
			Books book = interactionsToBeRetrieved.getBooks();
			dataToBeRetrieved[count][0] = book.getBookId();
			dataToBeRetrieved[count][1] = book.getBookTitle();
			dataToBeRetrieved[count][2] = book.getBookAuthor();
			dataToBeRetrieved[count][3] = book.getGenre();
			dataToBeRetrieved[count][4] = book.getPublicationYear();
			dataToBeRetrieved[count][5] = interactionsToBeRetrieved.getHasRead();
			dataToBeRetrieved[count][6] = interactionsToBeRetrieved.getHasGot();
			dataToBeRetrieved[count][7] = interactionsToBeRetrieved.getWantsToBuy();
			dataToBeRetrieved[count][8] = interactionsToBeRetrieved.getUsers();
			count++;
		}
	}

	private void closeSession() {
		transaction.commit();
		session.close();
	}
}
