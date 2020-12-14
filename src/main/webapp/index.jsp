<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Store Management</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<style>
.center {
		padding: 200px 0;
		text-align: center;
}
</style>
</head>
<body>
	<div class="container pt-4">

		<h1>Book Store Management</h1>
		<br>

		<ul class="nav nav-tabs bg-light">
			<li class="nav-item"><a class="nav-link" href="Login.jsp"
				class="btn btn-info">Login</a></li>
			<li class="nav-item"><a class="nav-link" href="Register.jsp"
				class="btn btn-info">Register</a></li>
		</ul>


		<div class="center">
			<h4 class="text-danger">Please Login or Register to see detail!</h4>
		</div>

	</div>

</body>
</html>