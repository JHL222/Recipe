<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recipe Search</title>
</head>
<body>
<div class="recipe">
    <h2>${recipeInfo.title}</h2>
    <p>ID: ${recipeInfo.id}</p>
    <p>Calories: ${recipeInfo.calories}</p>
    <p>Carbohydrates: ${recipeInfo.carbohydrates} g</p>
    <p>Protein: ${recipeInfo.protein} g</p>
    <p>Fat: ${recipeInfo.fat} g</p>
    <p>Instructions: ${recipeInfo.instructions}</p>
    <img src="${recipeInfo.image}" alt="Recipe Image">
</div>
</body>
</html>
