package readingProject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StoreUserData implements StoreData {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	private Users userToBeAdded;
	private Integer userId;

	public StoreUserData(Users userToBeAdded) {
		this.userToBeAdded = userToBeAdded;
	}

	@Override
	public Integer save() {
		getSessionFactoryInstance();
		startSession();
		insertDataIntoDatabase();
		closeSession();
		return userId;
	}

	private void getSessionFactoryInstance() {
		sessionFactory = SessionFactoryInstance.getSessionFactoryInstance();
	}

	private void startSession() {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	private void insertDataIntoDatabase() {
		userId = (Integer) session.save(userToBeAdded);
	}

	private void closeSession() {
		transaction.commit();
		session.close();
	}
}
