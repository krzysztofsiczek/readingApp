package readingProject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StoreUserData implements StoreData {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	private Users users;
	private User userToBeAdded;

	public StoreUserData(User user) {
		this.userToBeAdded = user;
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
		users = new Users();
		users.setUserName(userToBeAdded.getUserName());
		users.setEmail(userToBeAdded.getEmail());
		users.setPassword(userToBeAdded.getPassword());
		session.persist(users);
		System.out.println("uzytkownik dodany.");
	}

	private void closeSession() {
		transaction.commit();
		session.close();
	}
}
