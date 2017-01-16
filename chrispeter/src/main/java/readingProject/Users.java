package readingProject;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Users {

	private int user_id;
	private String user_name, email, password;
	private LocalDateTime user_since;

	private Set<Interactions> interactions = new HashSet<Interactions>();

	public Users() {
	};

	public Users(int user_id, String user_name, String email, String password, LocalDateTime user_since, Set<Interactions> interactions) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.email = email;
		this.password = password;
		this.user_since = user_since;
		this.interactions = interactions;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getUser_since() {
		return user_since;
	}

	public void setUser_since(LocalDateTime user_since) {
		this.user_since = user_since;
	}

	public Set<Interactions> getInteractions() {
		return interactions;
	}

	public void setInteractions(Set<Interactions> interactions) {
		this.interactions = interactions;
	}
}