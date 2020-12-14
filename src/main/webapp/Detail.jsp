<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail Page</title>
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
		<nav class="navbar navbar-expand-md navbar-light bg-light naving">
			<ul class="navbar-nav mr-auto mt-lg-0">
				<li class="nav-item"><a
					class="nav-link text-primary btn btn-outline-success my-2 my-sm-0 border-0"
					href="RegisterServlet?action=view&name=${user.name}">${user.name}
						${user.id}</a></li>
				<li class="nav-item"><a
					class="nav-link text-primary btn btn-outline-success my-2 my-sm-0 border-0"
					href="index.jsp">Logout</a></li>
			</ul>
		</nav>

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