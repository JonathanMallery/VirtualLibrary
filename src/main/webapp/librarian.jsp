<%@ include file="header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="librarian">
	<h2>Librarian Dashboard</h2>
	<hr>
	
	<section id="message">
	  <% String message = (String) request.getAttribute("message"); %>
	  <% if (message != null) {%>
	  <p style="padding: 1% 10%; color: #72c2d6"><%=message%></p>
	  <% } %>
	</section>
	
	<section id="add-book">
	  <form action="BookServlet" method="POST"">
	    <fieldset>
	      <legend>
	        <h4>Add Book</h4>
	      </legend>
	
	      <%@ include file="book-form.jsp"%>
	
	      <input type="hidden" name="bookAction" value="add">

	      <button type="submit" class="btn btn-primary">Add New Book</button>

	    </fieldset>
	  </form>
	</section>
	<hr>
	
	<section id="update-book">
	  <form action="BookServlet" method="POST"">
	    <fieldset>
	      <legend>
	        <h4>Update Book</h4>
	      </legend>
	
	      <%@ include file="book-form.jsp"%>
	
	      <input type="hidden" name="bookAction" value="update">

		      <button type="submit" class="btn btn-primary">Update Book</button>

	    </fieldset>
	  </form>
	</section>
	<hr>
	<section id="view-books">
	
	  <form action="BookServlet" method="GET"">
	    <fieldset>
	      <legend>
	        <h4>Book Collection</h4>
	      </legend>
	
	
	      <input type="hidden" name="userType" value="librarian">
			
			<% request.setAttribute("bookList", request.getAttribute("allBooks")); %>
			  <%@ include file="book-list.jsp" %>
		      <button type="submit" class="btn btn-primary" name="userType" value="librarian">Load Books</button>

	    </fieldset>
	  </form>
	
	  </fieldset>
	
	</section>
	<hr>
</div>

<%@ include file="footer.jsp"%>
