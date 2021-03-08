package com.cognixia.jump.maven.libraryproject.model;

public class Librarian {
	private int librarianId;
	private String username;
	private String password;
	
	public Librarian(int librarianId, String username, String password) {
		super();
		this.librarianId = librarianId;
		this.username = username;
		this.password = password;
	}

	public int getLibrarianId() {
		return librarianId;
	}

	public void setLibrarianId(int librarianId) {
		this.librarianId = librarianId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Librarian [librarianId=" + librarianId + ", username=" + username + ", password=" + password + "]";
	}
	
	
}
