<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Course</title>
</head>
<body><%@ include file="menu.html" %><br/><br/><br/><br/><BR/><BR/><BR/><br/><br/></br></br>
<h1><center>Select course to generate yearly chart</center></h1><br/>
<div align="center">
<form method="post" action="Barchart">
<select name="course">
            <c:forEach var="item" items="${courses}">
             <option value='${item}'>${item}</option>
            </c:forEach>
          </select>
          <select name="year">
            <option value='2014'>2014</option>
<option value='2013'>2013</option>
<option value='2012'>2012</option>
<option value='2011'>2011</option>
<option value='2010'>2010</option>
<option value='2009'>2009</option>
          </select>
          <input type="submit" value="Generate Graph"/>

</form>
</div>

</body>
</html>