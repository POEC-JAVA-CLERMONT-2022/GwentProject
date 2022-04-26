package com.example.gwent_projet.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column (name = "role", length = 50, nullable = false)
	private int role;

	@Column (name = "username", length = 100, nullable = false)
	private String username;

	@Column (name = "email", nullable = false)
	private String email;

	@Column (name = "password", nullable = false)
	private String password;

	public User (int role, String username, String email, String password) {
		this.role = role;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public User() {

	}

	// overrides -----------------------------------------------------------------------------

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", email='" + email + '\'' +
				", password=" + password +
				'}';
	}

	// getters & setters ----------------------------------------------------------------------

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public int getRole() {
		return role;
	}
	
	public void setRole(int role) {
		this.role = role;
	}

	// ------------------------------------------------------
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
}