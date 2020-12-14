<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome Screen</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.1/css/all.css">

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
		<h1>Book Store Management</h1>
		<br>

		<nav class="navbar navbar-expand-md navbar-light bg-light naving">
			<ul class="navbar-nav mr-auto mt-lg-0">
				<li class="nav-item"><a
					class="nav-link text-primary btn btn-outline-success my-2 my-sm-0 border-0"
					href="RegisterServlet?action=view&name=${user.name}"> <i
						class="fas fa-user fa-md"></i>${user.name}
				</a></li>
				<li class="nav-item"><a
					class="nav-link text-primary btn btn-outline-success my-2 my-sm-0 border-0"
					href="RentServlet?action=URentList&uid=${user.id}&name=${user.name}">Rent
						Book List</a></li>
				<li class="nav-item"><a
					class="nav-link text-primary btn btn-outline-success my-2 my-sm-0 border-0"
					href="index.jsp">Logout</a></li>
			</ul>

			<form class="form-inline my-lg-0"
				action="RegisterServlet?action=search&name=${user.name}"
				method="post">
				<select name="category" class="form-control">
					<c:forEach items="${categoryList }" var="book">
						<option value="${book.id}">${book.name }</option>

					</c:forEach>
				</select>
				<button class="btn btn-outline-success my-2 my-sm-0">Search</button>
			</form>
		</nav>

		<div class="cards">
			<c:forEach items="${bookList}" var="std" varStatus="row">
				<div class="card" style="width: 160px">
					<img class="card-img-top" src="data:image/jpg;base64,${std.photo}"
						alt="Text" style="width: 100%" />
					<div class="card-body">
						<h5 class="card-title">${std.bname}</h5>
						<h6 class="card-text">${std.author}</h6>
						<a
							href="RegisterServlet?action=byID&id=${std.id}&uname=${user.name}"
							class="btn btn-block btn-info">See Detail</a> <a
							href="RentServlet?action=adminRent&id=${std.id}&uid=${user.id}&uname=${user.name}"
							class="btn btn-block btn-secondary">Rent Book</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>