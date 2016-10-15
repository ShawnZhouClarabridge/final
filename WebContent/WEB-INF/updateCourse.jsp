<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Update Course</title>
</head>
<body>
<%@ include file="menu.html" %>
<br/><br/><br/><br/><br/><br/>
<div align="center">
    <form metod="post" action="modifyCourse">
        Select Course <select name="course">
        <option>--- Select Course ---</option>
        <c:forEach var="item" items="${coursesArray}">
            <option value='${item.name}'>${item.name}</option>
        </c:forEach>
    </select>
        <input type="submit" value="Update"/>
    </form>
</div>

<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>

<form:form method="POST" action="courseUpdated" commandName="courses">
    <table style="height:100%;width:100%">
        <tr>
            <td style="height:100%;width:25%">
            </td>
            <td style="height:100%;width:50%">
                <fieldset style="background:white">
                    <legend>Update Course</legend>
                    <h3 align="center" style="color: red"> ${msg} </h3>
                    <div class="divv">
                        <form:label path="name">Course Name</form:label>&nbsp;&nbsp;&nbsp;
                        <form:input path="name" name="name" type="text" class="textbox"/>
                        <form:errors style="color: red" path="name" cssClass="error"></form:errors>

                    </div>
                    <div class="divv">
                        <form:label path="id">Course ID</form:label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <form:input path="id" name="id" type="text" class="textbox"/>
                        <form:errors style="color: red" path="id" cssClass="error"></form:errors>
                    </div>
                    <div class="divv">
                        <form:label path="price">Course Price</form:label>&nbsp;&nbsp;&nbsp;&nbsp;
                        <form:input path="price" name="price" type="int" class="textbox"/>
                        <form:errors style="color: red" path="price" cssClass="error"></form:errors>
                    </div>
                    <div class="divv">
                        <form:label path="sessions">Course Session</form:label>
                        <form:input path="sessions" name="sessions" type="int" class="textbox"/>
                        <form:errors style="color: red" path="sessions" cssClass="error"></form:errors>
                    </div>
                    <br/>
                    <div align="center" class="divv">
                        <input type="submit" name="add" value="Submit" class="btn"/>
                    </div>
                </fieldset>
            </td>
            <td style="height:100%;width:25%">
            </td>
        </tr>
    </table>

</form:form>
-->
</body>
</html>