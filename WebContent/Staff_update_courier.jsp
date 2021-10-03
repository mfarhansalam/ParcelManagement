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
</head>

<body>
	<div class="py-5 text-center">
		<h2>Courier Information</h2>
	</div>
	<form action="UpdateCourierController" method="post" class="needs-validation" novalidate style="max-width: 800px; margin: 0 auto;">
		<div class="row g-3">
			<div class="col-sm-6">
				<label for="courier_id" class="form-label">ID number</label>
				<input type="text" class="form-control" id="courier_id" name="courier_id" value="<c:out value="${courierinfo.courier_id}"/>" readonly>
				<div class="invalid-feedback"> Valid id number is required. </div>
			</div>
			<div class="col-sm-6">
				<label for="courier_phonenum" class="form-label">Phone number</label>
				<input type="text" class="form-control" id="courier_phonenum" name="courier_phonenum" value="<c:out value="${courierinfo.courier_phonenum}"/>" required>
				<div class="invalid-feedback"> Valid phone number is required. </div>
			</div>
			<div class="col-12">
				<label for="courier_name" class="form-label">Name</label>
				<input type="text" class="form-control" id="courier_name" name="courier_name" value="<c:out value="${courierinfo.courier_name}"/>" required>
				<div class="invalid-feedback"> Valid name is required. </div>
			</div>
			<button class="w-100 my-5 btn btn-primary btn-lg" type="submit">Submit</button>
		</div>
	</form>
	<script src="form-validation.js"></script>
</body>

</html>