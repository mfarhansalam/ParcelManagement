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
		<h2>Parcel Information</h2>
	</div>
	<form action="UpdateParcelController" method="post" class="needs-validation" novalidate style="max-width: 800px; margin: 0 auto;">
		<div class="row g-3">
			<div class="col-sm-6">
				<label for="parcel_id" class="form-label">ID number</label>
				<input type="text" class="form-control" id="parcel_id" name="parcel_id" value="<c:out value="${parcelinfo.parcel_id}"/>" readonly>
				<div class="invalid-feedback"> Valid id number is required. </div>
			</div>
			<input type="hidden" name="staff_id" value="<c:out value="${session_id}"/>"/>
			<div class="col-sm-6">
				<label for="stud_id" class="form-label">Student</label>
				<select class="form-select" id="stud_id" name="stud_id" required>
					<option value=""></option>
					<c:forEach items="${studentlist}" var="s">
						<option value="<c:out value="${s.stud_id}"/>" <c:if test="${parcelinfo.stud_id == s.stud_id}">
							<c:out value="selected" /></c:if>>
							<c:out value="${s.stud_name}" />
						</option>
					</c:forEach>
				</select>
				<div class="invalid-feedback"> Valid student is required. </div>
			</div>
			<div class="col-sm-4">
				<label for="parcel_status" class="form-label">Status</label>
				<select class="form-select" id="parcel_status" name="parcel_status" required>
					<option value="Not Received" <c:if test="${parcelinfo.parcel_status == 'Not Received'}">
						<c:out value="selected" /></c:if>>Not Received</option>
					<option value="Received" <c:if test="${parcelinfo.parcel_status == 'Received'}">
						<c:out value="selected" /></c:if>>Received</option>
				</select>
				<div class="invalid-feedback"> Valid status is required. </div>
			</div>
			<div class="col-sm-4">
				<label for="parcel_date" class="form-label">Arrival date</label>
				<input type="date" class="form-control" id="parcel_date" name="parcel_date" value="<c:out value="${parcelinfo.parcel_date}"/>" required>
				<div class="invalid-feedback"> Valid arrival date is required. </div>
			</div>
			<div class="col-sm-4">
				<label for="parcel_weight" class="form-label">Weight</label>
				<input type="number" class="form-control" id="parcel_weight" name="parcel_weight" value="<c:out value="${parcelinfo.parcel_weight}"/>" required>
				<div class="invalid-feedback"> Valid weight is required. </div>
			</div>
			<div class="col-sm-6">
				<label for="courier_id" class="form-label">Courier</label>
				<select class="form-select" id="courier_id" name="courier_id" required>
					<option value=""></option>
					<c:forEach items="${courierlist}" var="c">
						<option value="<c:out value="${c.courier_id}"/>" <c:if test="${parcelinfo.courier_id == c.courier_id}">
							<c:out value="selected" /></c:if>>
							<c:out value="${c.courier_name}" />
						</option>
					</c:forEach>
				</select>
				<div class="invalid-feedback"> Please provide a valid Courier ID. </div>
			</div>
			<button class="w-100 my-5 btn btn-primary btn-lg" type="submit">Submit</button>
		</div>
	</form>
	<script src="form-validation.js"></script>
</body>

</html>