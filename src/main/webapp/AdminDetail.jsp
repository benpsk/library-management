<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Detail Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container pt-4">
		<h1>Book Detail</h1>
		<br>
		<ul class="nav nav-tabs bg-light">
			<li class="nav-item"><a class="nav-link"
				href="RegisterServlet?action=admin" class="btn btn-info">Home</a></li>
			<li class="nav-item"><a class="nav-link" href="index.jsp"
				class="btn btn-info">Log Out</a></li>

		</ul>

		<h3>${message }</h3>
		<div class="cards">
			<div class="card" style="width: 100%">
				<img class="card-img-top"
					src="data:image/jpg;base64,${bookList.photo}" alt="Text"
					style="width: 100%" />
				<div class="card-body">
					<h5>Book Name: ${bookList.bname}</h5>
					<h6>Book Author: ${bookList.author}</h6>
					<p>ISBN Number: ${bookList.isbn }</p>
					<p>Quantity: ${bookList.qty }</p>
					<p>Price: ${bookList.price }</p>
					<p>Book Category: ${bookList.cname }</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>