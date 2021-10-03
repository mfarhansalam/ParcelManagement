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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.css">
	<link href="assets/dist/css/bootstrap.css" rel="stylesheet">
	<script>
	$(document).ready(function() {
		$('#table_id').DataTable();
	});
	</script>
	<style>
	.sidebar {
		width: 260px;
		height: 100%;
		margin: 0;
		padding: 0;
		position: fixed;
		overflow: auto;
	}
	
	th,
	td {
		border-top: 1px solid #000;
		border-bottom: 1px solid #000;
	}
	
	table {
		border-bottom: none !important;
		padding: 20px;
	}
	</style>
</head>

<body>
	<div class="p-3 bg-secondary sidebar">
		<a href="" class=" align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none"> <span class="fs-4">Staff</span> </a>
		<hr>
		<ul class="nav nav-pills flex-column mb-auto">
			<li class="nav-item"> <a href="RedirectController?action=staffinfo" class="nav-link link-dark px-4">Information</a> </li>
			<li> <a href="RedirectController?action=staffcourier" class="nav-link active px-4">Courier</a> </li>
			<li> <a href="RedirectController?action=staffstudent" class="nav-link link-dark px-4">Student</a> </li>
			<li> <a href="RedirectController?action=staffparcel" class="nav-link link-dark px-4">Parcel</a> </li>
			<li> <a href="RedirectController?action=staffreport" class="nav-link link-dark px-4">Report</a> </li>
			<li> <a href="LogoutController" class="nav-link link-dark px-4">Logout</a> </li>
		</ul>
	</div>
	<div style="margin-left: 270px;" class="p-4">
		<div class="py-5 text-center">
			<h2>Courier Information</h2> </div>
		<form action="AddCourierController" method="post" class="needs-validation" novalidate style="max-width: 800px; margin: 0 auto;">
			<div class="row g-3">
				<c:if test="${process_status != 'success'}">
					<div class="alert alert-danger text-center" role="alert">
						<c:out value="${process_status}" /> </div>
				</c:if>
				<div class="col-sm-6">
					<label for="courier_id" class="form-label">ID number</label>
					<input type="text" class="form-control" id="courier_id" name="courier_id" placeholder="" required>
					<div class="invalid-feedback"> Valid id number is required. </div>
				</div>
				<div class="col-sm-6">
					<label for="courier_phonenum" class="form-label">Phone number</label>
					<input type="text" class="form-control" id="courier_phonenum" name="courier_phonenum" placeholder="" required>
					<div class="invalid-feedback"> Valid phone number is required. </div>
				</div>
				<div class="col-12">
					<label for="courier_name" class="form-label">Name</label>
					<input type="text" class="form-control" id="courier_name" name="courier_name" placeholder="" required>
					<div class="invalid-feedback"> Valid name is required. </div>
				</div>
				<button class="w-100 my-5 btn btn-primary btn-lg" type="submit">Submit</button>
			</div>
		</form>
		<div class="py-5 text-center">
			<h2>Courier List</h2> </div>
		<table id="table_id" class="display">
			<thead>
				<tr>
					<th>ID Number</th>
					<th>Name</th>
					<th>Phone Number</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${courierlist}" var="c">
					<tr>
						<td>
							<c:out value="${c.courier_id}" />
						</td>
						<td>
							<c:out value="${c.courier_name}" />
						</td>
						<td>
							<c:out value="${c.courier_phonenum}" />
						</td>
						<td>
							<input type="button" class="w-40 btn btn-secondary" value="Update" onclick="window.location.href='RedirectController?action=updatecourier&updateid=<c:out value="${c.courier_id}"/>'">
							<input type="button" class="w-40 btn btn-secondary" value="Delete" onclick="window.location.href='RedirectController?action=deletecourier&deleteid=<c:out value="${c.courier_id}"/>'"> </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="form-validation.js"></script>
</body>

</html>