<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Register User</title>
	</head>
	<body>
		<h2>User Registration</h2>
		<form action="registerUser" method="post">
			<pre>
				User Name:<input type="text" name="username"/>
				Password:<input type="password" name="password"/>
				<input type="submit" value="Register"/>
			</pre>
		</form>
	</body>
</html>