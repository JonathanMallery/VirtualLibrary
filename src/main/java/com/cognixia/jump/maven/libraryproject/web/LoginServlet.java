package com.cognixia.jump.maven.libraryproject.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.maven.libraryproject.dao.LibrarianDao;
import com.cognixia.jump.maven.libraryproject.dao.PatronDao;
import com.cognixia.jump.maven.libraryproject.model.Librarian;
import com.cognixia.jump.maven.libraryproject.model.Patron;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PatronDao patronDao;
	LibrarianDao librarianDao;
	
	@Override
	public void init() throws ServletException {
		patronDao = new PatronDao();
		librarianDao = new LibrarianDao();
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		String radioButton = request.getParameter("user");
		System.out.println(radioButton);
		
		boolean isValid=false;
		
		switch(radioButton) {
		case "librarian":
			if ((isValid = verifyLibrarianLogin(request, response))) {
				dispatcher=request.getRequestDispatcher("librarian.jsp");
			}			
			break;
		case "patron":
			if ((isValid = verifyPatronLogin(request, response))) {
				dispatcher=request.getRequestDispatcher("patron.jsp");
			}
			break;
		default:	
		}
		if (!isValid) {
			request.setAttribute("isValid", "Login Failed. Please try again.");
			dispatcher=request.getRequestDispatcher("/");
		} 
		
		dispatcher.forward(request, response);
	}
	
	private boolean verifyLibrarianLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Librarian librarian = librarianDao.getByUsername(request.getParameter("uname"));
		
		if(librarian.getUsername().equals(request.getParameter("uname")) && 
				(librarian.getPassword().contentEquals(request.getParameter("psw")))) {
			
			session.setAttribute("username", request.getParameter("uname"));
			session.setAttribute("password", request.getParameter("psw"));
			
			return true;
		} else {	
		return false;
	}
}
	
	private boolean verifyPatronLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(true);
		Patron patron = patronDao.getPatronByUsername(request.getParameter("uname"));
		
		if(patron.getUserName().equals(request.getParameter("uname")) && 
				(patron.getPassword().contentEquals(request.getParameter("psw")))) {
			session.setAttribute("username", request.getParameter("uname"));
			session.setAttribute("password", request.getParameter("psw"));
			return true;
		} else {	
		return false;
	}
}
}
