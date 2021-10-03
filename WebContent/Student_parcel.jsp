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
  
  th, td {
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
    <a class="align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none"><span class="fs-4">Student</span></a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
      <li class="nav-item"><a href="RedirectController?action=studentinfo" class="nav-link link-dark px-4">Information</a></li>
      <li><a href="RedirectController?action=studentparcel" class="nav-link active px-4">Parcel</a></li>
      <li><a href="LogoutController" class="nav-link link-dark px-4">Logout</a></li>
    </ul>
  </div>
  <div style="margin-left: 270px;" class="p-4">
    <div class="py-5 text-center">
      <h2>Parcel Information</h2>
    </div>
    <table id="table_id" class="display">
      <thead>
        <tr>
          <th>ID Number</th>
          <th>Status</th>
          <th>Weight</th>
          <th>Arrival Date</th>
          <th>Staff</th>
          <th>Courier</th>
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
              <c:out value="${p.staff.staff_name}" />
            </td>
            <td>
              <c:out value="${p.courier.courier_name}" />
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</body>

</html>