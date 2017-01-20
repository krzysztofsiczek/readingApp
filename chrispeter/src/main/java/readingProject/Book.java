package readingProject;

public class Book {

	private Integer bookId;
	private int publicationYear;
	private Double isbn;
	private String bookTitle, bookAuthor, genre;

	public Book() {
	};

	public Book(String bookTitle, String bookAuthor, Double isbn, String genre, int publicationYear) {
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.isbn = isbn;
		this.genre = genre;
		this.publicationYear = publicationYear;
	}

	public Book(Integer bookId, String bookTitle, String bookAuthor, Double isbn, String genre, int publicationYear) {
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.isbn = isbn;
		this.genre = genre;
		this.publicationYear = publicationYear;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public Double getIsbn() {
		return isbn;
	}

	public void setIsbn(Double isbn) {
		this.isbn = isbn;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
}
