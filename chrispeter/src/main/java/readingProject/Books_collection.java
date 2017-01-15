package readingProject;

import java.util.HashSet;
import java.util.Set;

public class Books_collection {

	private int book_id, publication_year;
	private double isbn;
	private String book_title, book_author, genre;
	private Set<Interactions> interactions = new HashSet<Interactions>();

	public Books_collection() {
	};

	public Books_collection(int book_id, String book_title, String book_author, double isbn, String genre,
			int publication_year, String language, Set<Interactions> interactions) {
		this.book_id = book_id;
		this.book_title = book_title;
		this.book_author = book_author;
		this.isbn = isbn;
		this.genre = genre;
		this.publication_year = publication_year;
		this.interactions = interactions;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getBook_title() {
		return book_title;
	}

	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public double getIsbn() {
		return isbn;
	}

	public void setIsbn(double isbn) {
		this.isbn = isbn;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getPublication_year() {
		return publication_year;
	}

	public void setPublication_year(int publication_year) {
		this.publication_year = publication_year;
	}

	public Set<Interactions> getInteractions() {
		return interactions;
	}

	public void setInteractions(Set<Interactions> interactions) {
		this.interactions = interactions;
	}
}