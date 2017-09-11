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

<form:form commandName="user">
<table>
	<tr>
	    <td> Name :</td>
	    <td><form:input path="name" /></td>
	    <td><form:errors path="name" /></td>
	</tr>
	<tr>
	    <td> 전화번호 :</td>
	    <td><form:input path="telephone" /></td>
	    <td><form:errors path="telephone" /></td>
	</tr>
</table>
</form:form>

</body>
</html>
