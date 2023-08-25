<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<!-- Link an external CSS for styling -->
<link rel="stylesheet" href="Registration.css">
<!-- The title that show in the browser tab -->
<title>Register Form</title>
</head>
<body>
	<div>
		<h1>Employee Register Form</h1>
		<form action="<%=request.getContextPath()%>/register" method="post">
			<input type="text" name="firstName" placeholder="First Name" value="<%=request.getParameter("firstName") == null ? "" : request.getParameter("firstName")%>" onkeypress='return event.charCode >= 65 && event.charCode <= 90 || (event.charCode >= 97 && event.charCode <= 122)' required maxlength="10" /> 
			<input type="text" name="lastName" placeholder="Last Name" value="<%=request.getParameter("lastName") == null ? "" : request.getParameter("lastName")%>" onkeypress='return event.charCode >= 65 && event.charCode <= 90 || (event.charCode >= 97 && event.charCode <= 122)' required maxlength="10" /> 
			<input type="text" name="userName" placeholder="Username" value="<%=request.getParameter("userName") == null ? "" : request.getParameter("userName")%>" required maxlength="10" /> 
			<input type="password" name="password" placeholder="Password" value="<%=request.getParameter("password") == null ? "" : request.getParameter("password")%>" pattern="(?=.*\d)(?=.*[a-z]).{8,}"
				   title="Must contain at least one number and lower case letter,and at least 8 more characters" required /> 
			<input type="text" name="contact" placeholder="Contact number" onkeypress='return event.charCode >= 48 && event.charCode <= 57' required maxlength="10" /> 				
			<input type="email" name="email" placeholder="Email" value="<%=request.getParameter("email") == null ? "" : request.getParameter("email")%>" required /> 
			<input type="text" name="address" placeholder="Address" value="<%=request.getParameter("address") == null ? "" : request.getParameter("address")%>" required /> 
			<input type="submit" value="Submit" /> 
			<input type="reset" value="Reset" />
		</form>
		<%
		String error = (String) request.getAttribute("error");
		if (error != null) {
		%>
		<p style="color: red; font-weight: bold"><%=error%></p>
		<% } %>
	</div>
</body>
</html>