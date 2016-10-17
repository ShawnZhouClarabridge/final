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
    <form method="POST" action="uploadFile" enctype="multipart/form-data">
        Select Trainer
        <select name="trainer">
            <option>--- Select Trainer ---</option>
            <c:forEach var="item" items="${trainersArray}">
                <option value='${item.name}'>${item.name}</option>
            </c:forEach>
        </select>
        <h3 align="center" style="color: red"> ${msgT} </h3>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        File to upload: <input type="file" name="file">
        <h3 align="center" style="color: red"> ${msgF} </h3>
        <input type="submit" value="Upload"> Press here to upload the file!
    </form>
</div>

</body>
</html>
