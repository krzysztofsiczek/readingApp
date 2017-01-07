package readingProject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class StoreData {

	public static void main(String[] args) {

		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");

		SessionFactory factory = config.buildSessionFactory();

		Session session = factory.openSession();

		Transaction t = session.beginTransaction();

		Books_collection books = new Books_collection();
		books.setBook_title("Przykladowa ksiazka");
		books.setBook_author("Przykładowy autor");
		books.setIsbn(836473893);
		books.setGenre("Powieść");
		books.setPublication_year(2000);

		Users users = new Users();

		users.setUser_name("Marian Ryszard");
		users.setEmail("ryszard@op.pl");
		users.setPassword("password");

		Interactions interactions = new Interactions();
		interactions.setUsers(users);
		interactions.setBooks_collection(books);
		interactions.setHasGot(true);

		session.persist(books);
		session.persist(users);
		session.persist(interactions);
		
		t.commit();
		session.close();

		System.out.println("successfully saved");

	}
}
