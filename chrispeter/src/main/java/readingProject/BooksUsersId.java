package readingProject;

import java.io.Serializable;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class BooksUsersId implements Serializable {

	private static final long serialVersionUID = -1888914384383362260L;
	private Books books;
	private Users users;

	public BooksUsersId() {
	}

	public Books getBooks() {
		return books;
	}

	public void setBooks(Books books) {
		this.books = books;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public boolean equals(Object o) {

		if (o == this)
			return true;
		if (!(o instanceof BooksUsersId)) {
			return false;
		}

		BooksUsersId booksUsersId = (BooksUsersId) o;

		return booksUsersId.books.equals(books) && booksUsersId.users.equals(users);
	}

	public int hashCode() {
		return new HashCodeBuilder().append(books).append(users).hashCode();
	}

}