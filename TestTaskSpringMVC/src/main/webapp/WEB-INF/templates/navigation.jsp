<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div id="navigation">
    <table><tr>
        <td><li>
            <a href="../" title="Home page">Home page</a>
        </li></td>	    
        <td><li>
            <a href="taskSummary" title="Task summary">Task summary</a>
	</li></td>
        <td><li>
            <a href="addTask" title="Add task">Add task</a>
	</li></td>
<security:authorize ifAnyGranted="ROLE_MEMBER,ROLE_ADMIN">
        <td><li>
            <a href="<c:url value="/j_spring_security_logout"/>">Logout</a> (<security:authentication property="principal.username"/>)
        </li></td> 
</security:authorize>
    </tr></table>
</div>