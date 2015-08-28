<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>

<h1><spring:message code="label.autorization" /></h1>

<div id="login-error">${error}</div>

<form action="j_spring_security_check" method="post" >
	<table>
		<tr>
			<td><label for="j_username"><spring:message code="label.login" /></label></td>
			<td><input id="j_username" name="j_username" type="text" /></td>
		</tr>
		<tr>
			<td><label for="j_password"><spring:message code="label.password" /></label></td>
			<td><input id="j_password" name="j_password" type="password" /></td>
		</tr>
		<tr>
			<td><input  type="submit" value="Login"/></td>								
		</tr>
	</table>	
</form>
<div><a href="userRegistration">Registration</a></div>

</body>
</html>