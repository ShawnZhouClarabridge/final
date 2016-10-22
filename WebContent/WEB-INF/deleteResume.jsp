<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Delete Course</title>
</head>
<body>
<%@ include file="menu.html" %>
<br/><br/><br/><br/><br/><br/>
<div align="center">
    <h3 align="center" style="color: red"> ${msg} </h3>
    <form action="selectTrainer" method="post">
        <h1>Select trainer : </h1>
        <select name="trainer">
            <option>${selectedTrainer}</option>
            <c:forEach var="item" items="${model}">
                <option value= '${item.trainerName}'>${item.trainerName}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Select"/>
    </form>
</div>
<div align="center">
    <form action="deletedResume" method="post" enctype="multipart/form-data">
        <h1>Select resume : </h1>
        <select name="resume">
            <c:forEach var="item" items="${curList}">
                <option value= '${item.fileName}'>${item.fileName}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Delete"/>
    </form>
</div>
</body>
</html>