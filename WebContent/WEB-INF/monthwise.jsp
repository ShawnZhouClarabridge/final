<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generate Graph Month Wise</title>
</head>
<body><%@ include file="menu.html" %><br/><br/><br/><br/><br/><br/><br/><br/>
<h1><center>Select Month to Generate Chart</center></h1>
<form method="get" action="forMonth">
<div align="center">
<select name="month">
<option value='01'>01</option>
<option value='02'>02</option>
<option value='03'>03</option>
<option value='04'>04</option>
<option value='05'>05</option>
<option value='06'>06</option>
<option value='07'>07</option>
<option value='08'>08</option>
<option value='09'>09</option>
<option value='10'>10</option>
<option value='11'>11</option>
<option value='12'>12</option>
</select>

<select name="year">
<option value='2014'>2014</option>
<option value='2013'>2013</option>
<option value='2012'>2012</option>
<option value='2011'>2011</option>
<option value='2010'>2010</option>
<option value='2009'>2009</option>

</select> &nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" value="Generate Graph"/>
</div>

</form>

</body>
</html>