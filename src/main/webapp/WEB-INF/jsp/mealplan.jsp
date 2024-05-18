<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Meal Plan</title>
</head>
<body>
<div class="container">
    <h1>Meal Plan</h1>
    <div class="meal-plan">
        <h2>Recipe</h2>
        <p><strong>Title:</strong> ${recipes.title}</p>
        <p><strong>Ready In Minutes:</strong> ${recipes.readyInMinutes}</p>
        <p><strong>Servings:</strong> ${recipes.servings}</p>
        <h3>Ingredients</h3>
        <ul>
            <c:forEach items="${recipes.ingredients}" var="ingredient">
                <li>${ingredient}</li>
            </c:forEach>
        </ul>
        <h3>Nutrition</h3>
        <p><strong>Calories:</strong> ${recipes.calories}</p>
        <p><strong>Carbs:</strong> ${recipes.carbs}</p>
        <p><strong>Fat:</strong> ${recipes.fat}</p>
        <p><strong>Protein:</strong> ${recipes.protein}</p>
    </div>
</div>
</body>
</html>
