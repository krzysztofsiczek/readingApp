package readingProject;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RetrieveInteractions implements RetrieveData {

	private static final int NUMBEROFCOLUMNS = 5;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	private Users currentUser;
	private Object[][] dataToBeRetrieved;

	public RetrieveInteractions() {
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
			dataToBeRetrieved[count][0] = interactionsToBeRetrieved.getBooks();
			dataToBeRetrieved[count][1] = interactionsToBeRetrieved.getUsers();
			dataToBeRetrieved[count][2] = interactionsToBeRetrieved.getHasGot();
			dataToBeRetrieved[count][3] = interactionsToBeRetrieved.getHasRead();
			dataToBeRetrieved[count][4] = interactionsToBeRetrieved.getWantsToBuy();
			count++;
		}
	}

	private void closeSession() {
		transaction.commit();
		session.close();
	}
}
