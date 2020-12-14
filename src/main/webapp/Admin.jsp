<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dash board</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
.cards {
		display: flex;
		flex-wrap: wrap;
		gap: 1rem;
		padding-top: 1.5rem;
}

.card {
		display: flex;
		flex-direction: column;
		max-width: 200px;
		overflow: hidden;
}
</style>
</head>
<body>
	<div class="container pt-4">
		<h1>Admin Dash Board</h1>
		<br>
		<nav class="navbar navbar-expand-md navbar-light bg-light naving">
			<ul class="navbar-nav mr-auto mt-lg-0">
				<li class="nav-item"><a
					class="nav-link text-primary btn btn-outline-success my-2 my-sm-0 border-0"
					href="RegisterServlet?action=admin">Home </a></li>
				<li class="nav-item"><a
					class="nav-link text-primary btn btn-outline-success my-2 my-sm-0 border-0"
					href="RentServlet?action=rentBook">Book Rent List</a></li>
				<li class="nav-item"><a
					class="nav-link text-primary btn btn-outline-success my-2 my-sm-0 border-0"
					href="index.jsp">Logout</a></li>
				<li class="nav-item"><a
					class="nav-link text-primary btn btn-outline-success my-2 my-sm-0 border-0"
					href="RegisterServlet?action=newForm">New Book</a></li>
			</ul>
			<form class="form-inline my-lg-0"
				action="RegisterServlet?action=searchAdmin" method="post">
				<select name="category" class="form-control">
					<c:forEach items="${categoryList }" var="book">
						<option value="${book.id}">${book.name }</option>

					</c:forEach>
				</select>
				<button class="btn btn-outline-success my-2 my-sm-0">Search</button>
			</form>
		</nav>


		<h3>${message }</h3>
		<div class="cards">
			<c:forEach items="${bookList}" var="std" varStatus="row">
				<div class="card" style="width: 160px">
					<img class="card-img-top" src="data:image/jpg;base64,${std.photo}"
						alt="Text" style="width: 100%" />
					<div class="card-body">
						<p>${std.bname}</p>
						<p>${std.author}</p>
						<a href="RegisterServlet?action=showEditForm&id=${std.id}"
							class="btn btn-sm btn-block btn-warning">Update</a> <a
							href="RegisterServlet?action=delete&id=${std.id}"
							class="btn btn-sm btn-block btn-danger">Delete</a> <a
							href="RegisterServlet?action=byAdminID&id=${std.id }"
							class="btn btn-sm btn-block btn-info">See Detail</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>