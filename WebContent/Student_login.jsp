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
	<title>Sign in | UiTM Parcel Management System</title>
	<link href="assets/dist/css/bootstrap.css" rel="stylesheet">
	<link href="signin.css" rel="stylesheet">
</head>

<body class="text-center">
	<div class="form-signin">
		<button class="w-50 tab-btn p-3 fw-bold" onclick="window.location.href='Student_login.jsp'">Student</button><button class="w-50 tab-btn p-3 disable" onclick="window.location.href='Staff_login.jsp'">Staff</button>
		<main class="p-4">
			<form action="LoginController" method="post">
				<h1 class="h3 my-3 fw-bold">Sign In</h1>
				<c:if test = "${status != null}">
					<div class="alert alert-danger" role="alert">
						<c:out value="${status}"/>
					</div>
				</c:if>
				<div class="form-floating">
					<input type="text" class="form-control" id="floatingInput" placeholder="ID number" name="user_id" required>
					<label for="floatingInput">ID number</label>
				</div>
				<div class="form-floating">
					<input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="user_password" required>
					<label for="floatingPassword">Password</label>
				</div>
				<input type="hidden" name="user_type" value="Student"/>
				<button class="w-100 mt-4 btn btn-lg btn-primary" type="submit">Sign in</button>
				<div class="pt-4">Don't have an account? <a href="Register.jsp" style="text-decoration: none;">Register</a> </div>
				<p class="mt-5 mb-3 text-muted">2017 - 2021</p>
			</form>
		</main>
	</div>
</body>

</html>