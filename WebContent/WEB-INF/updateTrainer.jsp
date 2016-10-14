<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Trainer</title>
</head>
<body>
<%@ include file="menu.html" %><br/><br/><br/><br/><br/><br/>
<div align="center">
<form metod="post" action="modifyTrainer">
   Select Trainer       <select name="trainer">
   <option>---Select Trainer---</option>
            <c:forEach var="item" items="${model.trainers}">
             <option value='${item}'>${item}</option>
            </c:forEach>
          </select>
          <input type="submit" value="Update"/>
</form>
</div><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<form  method="POST" action="trainerUpdated">
<table style="height:100%;width:100%">
	<tr>
	<td  style="height:100%;width:25%">
	</td>
	<td style="height:100%;width:50%"> 
		<fieldset style="background:white">
		<legend>Update Trainer</legend><br/>
		<div class="divv">
				<label for="cname">Trainer Name</label>
				<input type="text" name="tname"  value="${model.name}"class="textbox"/>
			</div><br/>
			<div class="divv">
				<label for="cname">Experience &nbsp;&nbsp;</label>
				<input type="text" name="texp"  value="${model.exp}"class="textbox"/>
			</div><br/>
			<div class="divv">
				<label for="cid">Email Id</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" name="temail"   value="${model.email}" class="textbox"/>
			</div><br/>
			<div class="divv">
				<label for="cprice">Phone &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </label>&nbsp;
				 <input type="text" name="tphone" value="${model.phone}"  class="textbox"/>
			</div><br/>
			<div align="center" class="divv">
				<input type="submit" value="Submit" class="btn"/><br/>
				
			</div>
		</fieldset>
		</td>
		<td style="height:100%;width:25%">
		</td>
		</tr>
		</table>
		
	</form>
</body>
</html>