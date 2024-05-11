<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Recipe</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
</head>

<body>
<div class="recipe_container">

    <div class="recipe_header">

        <div class="home_button">
            <a href="home.jsp"><button type="button">Home</button></a>
        </div>

        <h1>${recipeInfo.title}</h1>
    </div>

    <div class="recipe_img">
        <img src="${recipeInfo.image}" alt="${recipeInfo.title}">
    </div>

    <div class="recipe_ingredients">
        <h2>Ingredients:</h2>
        <ul>
            <c:forEach items="${recipeInfo.ingredients}" var="ingredient">
                <li>${ingredient}</li>
            </c:forEach>
        </ul>
    </div>

    <div class="recipe_instructions">
        <h3>Instructions:</h3>
        <p>${recipeInfo.instructions}</p>
    </div>
</div>
</body>
</html>