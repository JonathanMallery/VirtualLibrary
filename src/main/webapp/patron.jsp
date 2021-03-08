
​<%@page import="com.cognixia.jump.maven.libraryproject.model.Patron"%>
<%@ include file= "header.jsp" %>

<div class="patron">
	<section id="message">
	  <% String message = (String) request.getAttribute("message"); %>
	  <% if (message != null) {%>
	  <p style="padding: 1% 10%; color: #72c2d6"><%=message%></p>
	  <% } %>
	</section>

	<section id="patron-book-forms">
		<form action="CheckoutServlet" method="POST">
		    <fieldset>
		      <legend>
		        <h4>Checkout Book</h4>
		      </legend>
		
		        <div class="form-group">
				    <label for="isbn">ISBN (International Standard Book Number)</label>
				    <input type="text" class="form-control" id="isbn" name="isbn"placeholder="Enter ISBN" min="10" max="10">
				  </div>
		
		      <input type="hidden" name="bookAction" value="checkout">
		      <button type="submit" class="btn btn-primary">Checkout</button>

		    </fieldset>
	  	</form>
	  	
	  	<form action="CheckoutServlet" method="POST">
		    <fieldset>
		      <legend>
		        <h4>Return Book</h4>
		      </legend>
		
		        <div class="form-group">
				    <label for="isbn">ISBN (International Standard Book Number)</label>
				    <input type="text" class="form-control" id="isbn" name="isbn"placeholder="Enter ISBN" min="10" max="10">
				  </div>
		
		      <input type="hidden" name="bookAction" value="return">
		      <button type="submit" class="btn btn-primary">Return</button>

		    </fieldset>
	  	</form>
	  	
	</section>

	<section id="patron-book-list">
	    <div style="padding: 1% 0">
	    <h4>Book Collections</h4>
      <nav>
        <div class="nav nav-tabs" id="nav-tab" role="tablist">
          <a
            class="nav-item nav-link active"
            id="nav-home-tab"
            data-toggle="tab"
            href="#nav-all"
            role="tab"
            aria-controls="nav-home"
            aria-selected="true"
            >All Books</a
          >
          <a
            class="nav-item nav-link"
            id="nav-profile-tab"
            data-toggle="tab"
            href="#nav-history"
            role="tab"
            aria-controls="nav-profile"
            aria-selected="false"
            >Checkout History</a
          >
        </div>
      </nav>
      <div class="tab-content" id="nav-tabContent">
        <div
          class="tab-pane fade show active"
          id="nav-all"
          role="tabpanel"
          aria-labelledby="nav-home-tab"
        > 
			
			 <form action="BookServlet" method="GET">
			    <fieldset class="text-center" style="padding: 5%">

					<% request.setAttribute("bookList", request.getAttribute("allBooks")); %>
					  <jsp:include page="book-list.jsp" ></jsp:include>
				     <button type="submit" class="btn btn-primary" name="userType" value="patron">Load Books</button>
		
			    </fieldset>
			 </form>
			 
        </div>
        <div
          class="tab-pane fade"
          id="nav-history"
          role="tabpanel"
          aria-labelledby="nav-profile-tab"
        >

			 <form action="CheckoutServlet" method="GET">
			    <fieldset class="text-center" style="padding: 5%">
				     <% request.setAttribute("checkoutList", request.getAttribute("allCheckouts")); %>
					 <jsp:include page="checkout-list.jsp"></jsp:include>
				     <button type="submit" class="btn btn-primary" name="userType" value="patron">Load Books</button>
		
			    </fieldset>
			 </form>

        </div>

      </div>
    </div>
	
	</section>
</div>



​
<%@ include file= "footer.jsp" %>