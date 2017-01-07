package readingProject;

import java.util.HashSet;
import java.util.Set;

public class Books_collection {

	private int book_id, publication_year;
	double isbn;
	private String book_title, book_author, genre, language;
	private Set<Interactions> interactions = new HashSet<Interactions>();

	protected Books_collection() {
	};

	protected Books_collection(int book_id, String book_title, String book_author, double isbn, String genre,
			int publication_year, String language, Set<Interactions> interactions) {
		this.book_id = book_id;
		this.book_title = book_title;
		this.book_author = book_author;
		this.isbn = isbn;
		this.genre = genre;
		this.publication_year = publication_year;
		this.language = language;
		this.interactions = interactions;
	}

	protected int getBook_id() {
		return book_id;
	}

	protected void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	protected String getBook_title() {
		return book_title;
	}

	protected void setBook_title(String book_title) {
		this.book_title = book_title;
	}

	protected String getBook_author() {
		return book_author;
	}

	protected void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	protected double getIsbn() {
		return isbn;
	}

	protected void setIsbn(double isbn) {
		this.isbn = isbn;
	}

	protected String getGenre() {
		return genre;
	}

	protected void setGenre(String genre) {
		this.genre = genre;
	}

	protected int getPublication_year() {
		return publication_year;
	}

	protected void setPublication_year(int publication_year) {
		this.publication_year = publication_year;
	}

	protected String getLanguage() {
		return language;
	}

	protected void setLanguage(String language) {
		this.language = language;
	}

	protected Set<Interactions> getInteractions() {
		return interactions;
	}

	protected void setInteractions(Set<Interactions> interactions) {
		this.interactions = interactions;
	}
}