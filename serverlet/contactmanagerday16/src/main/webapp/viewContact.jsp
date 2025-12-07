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

<h2>Contact List</h2>

<table border="1" cellpadding="8">
    <tr>
        <th>ID</th><th>Name</th><th>Phone</th><th>Email</th><th>Action</th>
    </tr>

    <c:forEach items="${contacts}" var="c">
        <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td>${c.phone}</td>
            <td>${c.email}</td>
            <td>
                <a href="contact?action=edit&id=${c.id}">Edit</a>
                |
                <a href="contact?action=delete&id=${c.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<c:if test="${not empty message}">
    <p style="color:green">${message}</p>
</c:if>

<a href="index.jsp">Back</a>

</body>
</html>