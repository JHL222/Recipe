<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recipe Details</title>
</head>
<body>
    <h1>Recipe Details</h1>
    <h2>${recipeInfo.title}</h2>
    <img src="${recipeInfo.image}" alt="Recipe Image">
    <p>${recipeInfo.instructions}</p>
</body>
</html>
