package readingProject;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Users {

	private int user_id;
	private String user_name, email, password;
	LocalDate user_since;

	private Set<Interactions> interactions = new HashSet<Interactions>();

	protected Users() {
	};

	protected Users(int user_id, String user_name, String email, String password, LocalDate user_since, Set<Interactions> interactions) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.email = email;
		this.password = password;
		this.user_since = user_since;
		this.interactions = interactions;
	}

	protected int getUser_id() {
		return user_id;
	}

	protected void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	protected String getUser_name() {
		return user_name;
	}

	protected void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	protected String getEmail() {
		return email;
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	protected String getPassword() {
		return password;
	}

	protected void setPassword(String password) {
		this.password = password;
	}

	protected LocalDate getUser_since() {
		return user_since;
	}

	protected void setUser_since(LocalDate user_since) {
		this.user_since = user_since;
	}

	protected Set<Interactions> getInteractions() {
		return interactions;
	}

	protected void setInteractions(Set<Interactions> interactions) {
		this.interactions = interactions;
	}
}