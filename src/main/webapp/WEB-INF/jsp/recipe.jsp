<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Recipe</title>
</head>
<body>

<h1>${recipeInfo.title}</h1>
<img src="${recipeInfo.image}" alt="${recipeInfo.title}">

<h2>Ingredients:</h2>
<ul>
    <c:forEach items="${recipeInfo.ingredients}" var="ingredient">
        <li>${ingredient}</li>
    </c:forEach>
</ul>

<h2>Instructions:</h2>
<p>${recipeInfo.instructions}</p>

</body>
</html>
