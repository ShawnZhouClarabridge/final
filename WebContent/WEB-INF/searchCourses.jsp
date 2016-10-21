<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Upload Resume Page</title>
</head>
<body>
<%@ include file="menu.html" %>
<br/><br/><br/><br/><br/><br/>
<div align="center">
    <h3 align="center" style="color: red"> ${msg} </h3>
    <form method="POST" action="searchCoursesResult" enctype="multipart/form-data">
        Select Trainer
        <select name="searchBy">
            <option>--- Search By ---</option>
            <c:forEach var="item" items="${filedsArray}">
                <option value='${item}'>${item}</option>
            </c:forEach>
        </select>
        <input name="searchValue" type="text" class="textbox"/>
        <input type="submit" value="Search"> Press here to search!
    </form>
</div>

<div id="table-scroll" style="height:200px;width:300px;position:absolute;left:600px;top:150px">
    <h1> Courses </h1>
    <table border="1" align="left" >
        <tr><th>Course Name</th><th>Course Id</th><th>Price</th></tr>
        <c:forEach var="course" items="${result}">
            <tr>
                <td><c:out value="${course.name}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td><c:out value="${course.id}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td><c:out value="${course.price}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
