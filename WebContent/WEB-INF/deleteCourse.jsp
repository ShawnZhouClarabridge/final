<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Course</title>
</head>
<body>
<%@ include file="menu.html" %><br/><br/><br/><br/><br/><br/>
<div align="center">
<form action="courseDeletion" method="post">
  <h1>Select Course : </h1>       <select name="course">
            <c:forEach var="item" items="${model}">
             <option value='${item.name}'>${item.name}</option>
            </c:forEach>
          </select>
          <input type="submit" value="Delete"/>
</form>
</div>
</body>
</html>