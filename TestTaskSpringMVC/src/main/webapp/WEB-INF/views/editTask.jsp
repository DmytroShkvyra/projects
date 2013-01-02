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
	<!--link rel="stylesheet" href="<c:url value="/styles/springsource.css"/>" type="text/css"/-->
	<title>Edit Task Details</title>
</head>

<body>
<div id="main_wrapper">

<h3>Edit Task Details</h3>

<form:form commandName="task" acceptCharset="UTF-8">
	<table>
		<tr>
			<td>ID:</td>
			<td>${task.entityId}</td>
			<td></td>
		</tr>		
	        <tr>
			<td>Name:</td>
			<td><form:input path="name"/></td>
			<td><form:errors path="name"/></td>
		</tr>
		<tr>
			<td>Status:</td>
			<td>
			    <security:authorize ifAllGranted="ROLE_MEMBER">
				<form:input path="status" />
			    </security:authorize>
			    <security:authorize  ifNotGranted="ROLE_MEMBER">
				${task.status}
				<form:input path="status" type="hidden"/>
			    </security:authorize>
			
			</td>
			<td><form:errors path="status"/></td>
		</tr>		
		<tr>
			<td colspan="3"><input type="submit" value="Update Task"/></td>
		</tr>
	</table>
</form:form>

</div>
</body>

</html>
