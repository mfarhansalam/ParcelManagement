<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	
	if(session.getAttribute("session_id") == null) {
		response.sendRedirect("Student_login.jsp");
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
	<title>Student Page | UiTM Parcel Management System</title>
	<link href="assets/dist/css/bootstrap.css" rel="stylesheet">
	<script src="assets/dist/js/bootstrap.bundle.min.js"></script>
	<script>
	function notification_modal() {
		let myModal = new bootstrap.Modal(document.getElementById('exampleModal'), {});
		myModal.show();
	}
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
	</style>
</head>

<body>
	<div class="p-3 bg-secondary sidebar">
		<a class="align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none"><span class="fs-4">Student</span></a>
		<hr>
		<ul class="nav nav-pills flex-column mb-auto">
			<li class="nav-item"><a href="RedirectController?action=studentinfo" class="nav-link active px-4">Information</a></li>
			<li><a href="RedirectController?action=studentparcel" class="nav-link link-dark px-4">Parcel</a></li>
			<li><a href="LogoutController" class="nav-link link-dark px-4">Logout</a></li>
		</ul>
	</div>
	<div style="margin-left: 270px;" class="p-4">
		<div class="py-5 text-center">
			<h2>Student Information</h2>
    </div>
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Attention</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body text-center p-4"> You still have remaining parcel to be claimed. </div>
				</div>
			</div>
		</div>
		<c:if test="${noti_status == true}">
			<script>
			notification_modal();
			</script>
		</c:if>
		<form action="UpdateStudentController" method="post" class="needs-validation" novalidate style="max-width: 800px; margin: 0 auto;">
			<div class="row g-3">
				<div class="col-sm-6">
					<label for="stud_id" class="form-label">ID number</label>
					<input type="text" class="form-control" id="stud_id" name="student_id" value="<c:out value="${student.stud_id}" />" readonly>
					<div class="invalid-feedback"> Valid id number is required. </div>
				</div>
				<div class="col-12">
					<label for="stud_name" class="form-label">Full name</label>
					<input type="text" class="form-control" id="stud_name" name="student_name" value="<c:out value="${student.stud_name}" />" required>
					<div class="invalid-feedback"> Valid full name is required. </div>
				</div>
				<div class="col-sm-6">
					<label for="stud_email" class="form-label">Email</label>
					<input type="email" class="form-control" id="stud_email" name="student_email" value="<c:out value="${student.stud_email}" />" required>
					<div class="invalid-feedback"> Valid email address is required. </div>
				</div>
				<div class="col-sm-6">
					<label for="stud_phonenum" class="form-label">Phone number</label>
					<input type="text" class="form-control" id="stud_phonenum" name="student_phonenum" value="<c:out value="${student.stud_phonenum}" />" required>
					<div class="invalid-feedback"> Valid phone number is required. </div>
				</div>
				<button class="w-100 my-5 btn btn-primary btn-lg" type="submit">Update</button>
			</div>
		</form>
	</div>
	<script src="form-validation.js"></script>
</body>

</html>