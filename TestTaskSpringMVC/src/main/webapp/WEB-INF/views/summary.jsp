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
	<title>Task Summary</title>
</head>

<body>
<div id="main_wrapper">

<h3>Task Summary</h3>

<ul>
    <table>
	<c:forEach items="${tasks}" var="task">
	    <tr>
		<td><li><a href="taskDetails?entityId=${task.entityId}">${task.name}</a></li></td>
		<security:authorize ifAllGranted="ROLE_ADMIN">
		<td><a href="deleteTask?entityId=${task.entityId}">[delete]</a></td>
		</security:authorize>
	    </tr>
	</c:forEach>
    </table>		
</ul>

</div>
</body>

</html>
