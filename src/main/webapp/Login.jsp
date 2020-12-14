<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign in Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
.ajust {
		margin: auto;
		width: 60%;
		padding: 50px;
}
</style>
</head>
<body>

	<div class="jumbotron ajust pt-4 mt-5">
		<h1>Sign In</h1>
		<br>
		<br>
		<h4 class="text-danger">${message}</h4>
		<form action="RegisterServlet?action=login" method="post">

			<div class="form-group">
				<label>Enter name: </label> <input type="text" name="name"
					class="form-control" />
			</div>
			<div class="form-group">
				<label>Enter password: </label> <input type="password" name="pass"
					class="form-control" />
			</div>
			<input type="submit" value="Sign In" class="btn btn-primary" /> <a
				href="index.jsp" class="btn btn-primary">Back</a>

		</form>
	</div>

</body>
</html>