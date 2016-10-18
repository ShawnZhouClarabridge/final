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
    <form method="POST" action="downloadResumeFile" enctype="multipart/form-data">
        Select Trainer
        <select name="resume">
            <option>--- Select Trainer ---</option>
            <c:forEach var="item" items="${resumesArray}">
                <option value='${item.fileName}'>${item.trainerName}</option>
            </c:forEach>
        </select>
        <%--<h3 align="center" style="color: red"> ${msgT} </h3>--%>
        <%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
        <%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
        <%--File to upload: <input type="file" name="file">--%>
        <%--<h3 align="center" style="color: red"> ${msgF} </h3>--%>
        <input type="submit" value="Download"> Press here to upload the file!
    </form>
</div>

</body>
</html>
