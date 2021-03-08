

<%@page import="com.cognixia.jump.maven.libraryproject.model.Book"%>
<%@page import="com.cognixia.jump.maven.libraryproject.dao.BookDao"%>
<%@page import="com.cognixia.jump.maven.libraryproject.model.BookCheckout"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<% List<BookCheckout> checkoutList = (List<BookCheckout>) request.getAttribute("checkoutList"); %>
<% BookDao bookDao = new BookDao(); %>
<% if (checkoutList != null) {%>

<div class="container">
	<table class="table  table-responsive-sm">
		<table class="table">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">ISBN</th>
					<th scope="col">Title</th>
					<th scope="col">Date Checked Out</th>
					<th scope="col">Due Date</th>
					<th scope="col">Returned</th>
					<th scope="col">Status</th>
				</tr>
			</thead>
			<tbody>
				<% int count = 1;%>

				<% for (int i = 0; i < checkoutList.size(); i++) { %>
					<% String currentIsbn = checkoutList.get(i).getIsbn(); %>
					<% Book book = bookDao.getBookByIsbn(currentIsbn); %>
					<tr>
						<th><%= count++ %></th>
						<td><%=checkoutList.get(i).getIsbn() %></td>
						<td><%=book.getTitle() %></td>
						<td><%=checkoutList.get(i).getCheckedOut() %></td>
						<td><%=checkoutList.get(i).getDueDate() %></td>
						<td>
							<%if(checkoutList.get(i).getReturned()!=null ) { %>
							<%=checkoutList.get(i).getReturned() %>
							<%} %>
						</td>
						<td>
							<% if (checkoutList.get(i).getReturned()==null) { %>
							<p style="color: red">Checked Out</p>
							<% } else { %>
							<p style="color: green;">Returned</p>
							<%} %>
						</td>
					</tr>
				<% } %>
			</tbody>
		</table>
	</table>
</div>
<% } else {%>
<p> If you are unable to see any books, try clicking on button to load books. </p>
<% } %>