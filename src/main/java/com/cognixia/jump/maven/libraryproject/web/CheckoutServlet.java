package com.cognixia.jump.maven.libraryproject.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.maven.libraryproject.connection.ConnectionManager;
import com.cognixia.jump.maven.libraryproject.dao.BookCheckoutDao;
import com.cognixia.jump.maven.libraryproject.dao.BookDao;
import com.cognixia.jump.maven.libraryproject.dao.PatronDao;
import com.cognixia.jump.maven.libraryproject.model.Book;
import com.cognixia.jump.maven.libraryproject.model.BookCheckout;
import com.cognixia.jump.maven.libraryproject.model.Patron;

/**
 * Servlet implementation class CheckoutServlet
 */
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookCheckoutDao checkOutDao;
	private PatronDao patronDao;
	private BookDao bookDao;
	
	@Override
	public void init() throws ServletException {
		checkOutDao = new BookCheckoutDao();
		patronDao = new PatronDao();
	}

	public void destroy() {
		try {
			ConnectionManager.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Unable to close");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		String username = (String) request.getSession(true).getAttribute("username");
		Patron patron = patronDao.getPatronByUsername(username);
		List<BookCheckout> checkoutHistory = checkOutDao.getCheckoutsByPatronId(patron.getPatronId());
		
		request.setAttribute("allCheckouts", checkoutHistory);
		dispatcher = request.getRequestDispatcher("patron.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * TODO: Checkout or return book.
		 * 1) Set up switch statement, checking if patron is checking out a book or returning a book.
		 * 2) If checkout, call checkoutBook. If return, call returnBook
		 * 3) Use RequestDispatcher to go to patron.jsp
		 */
		
		String bookAction = request.getParameter("bookAction");
		System.out.println(bookAction);
		switch (bookAction) {
		case "checkout":
			checkoutBook(request, response);
			break;
		case "return":
			returnBook(request, response);
			break;
		default:

		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("patron.jsp");
		dispatcher.forward(request, response);
	}
	
	private void checkoutBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * TODO: Add check
		 * 1) Get book using ISBN
		 * 2) Use UpdateCheckout() method from BookCheckoutDao to update  attribute
		 * 
		 */
		
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession(true);
		Patron patron = patronDao.getPatronByUsername((String)session.getAttribute("username"));
		int patronId = patron.getPatronId();

		BookCheckout bco = new BookCheckout(1,patronId,request.getParameter("isbn"),getTodaysDateString(),getDueDateString(),"");
		System.out.println(bco);
		if (checkOutDao.addCheckout(bco)) {
			request.setAttribute("message", "Checkout book successful.");
		} else {
			
			request.setAttribute("message", "Checkout book failed.");
		}
		
	}
	
	private void returnBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * TODO: Return books
		 * 1) Get book using ISBN
		 * 2) Use UpdateCheckout() method from BookCheckoutDao to update returned attribute
		 * 
		 */
		
		String isbn = (String) request.getParameter("isbn");
		
		String username = (String) request.getSession(true).getAttribute("username");
		Patron patron = patronDao.getPatronByUsername(username);
		List<BookCheckout> checkoutHistory = checkOutDao.getCheckoutsByPatronId(patron.getPatronId());
		System.out.println("Return Book: checkoutHistory");
		BookCheckout bookToReturn = null;
		boolean isReturned = false;
		for (int i = 0; i < checkoutHistory.size(); i++) {

			
			if (checkoutHistory.get(i).getIsbn().equals(isbn)) {
				
				bookToReturn = checkoutHistory.get(i);
				isReturned = true;
				break;
			}
		}
		
		if (isReturned) {
			
			BookCheckout newCheckout = new BookCheckout(
					bookToReturn.getCheckoutId(),
					bookToReturn.getPatronId(),
					bookToReturn.getIsbn(), 
					bookToReturn.getCheckedOut(), 
					getDueDateString(),
					getTodaysDateString()
					);
			
			if (checkOutDao.updateCheckout(newCheckout)) {
				request.setAttribute("message", "Book returned successfully.");				
			} else {
				request.setAttribute("message", "Book Return Failed.");
			}
			
		} else {
			request.setAttribute("message", "Book Return Failed.");
		}
		
	}
		
		private String getTodaysDateString() {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			String date = dtf.format(now).toString();  

			return date;
		}	
		
		private String getDueDateString() {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			String date = dtf.format(now.plusMonths(1)).toString();  
			System.out.println(date);
			return date;
		}	

}
