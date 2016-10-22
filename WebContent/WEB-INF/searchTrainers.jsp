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
    <form method="POST" action="searchTrainersResult" enctype="multipart/form-data">
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
    <h1>Trainers</h1>
    <table border="1" >
        <tr><th>Trainer Name</th><th>Experience</th><th>Email</th><th>Phone</th></tr>
        <c:forEach var="trainer" items="${data}">
            <tr>
                <td><c:out value="${trainer.name}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td><c:out value="${trainer.experience}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td><c:out value="${trainer.email}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td><c:out value="${trainer.phone}"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
