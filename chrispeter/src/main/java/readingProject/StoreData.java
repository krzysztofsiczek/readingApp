package readingProject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StoreData {

	private Book bookToBeAdded;
	private Configuration config;
	private SessionFactory factory;
	private Session session;
	private Transaction t;
	
	public StoreData(Book bookToBeAdded){
		this.bookToBeAdded = bookToBeAdded;
		storeData();
	}

	public void storeData() {

		setUpConfiguration();

		Books_collection books = new Books_collection();
		books.setBook_title(bookToBeAdded.getBookTitle());
		books.setBook_author(bookToBeAdded.getBookAuthor());
		books.setIsbn(bookToBeAdded.getIsbn());
		books.setGenre(bookToBeAdded.getGenre());
		books.setPublication_year(bookToBeAdded.getPublicationYear());

/*		Users users = new Users();

		users.setUser_name("Marian Ryszard");
		users.setEmail("ryszard@op.pl");
		users.setPassword("password");

		Interactions interactions = new Interactions();
		interactions.setUsers(users);
		interactions.setBooks_collection(books);
		interactions.setHasGot(true);*/

		session.persist(books);
/*		session.persist(users);
		session.persist(interactions);*/

		t.commit();
		session.close();

		System.out.println("successfully saved");

	}

	private void setUpConfiguration() {
		config = new Configuration();
		config.configure("hibernate.cfg.xml");
		factory = config.buildSessionFactory();
		session = factory.openSession();
		t = session.beginTransaction();
	}
}
