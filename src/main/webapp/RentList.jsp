<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Rent List</title>
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
		<h1>Book Rent List</h1>
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
		</nav>


		<table class="table mt-4">
			<tbody>
				<c:forEach items="${rentList}" var="std" varStatus="row">
					<tr>
						<td rowspan="7"><img
							src="data:image/jpg;base64,${std.stringPhoto}" alt="Text" /></td>
						<td>BookID</td>
						<td>${std.bookID}</td>
					</tr>
					<tr>
						<td>Book Name:</td>
						<td>${std.bookName}</td>
					</tr>
					<tr>
						<td>Book Author</td>
						<td>${std.bookAuthor}</td>
					</tr>
					<tr>
						<td>User ID:</td>
						<td>${std.studentID}</td>
					</tr>
					<tr>
						<td>Rent By:</td>
						<td>${std.userName}</td>
					</tr>
					<tr>
						<td>Rent Date:</td>
						<td>${std.rentDate}</td>
					</tr>
					<tr>
						<td>Return Date:</td>
						<td>${std.returnDate}</td>
						<td>..</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>