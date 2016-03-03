<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name='viewport' content="width=device-width, initial-scale=1" />
	<!--link rel="stylesheet" href="<c:url value="/test/styles/style.css"/>" type="text/css"/-->
	<title>Task Details</title>
</head>

<body>
<div id="main_wrapper">

<h3>Task Details</h3>

<table>
	<tr><td>
		<table>
			<tr>
				<td>Task:</td>
				<td>${task.entityId}</td>
			</tr>
			<tr>
				<td>Name:</td>
				<td>${task.name}</td>
			</tr>
			<tr>
				<td>Status:</td>
				<td>${task.status}</td>
			</tr>
		</table>
	</td></tr>

</table>

<security:authorize ifAllGranted="ROLE_ADMIN">
	<p><a href="editTask?entityId=${task.entityId}">Edit Task</a></p>
</security:authorize>

</div>
</body>

</html>
