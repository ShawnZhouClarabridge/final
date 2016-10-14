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
<table border="1" align="center">
<tr><th>Course Name</th><th>Starting date</th><th>Ending date</th><th>Sessions</th><th>Trainers</th></tr>
<c:forEach var="course" items="${model.course_list}">
<tr>
<td><c:out value="${course.name}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><c:out value="${course.start_date}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><c:out value="${course.end_date}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td><c:out value="${course.sessions}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
<td>
 <select name="trainer">
            <c:forEach var="item" items="${model.trainers}">
             <option value='${item}'>${item}</option>
            </c:forEach>
          </select>
</td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>