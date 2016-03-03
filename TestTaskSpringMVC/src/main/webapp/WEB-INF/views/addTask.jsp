<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name='viewport' content="width=device-width, initial-scale=1" />
	<script src='/test/js/dtmf_play.js'></script>
	<title>Add Task</title>
</head>

<body>
<div id="main_wrapper">

<h3>Add Task</h3>

<form:form commandName="task">
	<table>
	        <tr>
			<td>Name:</td>
			<td><form:input path="name"/></td>
			<td><form:errors path="name"/></td>
		</tr>
		<tr >
			<td colspan="3"><form:input path="status" type="hidden"/></td>
		</tr>		

		<tr>
			<td colspan="3"><input type="submit" value="Add Task" onmousedown="dialTone(941.0, 1209.0)" onmouseup="stop()" data-freq='941.0, 1209.0'/></td>
		</tr>
	</table>
</form:form>
<input type="submit" value="Play" onmousedown="playDialtoneString('1234567890*#ABC')" data-freq='941.0, 1209.0'/>

</div>
</body>

</html>
