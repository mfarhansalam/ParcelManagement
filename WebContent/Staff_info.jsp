<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	
	if(session.getAttribute("session_id") == null) {
		response.sendRedirect("Staff_login.jsp");
	}
%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
	<meta name="generator" content="Hugo 0.84.0">
	<title>Staff Page | UiTM Parcel Management System</title>
	<link href="assets/dist/css/bootstrap.css" rel="stylesheet">
	<style>
	.sidebar {
		width: 260px;
		height: 100%;
		margin: 0;
		padding: 0;
		position: fixed;
		overflow: auto;
	}
	</style>
</head>

<body>
	<div class="p-3 bg-secondary sidebar">
		<a href="" class=" align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none"> <span class="fs-4">Staff</span> </a>
		<hr>
		<ul class="nav nav-pills flex-column mb-auto">
			<li class="nav-item"> <a href="RedirectController?action=staffinfo" class="nav-link active px-4">Information</a> </li>
			<li> <a href="RedirectController?action=staffcourier" class="nav-link link-dark px-4">Courier</a> </li>
			<li> <a href="RedirectController?action=staffstudent" class="nav-link link-dark px-4">Student</a> </li>
			<li> <a href="RedirectController?action=staffparcel" class="nav-link link-dark px-4">Parcel</a> </li>
			<li> <a href="RedirectController?action=staffreport" class="nav-link link-dark px-4">Report</a> </li>
			<li> <a href="LogoutController" class="nav-link link-dark px-4">Logout</a> </li>
		</ul>
	</div>
	<div style="margin-left: 270px;" class="p-4">
		<div class="py-5 text-center">
			<h2>Staff Information</h2> </div>
		<form action="UpdateStaffController" method="post" class="needs-validation" novalidate style="max-width: 800px; margin: 0 auto;">
			<div class="row g-3">
				<div class="col-sm-6">
					<label for="staff_id" class="form-label">ID number</label>
					<input type="text" class="form-control" id="staff_id" name="staff_id" value="<c:out value="${staff.staff_id}" />" readonly>
					<div class="invalid-feedback"> Valid id number is required. </div>
				</div>
				<div class="col-12">
					<label for="staff_name" class="form-label">Full name</label>
					<input type="text" class="form-control" id="staff_name" name="staff_name" value="<c:out value="${staff.staff_name}" />" required>
					<div class="invalid-feedback"> Valid full name is required. </div>
				</div>
				<div class="col-6">
					<label for="staff_email" class="form-label">Email</label>
					<input type="email" class="form-control" id="staff_email" name="staff_email" value="<c:out value="${staff.staff_email}" />" required>
					<div class="invalid-feedback"> Valid email address is required. </div>
				</div>
				<div class="col-6">
					<label for="staff_phonenum" class="form-label">Phone number</label>
					<input type="text" class="form-control" id="staff_phonenum" name="staff_phonenum" value="<c:out value="${staff.staff_phonenum}" />" required>
					<div class="invalid-feedback"> Valid phone number is required. </div>
				</div>
				<button class="w-100 my-5 btn btn-primary btn-lg" type="submit">Update</button>
			</div>
		</form>
	</div>
	<script src="form-validation.js"></script>
</body>

</html>