package com.cognixia.jump.maven.libraryproject.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cognixia.jump.maven.libraryproject.dao.BookDao;
import com.cognixia.jump.maven.libraryproject.model.Book;

/**
 * Servlet implementation class BookServlet
 */
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BookDao bookDao;
    
    @Override
    public void init() throws ServletException {
    	bookDao = new BookDao();
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Book> allBooks = bookDao.getAllBooks();
		request.setAttribute("allBooks", allBooks);
		
		String bookAction = request.getParameter("userType");
		System.out.println(bookAction);
		RequestDispatcher dispatcher = null;
		switch (bookAction) {
		case "librarian":
			dispatcher = request.getRequestDispatcher("librarian.jsp");
			break;
		case "patron":
			dispatcher = request.getRequestDispatcher("patron.jsp");
			break;
		default:
			request.setAttribute("isValid", "Sorry, something went wrong.");
			dispatcher = request.getRequestDispatcher("/");
		}
		
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bookAction = request.getParameter("bookAction");
		
		switch (bookAction) {
		case "add":
			addBooks(request, response);
			break;
		case "update":
			updateBooks(request, response);
			break;
		default:
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("librarian.jsp");
		dispatcher.forward(request, response);
	}
	
	private void addBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		boolean rented = false;
		String addedToLibrary = getTodaysDateString();
		
		Book book = new Book(isbn, title, description, rented, addedToLibrary); 
		
		if (bookDao.addBook(book)) {
			request.setAttribute("message", "Book added successsfully.");
			System.out.println("Book Add Success");
		} else {
			request.setAttribute("message", "Error: Adding book failed.");
			System.out.println("Book Add Failed");
		}
	}
	
	private void updateBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String isbn = request.getParameter("isbn");
		
		Book book = bookDao.getBookByIsbn(isbn);
		if (book != null) {
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			boolean rented = book.isRented();
			String addedToLibrary = book.getAddedToLibrary();
			
			
			Book updatedBook = new Book(isbn, title, description, rented, addedToLibrary);
			
			if(bookDao.updateBook(updatedBook)) {
				request.setAttribute("message", "Book updated successsfully.");
				System.out.println("Book Update Success");
			} else {
				request.setAttribute("message", "Error: Updating book failed.");
				System.out.println("Book Update Failed");
			}
			
		} else {
			request.setAttribute("message", "Error: Book with ISBN " + isbn + " does not exist.");
			System.out.println("Book Update Failed");
		}
		
	}

	private String getTodaysDateString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String date = dtf.format(now).toString();  

		return date;
	}
}
