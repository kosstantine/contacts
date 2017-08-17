<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Edit phone</title>
</head>
<body>
<h1 style="text-align: center">Edit phone</h1>
<form:form modelAttribute="phone" method="POST" action="/edit-phone-${contact.id}-${phone.id}">
    <table style="margin: 0 auto">
        <tbody>
        <tr>
            <td>Phone:</td>
            <td>
                <form:select path="type" items="${types}"/>
            </td>
            <td><form:label path="number"/></td>
            <td><form:input path="number"/></td>
            <td><form:errors path="number"/></td>
            <td><p align="center"><input type="submit" value="Save"></p></td>
        </tr>
        </tbody>
    </table>
</form:form>
</body>
</html>
