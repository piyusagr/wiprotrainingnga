<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Add Contact</h2>

<form action="contact" method="post">
    <input type="hidden" name="action" value="add">
    Name: <input type="text" name="name"><br><br>
    Phone: <input type="text" name="phone"><br><br>
    Email: <input type="text" name="email"><br><br>

    <input type="submit" value="Add Contact">
</form>

<c:if test="${not empty message}">
    <p style="color:green">${message}</p>
</c:if>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

<br>
<a href="index.jsp">Back</a>

</body>
</html>