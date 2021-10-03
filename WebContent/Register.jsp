<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
	<meta name="generator" content="Hugo 0.84.0">
	<title>Sign up | UiTM Parcel Management System</title>
	<link href="assets/dist/css/bootstrap.css" rel="stylesheet">
	<link href="signin.css" rel="stylesheet">
</head>

<body class="text-center">
	<div class="form-signin">
		<main class="p-4">
			<form action="RegisterController" method="post">
				<h1 class="h3 my-3 fw-bold">Sign Up</h1>
				<c:if test="${status != null}">
					<div class="alert alert-danger" role="alert">
						<c:out value="${status}" /> </div>
				</c:if>
				<div class="form-floating">
					<input type="text" class="form-control" id="floatingInput" placeholder="ID number" name="user_id" required>
					<label for="floatingInput">ID number</label>
				</div>
				<div class="form-floating">
					<input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="user_password" required>
					<label for="floatingPassword">Password</label>
				</div>
				<select class="form-select p-3 mb-5" name="user_type" required>
					<option value="">User type</option>
					<option value="Student">Student</option>
					<option value="Staff">Staff</option>
				</select>
				<button class="w-100  mb-5 btn btn-lg btn-primary" type="submit">Sign up</button>
			</form>
		</main>
	</div>
</body>

</html>