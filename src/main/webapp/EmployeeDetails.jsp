<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<!-- Link an external CSS for styling -->
<link rel="stylesheet" href="Registration.css">
<!-- The title that show in the browser tab -->
<title>Register Successfully</title>
</head>
<body>
	<h2>
		<!-- If the information is complete, a message will be printed and redirected to the register page -->
		<%=request.getAttribute("userName") %> your record successfully saved <br> 
		<a href="<%=request.getContextPath()%>/EmployeeRegister.jsp">Go back to registration</a>
	</h2>
</body>
</html>