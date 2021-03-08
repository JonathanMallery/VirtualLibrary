package com.cognixia.jump.maven.libraryproject.model;


public class Book {
	
	private String isbn;
	private String title;
	private String descr;
	private boolean rented;
	private String addedToLibrary;
	
	public Book(String isbn, String title, String descr, boolean rented, String addedToLibrary) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.descr = descr;
		this.rented = rented;
		this.addedToLibrary = addedToLibrary;
	}

	public Book(String title, String descr, boolean rented, String addedToLibrary) {
		super();
		this.title = title;
		this.descr = descr;
		this.rented = rented;
		this.addedToLibrary = addedToLibrary;
	}



	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public String getAddedToLibrary() {
		return addedToLibrary;
	}

	public void setAddedToLibrary(String addedToLibrary) {
		this.addedToLibrary = addedToLibrary;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", descr=" + descr + ", rented=" + rented
				+ ", addedToLibrary=" + addedToLibrary + "]";
	}
}
