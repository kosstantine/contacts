<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Contacts</title>
</head>
<body>
<h1 style="text-align: center">Contacts</h1>
<a href="<c:url value='/add-contact'/>">Add new contact</a>
</br>
<table style="width: 100%; text-align:center">
    <thead style="background:#b0c4de">
        <tr>
            <th>Id</th>
            <th>First name</th>
            <th>Last name</th>
            <th>E-mail</th>
            <th>Phone</th>
            <th colspan="3"></th>
        </tr>
    </thead>
    <tbody style="background:#e6e6e6">
        <c:forEach items="${contacts}" var="contact">
            <tr>
                <td>${contact.id}</td>
                <td>${contact.firstName}</td>
                <td>${contact.lastName}</td>
                <td>${contact.email}</td>
                <td>${contact.basePhone.number}</td>
                <td><a href="<c:url value='/show-phones-${contact.id}'/>" class="button">Show phones</a></td>
                <td><a href="<c:url value='/delete-contact-${contact.id}'/>">Delete</a></td>
                <td><a href="<c:url value='/edit-contact-${contact.id}'/>">Edit</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>