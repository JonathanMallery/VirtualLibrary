package com.cognixia.jump.maven.libraryproject.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.maven.libraryproject.connection.ConnectionManager;
import com.cognixia.jump.maven.libraryproject.dao.PatronDao;
import com.cognixia.jump.maven.libraryproject.model.Patron;

/**
 * Servlet implementation class PatronDAOServlet
 */
public class PatronServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PatronDao patronDAO;

	public void init(ServletConfig config) throws ServletException {
		patronDAO = new PatronDao();
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

		String choice = request.getServletPath();
		switch(choice) {
		
		case "/list"://SELECT * query
			listPatrons(request,response);
			break;
			
		case "/insert"://INSERT OR ADD patron
			addNewPatron(request,response);
			break;
		case "/edit":
			goToEditPatronForm(request,response);
			break;
			
			default:
			 response.sendRedirect("/");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
    private void listPatrons(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//calls the patronDAO get all patrons method
    	List<Patron> allPatrons = patronDAO.getAllPatrons();
    	System.out.println("The list of patrons: " + allPatrons);
        request.setAttribute("allPatrons", allPatrons);
        RequestDispatcher dispatcher = request.getRequestDispatcher("patron-list.jsp");
        dispatcher.forward(request, response);
    }
    private void addNewPatron(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	 
    	String firstName =  request.getParameter("firstName");
    	String lastName = request.getParameter("lastName");
    	String userName = request.getParameter("userName");
    	String passWord = request.getParameter("passWord");
    	int patronId = Integer.parseInt(request.getParameter("patronId"));
    	
    	Patron patron = new Patron(0, firstName, lastName, userName, passWord, patronId);
    	
    	patronDAO.addPatron(patron);
    	
    	response.sendRedirect("list");
    	
    }
    private void goToEditPatronForm (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	
    	int patronId = Integer.parseInt(request.getParameter("patronId"));
    	
    	Patron patron = patronDAO.getPatronById(patronId);
    	
    	request.setAttribute("patron", patron);
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("patron-form.jsp");
    	
    	dispatcher.forward(request, response);
    	
    }
}