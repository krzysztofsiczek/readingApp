package readingProject;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class StoreData {

	private Book bookToBeAdded;
	private User userToBeAdded;
	// private Interaction interactionToBeAdded;
	private Configuration configuration;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	private boolean doesUserExist;
	private String emailToBeAdded;
	private String userNameToBeCompared;
	private String passwordToBeCompared;
	private boolean isLoginDataCorrect;

	public StoreData(String emailToBeAdded) {
		this.emailToBeAdded = emailToBeAdded;
	}

	public StoreData(String userNameToBeCompared, String passwordToBeCompared) {
		this.userNameToBeCompared = userNameToBeCompared;
		this.passwordToBeCompared = passwordToBeCompared;
	}

	public StoreData(Book bookToBeAdded) {
		this.bookToBeAdded = bookToBeAdded;
	}

	public StoreData(User userToBeAdded) {
		this.userToBeAdded = userToBeAdded;
	}

	public void storeData() {

		setUpConfiguration();

		if (!(userToBeAdded == null)) {
			Users users = new Users();
			users.setUser_name(userToBeAdded.getUserName());
			users.setEmail(userToBeAdded.getEmail());
			users.setPassword(userToBeAdded.getPassword());
			session.persist(users);
			System.out.println("uzytkownik dodany.");
		}

		if (!(bookToBeAdded == null)) {
			Books_collection books = new Books_collection();
			books.setBook_title(bookToBeAdded.getBookTitle());
			books.setBook_author(bookToBeAdded.getBookAuthor());
			books.setIsbn(bookToBeAdded.getIsbn());
			books.setGenre(bookToBeAdded.getGenre());
			books.setPublication_year(bookToBeAdded.getPublicationYear());
			session.persist(books);

			System.out.println("ksiazka dodana.");

			/*
			 * Users users = new Users();
			 * 
			 * users.setUser_name("Marian Ryszard");
			 * users.setEmail("ryszard@op.pl"); users.setPassword("password");
			 * 
			 * Interactions interactions = new Interactions();
			 * interactions.setUsers(users);
			 * interactions.setBooks_collection(books);
			 * interactions.setHasGot(true);
			 */

			/*
			 * session.persist(users); session.persist(interactions);
			 */

		}

		transaction.commit();
		session.close();

		System.out.println("successfully saved.");

	}

	public boolean checkWhetherUserExists() {
		doesUserExist = false;

		setUpConfiguration();

		String checkingWhetherUserExists = "FROM Users WHERE email= :email";
		Query<User> query = session.createQuery(checkingWhetherUserExists);
		query.setParameter("email", emailToBeAdded);
		List<User> result = query.getResultList();
		boolean wynik = result.isEmpty();
		if (wynik != true) {
			doesUserExist = true;
		}
		return doesUserExist;
	}

	public boolean checkLoginData() {
		isLoginDataCorrect = false;

		setUpConfiguration();

		String checkingLoginData = "FROM Users WHERE user_name= :user_name AND password= :password";
		Query<User> query = session.createQuery(checkingLoginData);
		query.setParameter("user_name", userNameToBeCompared);
		query.setParameter("password", passwordToBeCompared);
		List<User> result = query.getResultList();
		boolean wynik = result.isEmpty();
		if (wynik != true) {
			isLoginDataCorrect = true;
		}
		return isLoginDataCorrect;
	}

	private void setUpConfiguration() {
		configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		sessionFactory = configuration.buildSessionFactory();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
}
