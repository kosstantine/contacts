<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Show phones</title>
</head>
<body>
<h1 style="text-align: center">Phones</h1>
<a href="<c:url value='/add-phone-${contact.id}'/>">Add new phone</a>
<br/>
<a href="<c:url value='/'/>">Show contacts</a>
<table style="width: 100%; text-align:center">
    <thead style="background:#b0c4de">
    <tr>
        <th>Type</th>
        <th>Number</th>
        <th colspan="3"></th>
    </tr>
    </thead>
    <tbody style="background:#e6e6e6">
    <c:forEach items="${phones}" var="phone">
        <tr>
            <td>${phone.type}</td>
            <td>${phone.number}</td>
            <td><a href="<c:url value='/set-base-${contact.id}-${phone.id}'/>">Set base</a></td>
            <td><a href="<c:url value='/delete-phone-${contact.id}-${phone.id}'/>">Delete</a></td>
            <td><a href="<c:url value='/edit-phone-${contact.id}-${phone.id}'/>">Edit</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
