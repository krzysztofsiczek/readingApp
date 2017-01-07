package readingProject;

import java.io.Serializable;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Books_Users_Id implements Serializable {

	private Books_collection books;
	private Users users;

	public Books_Users_Id() {
	}

	public Books_collection getBooks() {
		return books;
	}

	public void setBooks(Books_collection books) {
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
		if (!(o instanceof Books_Users_Id)) {
			return false;
		}

		Books_Users_Id booksUsersId = (Books_Users_Id) o;

		return booksUsersId.books.equals(books) && booksUsersId.users.equals(users);
	}

	public int hashCode() {
		return new HashCodeBuilder().append(books).append(users).hashCode();
	}

}