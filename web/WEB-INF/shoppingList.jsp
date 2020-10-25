<%-- 
    Document   : shoppingList
    Created on : 25-Oct-2020, 4:29:38 PM
    Author     : ethan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        Hello, ${username} <a href="<c:url value='ShoppingList?action=logout'/>">Logout</a>

        <h2>List</h2>
        <form action="ShoppingList" method="post">
            Add item: <input type="text" name="item">
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add">
        </form>

        <form action="ShoppingList" method="post">
            <ul>
                <c:forEach items="${shoppingList}" var="item">
                    <li><input type="radio" name="items" value="${item}">${item}</li>
                </c:forEach>
            </ul>
            <input type="hidden" name="action" value="delete">
            <input type="submit" value="Delete">
        </form>
    </body>
</html>
