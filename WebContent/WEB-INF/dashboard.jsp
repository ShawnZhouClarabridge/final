<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
#table-scroll {
  height:150px;
  overflow:auto;  
  margin-top:20px;
}

</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
</head>
<body>
<%@ include file="menu.html" %><br/><br/><br/><br/><br/><br/><br/><br/>

<div id="table-scroll" style="height:200px;width:300px;position:absolute;left:40px;top:50px">
<h1> Courses </h1>
<table border="1" align="left" >
<tr><th>Course Name</th><th>Course Id</th><th>Price</th></tr>
<c:forEach var="course" items="${data.courseDetails}">
<tr>
<td><c:out value="${course.name}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><c:out value="${course.id}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><c:out value="${course.price}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>

</tr>
</c:forEach>
</table>
</div>



<div id="table-scroll" style="height:200px;width:400px;position:absolute;left:900px;top:50px">
<h1>Trainers</h1>
<table border="1" >
<tr><th>Trainer Name</th><th>Experience</th><th>Email</th><th>Phone</th></tr>
<c:forEach var="trainer" items="${data.trainerDetails}">
<tr>
<td><c:out value="${trainer.name}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><c:out value="${trainer.experience}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><c:out value="${trainer.email}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><c:out value="${trainer.phone}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
</c:forEach>
</table>
</div>

<br/><br/><br/><br/>
<br/><br/>
<br/><br/>
<br/><br/><br/><br/>



<div id="table-scroll" style="height:200px;width:300px;position:absolute;left:40px;top:300px">
<h1>Not Assigned Courses</h1>
<table border="1" >
<tr><th>Course</th><th>Start Date</th><th>End Date</th><th>Sessions</th></tr>
<c:forEach var="na" items="${data.notAssignedCourses}">
<tr>
<td><c:out value="${na.name}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><c:out value="${na.start_date}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><c:out value="${na.end_date}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><c:out value="${na.sessions}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
</c:forEach>
</table>
</div>


<br/><br/>
<div id="table-scroll" style="height:200px;width:400px;position:absolute;left:900px;top:300px">
<h1>Assigned Courses</h1>
<table border="1" >
<tr><th>Course</th><th>Start Date</th><th>End Date</th><th>Sessions</th></tr>
<c:forEach var="assigned" items="${data.assignedCourses}">
<tr>
<td><c:out value="${assigned.name}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><c:out value="${assigned.start_date}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><c:out value="${assigned.end_date}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><c:out value="${assigned.sessions}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
</c:forEach>
</table>
</div>

</body>
</html>