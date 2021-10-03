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
	
	input[type=date]:required:invalid::-webkit-datetime-edit {
		color: transparent;
	}
	
	input[type=date]:focus::-webkit-datetime-edit {
		color: black !important;
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
			<li> <a href="RedirectController?action=staffcourier" class="nav-link link-dark px-4">Courier</a> </li>
			<li> <a href="RedirectController?action=staffstudent" class="nav-link link-dark px-4">Student</a> </li>
			<li> <a href="RedirectController?action=staffparcel" class="nav-link active px-4">Parcel</a> </li>
			<li> <a href="RedirectController?action=staffreport" class="nav-link link-dark px-4">Report</a> </li>
			<li> <a href="LogoutController" class="nav-link link-dark px-4">Logout</a> </li>
		</ul>
	</div>
	<div style="margin-left: 270px;" class="p-4">
		<div class="py-5 text-center">
			<h2>Parcel Information</h2> </div>
		<form action="AddParcelController" method="post" class="needs-validation" novalidate style="max-width: 800px; margin: 0 auto;">
			<div class="row g-3">
				<c:if test="${process_status != 'success'}">
					<div class="alert alert-danger text-center" role="alert">
						<c:out value="${process_status}" /> </div>
				</c:if>
				<div class="col-sm-6">
					<label for="parcel_id" class="form-label">ID number</label>
					<input type="text" class="form-control" id="parcel_id" name="parcel_id" required>
					<div class="invalid-feedback"> Valid id number is required. </div>
				</div>
				<input type="hidden" name="staff_id" value="<c:out value="${session_id}"/>"/>
				<div class="col-sm-6">
					<label for="stud_id" class="form-label">Student</label>
					<select class="form-select" id="stud_id" name="stud_id" required>
						<option value=""></option>
						<c:forEach items="${studentlist}" var="s">
							<option value="<c:out value="${s.stud_id}"/>">
								<c:out value="${s.stud_name}" />
							</option>
						</c:forEach>
					</select>
					<div class="invalid-feedback"> Valid student is required. </div>
				</div>
				<div class="col-sm-4">
					<label for="parcel_status" class="form-label">Status</label>
					<select class="form-select" id="parcel_status" name="parcel_status" required>
						<option>Not Received</option>
						<option>Received</option>
					</select>
					<div class="invalid-feedback"> Valid status is required. </div>
				</div>
				<div class="col-sm-4">
					<label for="parcel_date" class="form-label">Arrival date</label>
					<input type="date" class="form-control" id="parcel_date" name="parcel_date" required>
					<div class="invalid-feedback"> Valid arrival date is required. </div>
				</div>
				<div class="col-sm-4">
					<label for="parcel_weight" class="form-label">Weight</label>
					<input type="number" class="form-control" id="parcel_weight" name="parcel_weight" required>
					<div class="invalid-feedback"> Valid weight is required. </div>
				</div>
				<div class="col-sm-6">
					<label for="courier_id" class="form-label">Courier</label>
					<select class="form-select" id="courier_id" name="courier_id" required>
						<option value=""></option>
						<c:forEach items="${courierlist}" var="c">
							<option value="<c:out value="${c.courier_id}"/>">
								<c:out value="${c.courier_name}" />
							</option>
						</c:forEach>
					</select>
					<div class="invalid-feedback"> Please provide a valid Courier ID. </div>
				</div>
				<button class="w-100 my-5 btn btn-primary btn-lg" type="submit">Submit</button>
			</div>
		</form>
		<div class="py-5 text-center">
			<h2>Parcel List</h2> </div>
		<table id="table_id" class="display">
			<thead>
				<tr>
					<th>ID Number</th>
					<th>Status</th>
					<th>Weight</th>
					<th>Arrival Date</th>
					<th>Student</th>
					<th>Courier</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${parceljoinlist}" var="p">
					<tr>
						<td>
							<c:out value="${p.parcel_id}" />
						</td>
						<td>
							<c:out value="${p.parcel_status}" />
						</td>
						<td>
							<c:out value="${p.parcel_weight}" />
						</td>
						<td>
							<c:out value="${p.parcel_date}" />
						</td>
						<td>
							<c:out value="${p.student.stud_name}" />
						</td>
						<td>
							<c:out value="${p.courier.courier_name}" />
						</td>
						<td>
							<input type="button" class="w-40 btn btn-secondary" value="Update" onclick="window.location.href='RedirectController?action=updateparcel&updateid=<c:out value="${p.parcel_id}"/>'">
							<input type="button" class="w-40 btn btn-secondary" value="Delete" onclick="window.location.href='RedirectController?action=deleteparcel&deleteid=<c:out value="${p.parcel_id}"/>'">
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="form-validation.js"></script>
</body>

</html>