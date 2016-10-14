<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Batch Assignment</title>

</head>
<body>
<%@ include file="menu.html" %><br/><br/><br/><br/><br/><br/>

<div align="center">
<form metod="post" action="batAssign">
   Select Course       <select name="course">
            <option value='na'>--- Select Course ---</option>
            <c:forEach var="item" items="${model.batches}">
             <option value='${item}'>${item}</option>
            </c:forEach>
          </select>
          <input type="submit" value="Assign Trainer"/>
</form>
</div>
<br/><br/><br/><br/>

<div align="center">
<h1><center>Assign Trainer :</center></h1><br/>
<form method="post" action="courseassignment">
<table border="1" align="center">
<tr><th>Course Name | Start_Date | End_Date | Sessions</th><th>Trainers</th></tr>

<tr>

<td>
 <select name="course">
  <option value="batch">--- Select Batch ---</option>
            <c:forEach var="course" items="${model.course_list}">
             <option value='${course.name},${course.start_date},${course.end_date},${course.sessions}'>${course.name} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   |${course.start_date} &nbsp;&nbsp;&nbsp;   | ${course.end_date} | ${course.sessions}</option>
            </c:forEach>
          </select>
</td>

<td>
 <select name="trainer">
   <option value="batch">--- Select Trainer ---</option>
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