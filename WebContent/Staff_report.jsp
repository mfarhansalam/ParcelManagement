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
  <!-- Bootstrap core CSS -->
  <link href="assets/dist/css/bootstrap.css" rel="stylesheet">
  <script>
    $(document).ready(function() {
      $('#table_id').DataTable();
    });
  </script>
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
	google.charts.load('current', {'packages':['bar']});
    google.charts.load('current', { 'packages': ['corechart'] });
	google.charts.setOnLoadCallback(drawCourierChart);
	google.charts.setOnLoadCallback(drawStatusChart);
	google.charts.setOnLoadCallback(drawMonthChart);
	
	function drawCourierChart() {
		var courier_array = new Array();
		
		courier_array[0] = ['Courier', 'Total'];
		<c:forEach begin="0" end="${parcelcourierlist.size()}" step="1" varStatus="loop" items="${parcelcourierlist}" var="pc">
			courier_array["${loop.count}"] = ["${pc.courier.courier_name}", +"${pc.total_by_courier}"];
		</c:forEach>
		
		var data = new google.visualization.arrayToDataTable(courier_array);
		var options = {
			legend: { position: 'none'},
			bar: { groupWidth: '40%' },
			colors: ['#ffcc00']
		};
		
		var chart = new google.charts.Bar(document.getElementById('courier_chart'));
		chart.draw(data, google.charts.Bar.convertOptions(options));
	}
	
	function drawStatusChart() {
		var status_array = new Array();
		
		status_array[0] = ['Status', 'Total'];
		<c:forEach begin="0" end="${parcelstatuslist.size()}" step="1" varStatus="loop" items="${parcelstatuslist}" var="ps">
			status_array["${loop.count}"] = ["${ps.parcel_status}", +"${ps.total_by_status}"];
		</c:forEach>
		
		var data = new google.visualization.arrayToDataTable(status_array);
		var options = {
			legend: { position: 'left' },
			colors: ['#0086b3', '#006080']
		};
		
		var chart = new google.visualization.PieChart(document.getElementById('status_chart'));
		chart.draw(data, options);
	}
	
	function drawMonthChart() {
		var status_array = new Array();
		
		status_array[0] = ['Month', 'Total'];
		<c:forEach begin="0" end="${parcelmonthlist.size()}" step="1" varStatus="loop" items="${parcelmonthlist}" var="pm">
			status_array["${loop.count}"] = ["${pm.parcel_month}", +"${pm.total_by_month}"];
		</c:forEach>
		
		var data = new google.visualization.arrayToDataTable(status_array, false);
		var options = {
			legend: { position: 'none'},
			bar: { groupWidth: '40%' },
			colors: ['#ff66cc']
		};
		
		var chart = new google.charts.Bar(document.getElementById('month_chart'));
		chart.draw(data, google.charts.Bar.convertOptions(options));
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
		<a href="" class=" align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
      <span class="fs-4">Staff</span>
    </a>
		<hr>
		<ul class="nav nav-pills flex-column mb-auto">
			<li class="nav-item">
        <a href="RedirectController?action=staffinfo" class="nav-link link-dark px-4">Information</a>
      </li>
			<li>
        <a href="RedirectController?action=staffcourier" class="nav-link link-dark px-4">Courier</a>
      </li>
			<li>
        <a href="RedirectController?action=staffstudent" class="nav-link link-dark px-4">Student</a>
      </li>
			<li>
        <a href="RedirectController?action=staffparcel" class="nav-link link-dark px-4">Parcel</a>
      </li>
			<li>
        <a href="RedirectController?action=staffreport" class="nav-link active px-4">Report</a>
      </li>
			<li>
        <a href="LogoutController" class="nav-link link-dark px-4">Logout</a>
      </li>
		</ul>
	</div>
	<div style="margin-left: 270px;" class="p-4">
    <div class="py-5 text-center">
      <h2>Parcel Report</h2>
      <div id="courier_chart" class="m-5" style="width: 500px; height: 350px; margin: 0 auto; display: inline-block;"></div>
      <div id="status_chart" class="m-5" style="width: 500px; height: 350px; margin: 0 auto; display: inline-block;"></div>
      <div id="month_chart" class="m-5" style="width: 80%; height: 350px; margin: 0 auto; display: inline-block;"></div>
      <table id="table_id" class="display">
      <thead>
        <tr>
          <th>ID Number</th>
          <th>Status</th>
          <th>Weight</th>
          <th>Arrival Date</th>
          <th>Staff</th>
          <th>Courier</th>
          <th>Student</th>
        </tr>
      </thead>
      <tbody>
      	<c:forEach items="${parcellist}" var="p">
        <tr>
          <td><c:out value="${p.parcel_id}"/></td>
          <td><c:out value="${p.parcel_status}"/></td>
          <td><c:out value="${p.parcel_weight}"/></td>
          <td><c:out value="${p.parcel_date}"/></td>
          <td><c:out value="${p.staff.staff_name}"/></td>
          <td><c:out value="${p.student.stud_name}"/></td>
          <td><c:out value="${p.courier.courier_name}"/></td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
    </div>
  </div>
	<script src="form-validation.js"></script>
</body>

</html>