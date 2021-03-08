<%@ include file= "header.jsp" %>
<div class="login">
<HR WIDTH="100%" COLOR="#dbe2ef" SIZE="10px" style="height:10px">
  <h2><strong>Login Information</strong></h2>
<form action="LoginServlet" method="POST">
  <div class="imgcontainer">
    <img src="https://cdn.onlinewebfonts.com/svg/img_258083.png" alt="Avatar" class="avatar" style="padding: 1% 10%">
  </div>
	<fieldset>
        <legend class="text-center">Select User Type</legend>
        <div class="btn-group btn-group-toggle radio-buttons" data-toggle="buttons">
	        <label class="btn btn-secondary active" for="patron"><strong>Patron</strong>
	        	<input type="radio" name="user" id="patron" value="patron" autocomplete="off" checked>
	        </label>
	        <label class="btn btn-secondary" for="librarian"><strong>Librarian</strong>
	        	<input type="radio" name="user" id="librarian" value="librarian" autocomplete="off">
	        </label>
        </div>
     </fieldset>
<div id="message" class="text-center">
<% String message = (String) request.getAttribute("isValid"); %>
<% if (message != null) {%>
<p style="padding: 1% 10%; color: red"><%=message%></p>
<% } %>
</div>
  <div class="container">
    <label for="uname"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="uname" required>
    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="psw" required>
    <button type="submit">Login</button>
  </div>
</form>
		<HR WIDTH="100%" COLOR="#dbe2ef" SIZE="10px" style="height:10px">
</div>

<%@ include file= "footer.jsp" %>