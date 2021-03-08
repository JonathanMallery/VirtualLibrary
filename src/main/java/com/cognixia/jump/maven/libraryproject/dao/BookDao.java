package com.cognixia.jump.maven.libraryproject.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cognixia.jump.maven.libraryproject.connection.ConnectionManager;
import com.cognixia.jump.maven.libraryproject.model.Book;

public class BookDao {
	
	public static final Connection conn = ConnectionManager.getConnection();
	
	private static final String SELECT_ALL_BOOKS = "select * from book";
	private static final String SELECT_BOOK_BY_ISBN = "select * from book where isbn = ?";
	private static final String SELECT_BOOK_RENTED = "select * from book where rented = ?";
	private static final String SELECT_BOOK_AVAILABLE = "select * from book where rented = ?";
	private static final String INSERT_BOOK = "insert into book(isbn, title, descr, rented, added_to_library) values(?, ?, ?, ?, ?)";
	private static final String UPDATE_BOOK = "update book set title = ?, descr = ? where isbn = ?";

	
public List<Book> getAllBooks() {
		
	List<Book> allBooks = new ArrayList<Book>();
	
	try(PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_BOOKS);
			ResultSet rs = stmt.executeQuery() ) {
		
		while(rs.next()) {
			
			String isbn = rs.getString("isbn");
			String title = rs.getString("title");
			String descr = rs.getString("descr");
			boolean rented = rs.getBoolean("rented");
			String addedToLibrary = rs.getString("added_to_library");
			
			allBooks.add(new Book(isbn, title, descr, rented, addedToLibrary));	
		}
	} catch(SQLException e) {
		e.printStackTrace();
	}
	return allBooks;
}


public Book getBookByIsbn(String isbn) {
		
	Book book = null;
	
	try(PreparedStatement stmt = conn.prepareStatement(SELECT_BOOK_BY_ISBN)) {
		
		stmt.setString(1, isbn);
		
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			String title = rs.getString("title");
			String descr = rs.getString("descr");
			boolean rented = rs.getBoolean("rented");
			String addedToLibrary = rs.getString("added_to_library");
			
			book = new Book(isbn, title, descr, rented, addedToLibrary);
		}
		
	} catch(SQLException e) {
		e.printStackTrace();
	}
	return book;
}


//@SuppressWarnings("hiding")
public ArrayList<Book> getAllRented(boolean rented) {
	
	ArrayList<Book> rentedBooks = new ArrayList<Book>();
	Book book = null;

	try(PreparedStatement stmt = conn.prepareStatement(SELECT_BOOK_RENTED)) {
		
		stmt.setBoolean(1, true);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			
			String isbn = rs.getString("isbn");
			String title = rs.getString("title");
			String descr = rs.getString("descr");
			String addedToLibrary = rs.getString("added_to_library");
			
			rentedBooks.add(new Book(isbn, title, descr, rented, addedToLibrary));	
		}
		
	} catch(SQLException e) {
		e.printStackTrace();
	}
	return rentedBooks;
}

public ArrayList<Book> getAllAvailable(boolean rented) {
	ArrayList<Book> availableBooks = new ArrayList<Book>();
	Book book = null;
	
	try(PreparedStatement stmt = conn.prepareStatement(SELECT_BOOK_AVAILABLE)) {
		
		stmt.setBoolean(1, false);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			
			String isbn = rs.getString("isbn");
			String title = rs.getString("title");
			String descr = rs.getString("descr");
			String addedToLibrary = rs.getString("added_to_library");
			
			availableBooks.add(new Book(isbn, title, descr, rented, addedToLibrary));	
		}
	} catch(SQLException e) {
		e.printStackTrace();
	}
	return availableBooks;
}

public boolean addBook(Book book) {
	
	try(PreparedStatement stmt = conn.prepareStatement(INSERT_BOOK)) {
		
		stmt.setString(1, book.getIsbn());
		stmt.setString(2, book.getTitle());
		stmt.setString(3, book.getDescr());
		stmt.setBoolean(4, book.isRented());
		stmt.setString(5, book.getAddedToLibrary());
		
		if(stmt.executeUpdate() > 0) {
			return true;
		}
	} catch(SQLException e) {
		e.printStackTrace();
	}	
	return false;
}

public boolean updateBook(Book book) {
	
	try (PreparedStatement stmt = conn.prepareStatement(UPDATE_BOOK)) {

		stmt.setString(1, book.getTitle());
		stmt.setString(2, book.getDescr());
		stmt.setString(3, book.getIsbn());

		if (stmt.executeUpdate() > 0) {
			return true;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
}
}
