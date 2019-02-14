<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<table>
    <tr>
        <td><a href="meals?action=create">Добавить запись</a></td>


    </tr>

</table>
<table border="1">
    <thead>
    <th>Дата</th>
    <th>Описание</th>
    <th>Калории</th>
    </thead>

    <c:forEach var="meal" items="${mealToList}">
        <tr bgcolor="${meal.excess? "#ff0000" : "#00ff00"}">


            <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
            <td><fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}"/></td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}">Редакт.</a>
                <a href="meals?action=delete&id=${meal.id}">Удал.</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>