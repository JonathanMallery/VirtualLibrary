package com.cognixia.jump.maven.libraryproject.model;

public class BookCheckout {
	private int checkoutId;
	private int patronId;
	private String isbn;
	private String checkedOut;
	private String dueDate;
	private String returned;
	
	
	public BookCheckout(int checkoutId, int patronId, String isbn, String checkedOut, String dueDate, String returned) {
		super();
		this.checkoutId = checkoutId;
		this.patronId = patronId;
		this.isbn = isbn;
		this.checkedOut = checkedOut;
		this.dueDate = dueDate;
		this.returned = returned;
	}

	public int getCheckoutId() {
		return checkoutId;
	}

	public void setCheckoutId(int checkoutId) {
		this.checkoutId = checkoutId;
	}

	public int getPatronId() {
		return patronId;
	}

	public void setPatronId(int patronId) {
		this.patronId = patronId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getCheckedOut() {
		return checkedOut;
	}

	public void setCheckedOut(String checkedOut) {
		this.checkedOut = checkedOut;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getReturned() {
		return returned;
	}

	public void setReturned(String returned) {
		this.returned = returned;
	}

	@Override
	public String toString() {
		return "BookCheckout [checkoutId=" + checkoutId + ", patronId=" + patronId + ", isbn=" + isbn + ", checkedOut="
				+ checkedOut + ", dueDate=" + dueDate + ", returned=" + returned + "]";
	}
	
	
	
}
