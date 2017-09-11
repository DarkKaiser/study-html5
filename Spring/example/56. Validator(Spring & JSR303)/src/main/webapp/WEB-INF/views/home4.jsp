<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%> 
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<form:form commandName="userJSR303">
<table>
	<tr>
	    <td> Name :</td>
	    <td><form:input path="name" /></td>
	    <td><form:errors path="name" /></td>
	</tr>
</table>
</form:form>

</body>
</html>
