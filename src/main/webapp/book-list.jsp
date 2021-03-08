

<%@page import="com.cognixia.jump.maven.libraryproject.model.Book"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<% List<Book> allBooks = (List<Book>) request.getAttribute("bookList"); %>

<% if (allBooks != null) {%>

<div class="container">
	<table class="table  table-responsive-sm">
		<table class="table">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">ISBN</th>
					<th scope="col">Title</th>
					<th scope="col">Description</th>
					<th scope="col">Dated Added To Library</th>
				</tr>
			</thead>
			<tbody>
				<% int count = 1;%>

				<% for (int i = 0; i < allBooks.size(); i++) { %>
					<tr>
						<th><%= count++ %></th>
						<td><%=allBooks.get(i).getIsbn() %></td>
						<td><%=allBooks.get(i).getTitle() %></td>
						<td><%=allBooks.get(i).getDescr() %></td>
						<td><%=allBooks.get(i).getAddedToLibrary() %></td>
					</tr>
				<% } %>
			</tbody>
		</table>
	</table>
</div>
<% } else {%>
<p> If you are unable to see any books, try clicking on button to load books. </p>
<% } %>