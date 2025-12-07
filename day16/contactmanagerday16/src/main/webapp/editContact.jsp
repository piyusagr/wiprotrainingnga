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

<h2>Edit Contact</h2>

<form action="contact" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${contact.id}">

    Name: <input type="text" name="name" value="${contact.name}"><br><br>
    Phone: <input type="text" name="phone" value="${contact.phone}"><br><br>
    Email: <input type="text" name="email" value="${contact.email}"><br><br>

    <input type="submit" value="Update Contact">
</form>

<a href="contact?action=view">Back</a>

</body>
</html>