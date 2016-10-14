<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Course</title>
</head>
<body>
<%@ include file="menu.html" %><br/><br/><br/><br/><br/><br/>
<div align="center">
<form metod="post" action="modifyCourse">
   Select Course       <select name="course">
            <option>--- Select Course ---</option>
            <c:forEach var="item" items="${courses}">
             <option value='${item.name}'>${item.name}</option>
            </c:forEach>
          </select>
          <input type="submit" value="Update"/>
</form>
</div><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>  
<form  method="POST" action="courseUpdated">
<table style="height:100%;width:100%">
	<tr>
	<td  style="height:100%;width:25%">
	</td>
	<td style="height:100%;width:50%"> 
		<fieldset style="background:white">
		<legend>Update Course</legend>
			<div class="divv">
				<label for="name">Course Name</label>
				<input type="text" name="name" placeholder="Course Name"  value="${course.name}"class="textbox"/>
			</div>
			<div class="divv">
				<label for="id">Course Id</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" name="id"   placeholder="Course Id" value="${course.id}" class="textbox"/>
			</div>
			<div class="divv">
				<label for="price">Course Price</label>&nbsp;
				 <input type="text" name="price" placeholder="Course Price" value="${course.price}"  class="textbox"/>
			</div><br/>
			<div align="center" class="divv">
				<input type="submit" value="Submit" class="btn"/>
				
			</div>
		</fieldset>
		</td>
		<td style="height:100%;width:25%">
		</td>
		</tr>
		</table>
		
	</form>
	-->
</body>
</html>