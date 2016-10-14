<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
	 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
     <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
     <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script> 
     
     

  <style>

    .datepicker{
    }

  </style>

 

  <script>

  $(function() {

    $( ".datepicker" ).datepicker();

  });

  </script>
	
	
		  <style>
		.textbox
		{
			height:25px;
			width:240px;
			-moz-border-radius:7px; /* Firefox */
			-webkit-border-radius: 7px; /* Safari, Chrome */
			-khtml-border-radius: 7px; /* KHTML */
			border-radius: 7px; /* CSS3 */
		}
		td,label
		{
			padding:20px;
		}
		
		.btn
		{
			cursor:pointer; /*forces the cursor to change to a hand when the button is hovered*/
			padding:5px 25px; /*add some padding to the inside of the button*/
			background:#0450ab; /*the colour of the button*/
			border:1px solid #0bc0ef; /*required or the default border for the browser will appear*/
			/*give the button curved corners, alter the size as required*/
			-moz-border-radius: 10px;
			-webkit-border-radius: 10px;
			border-radius: 10px;
			/*give the button a drop shadow*/
			-webkit-box-shadow: 0 0 4px rgba(0,0,0, .75);
			-moz-box-shadow: 0 0 4px rgba(0,0,0, .75);
			box-shadow: 0 0 4px rgba(0,0,0, .75);
			/*style the text*/
			color:#f3f3f3;
			font-size:1.1em;
		}
		.btn:hover, .btn:focus
		{
			background-color :#012045; /*make the background a little darker*/
			/*reduce the drop shadow size to give a pushed button effect*/
			-webkit-box-shadow: 0 0 1px rgba(0,0,0, .75);
			-moz-box-shadow: 0 0 1px rgba(0,0,0, .75);
			box-shadow: 0 0 1px rgba(0,0,0, .75);
		}
		.divv
		{
			padding:10px;
		}
		
      </style>
	</head>
	<%@ include file="menu.html" %><br/><br/><br/><br/><br/><br/><br/>
<body bgcolor="#dddddd">
<form method="POST" action="calendarResult">
<table style="height:100%;width:100%">
	<tr>
	<td  style="height:100%;width:33%">
	</td>
	<td style="height:100%;width:34%">
		<fieldset style="background:white">
			<legend>New Calendar</legend>
			<div class="divv">
				<label for="name">Name: </label>
			<!--  	<select>
				<option>--Select Course--</option>
				<option>Course 1</option>
				<option>Course 2</option>
				<option>Course 3</option>
				<option>Course 4</option>
				</select>
				-->
				

           <select name="course">
            <c:forEach var="item" items="${list}">
             <option value='${item}'>${item}</option>
            </c:forEach>
          </select>
				
			</div>
			<div class="divv">
				<label for="email">Starting Date: </label>
				<input name="startdate" id="email"  class="datepicker" placeholder="YYYY-MM-DD">
			</div>
			<div class="divv">
				<label for="name">Ending Date: </label>&nbsp;&nbsp;
				<input name="enddate" id="name"  class="datepicker" placeholder="YYYY-MM-DD">
			</div>
			<div class="divv">
				<label for="name">Number of sessions: </label>
				<input name="sessions" id="name"  class="textbox">
			</div><br/>
			
			<div align="center" class="divv">
				<input type="submit" value="Submit" class="btn"/>
				
			</div>
		</fieldset>
		</td>
		<td style="height:100%;width:33%">
		</td>
		</tr>
		</table>
	</form>
	</body>
</html>