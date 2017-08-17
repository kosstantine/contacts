<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Edit contact</title>
</head>
<body>
<h1 style="text-align: center">Edit contact</h1>
<form:form modelAttribute="contact" method="POST" action="/edit-contact-${contact.id}">
    <table style="margin: 0 auto">
        <tbody>
            <tr>
                <td><form:label path="firstName">First name:</form:label></td>
                <td><form:input path="firstName"/></td>
                <td><form:errors path="firstName"/></td>
            </tr>
            <tr>
                <td><form:label path="lastName">Last name:</form:label></td>
                <td><form:input path="lastName"/></td>
                <td><form:errors path="lastName"/></td>
            </tr>
            <tr>
                <td><form:label path="email">E-male:</form:label></td>
                <td><form:input path="email"/></td>
                <td><form:errors path="email"/></td>
            </tr>
        </tbody>
    </table>
    <p align="center"><input type="submit" value="Save"></p>
</form:form>
</body>
</html>
