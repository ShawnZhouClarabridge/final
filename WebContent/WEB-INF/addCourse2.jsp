
<html>
	<head>
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
	<title>New Course</title>
<body bgcolor="#dddddd">

<%@ include file="menu.html" %><br/><br/>
<form  method="POST" action="subCourse">
<table style="height:100%;width:100%">
	<tr>
	<td  style="height:100%;width:25%">
	</td>
	<td style="height:100%;width:50%"> 
		<fieldset style="background:white">
		<legend>New Course</legend>
			<div class="divv">
				<label for="cname">Course Name</label>
				<input type="text" name="name"  class="textbox"/>
			</div>
			<div class="divv">
				<label for="cid">Course Id</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" name="id"  class="textbox"/>
			</div>
			<div class="divv">
				<label for="cprice">Course Price  </label>&nbsp;
				 <input type="text" name="price"  class="textbox"/>
			</div><br/>
			<div align="center" class="divv">
				<input type="submit" value="Submit" class="btn"/>
				
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