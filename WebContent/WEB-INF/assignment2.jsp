<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Courses</title>
</head>
<body>
<%@ include file="menu.html" %><br/><br/><BR/><BR/><BR/><br/><br/>
<div align="center">
<h1><center>Assign Courses :</center></h1><br/>
<form method="post" action="courseassignment">
<table border="1" align="center">
<tr><th>Course Name | Start_Date | End_Date | Sessions</th><th>Trainers</th></tr>

<tr>

<td>
 <select name="course">
            <c:forEach var="course" items="${model.course_list}">
             <option value='${course.name},${course.start_date},${course.end_date},${course.sessions}'>${course.name} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   |${course.start_date} &nbsp;&nbsp;&nbsp;   | ${course.end_date} | ${course.sessions}</option>
            </c:forEach>
          </select>
</td>
<!--  
<td><c:out value="${course.start_date}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><c:out value="${course.end_date}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><c:out value="${course.sessions}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>
-->
<td>
 <select name="trainer">
            <c:forEach var="item" items="${model.trainers}">
             <option value='${item}'>${item}</option>
            </c:forEach>
          </select>
</td>


</tr>

</table><br/><br/>
<input type="submit" value="Assign"/>
</form>
</div>
</body>
</html>