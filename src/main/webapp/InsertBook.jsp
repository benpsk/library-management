<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Book</title>
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

		<h1>Add New Books</h1>
		<form action="RegisterServlet?action=newBook" method="post"
			enctype="multipart/form-data">
			<div class="form-group">
				<label>Book Name </label> <input type="text" name="bname"
					class="form-control" />
			</div>
			<div class="form-group">
				<label>Book Quantity </label> <input type="number" name="qty"
					class="form-control" />
			</div>
			<div class="form-group">
				<label>Book Price </label> <input type="number" name="price"
					class="form-control" />
			</div>
			<div class="form-group">
				<label>Select Photo</label> <input type="file" name="photo"
					class="form-control" />
			</div>
			<div class="form-group">
				<label>Book Author </label> <input type="text" name="author"
					class="form-control" />
			</div>
			<div class="form-group">
				<label>Book Category </label> <select name="category"
					class="form-control">
					<c:forEach items="${categoryList }" var="book">
						<option value="${book.id}">${book.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label>Book ISBN number </label> <input type="text" name="isbn"
					class="form-control" />
			</div>
			<input type="submit" value="Sign Up" class="btn btn-primary" /> <a
				href="RegisterServlet?action=admin" class="btn btn-primary">Back</a>
		</form>

	</div>

</body>
</html>