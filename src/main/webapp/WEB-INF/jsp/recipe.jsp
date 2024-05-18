<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>Recipe</title>--%>
<%--    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">--%>
<%--</head>--%>

<%--<body>--%>
<%--<div class="recipe_container">--%>

<%--    <div class="slider-container">--%>
<%--        <a href="/Home">--%>
<%--            <div class="slide">--%>
<%--                <img src="/images/image8.png" alt="Image 1">--%>
<%--            </div>--%>
<%--            <div class="slide">--%>
<%--                <img src="/images/image9.png"alt="Image 2">--%>
<%--            </div>--%>
<%--            <div class="slide">--%>
<%--                <img src="/images/image10.png" alt="Image 3">--%>
<%--            </div>--%>
<%--        </a>--%>
<%--    </div>--%>

<%--    <script>--%>
<%--        let slideIndex = 0;--%>
<%--        const slides = document.querySelectorAll('.slide');--%>

<%--        function showSlides() {--%>
<%--            slides.forEach(slide => {--%>
<%--                slide.style.display = 'none';--%>
<%--            });--%>
<%--            slideIndex++;--%>
<%--            if (slideIndex > slides.length) {--%>
<%--                slideIndex = 1;--%>
<%--            }--%>
<%--            slides[slideIndex - 1].style.display = 'block';--%>
<%--            setTimeout(showSlides, 3000); // 3초마다 이미지 전환--%>
<%--        }--%>

<%--        showSlides();--%>
<%--    </script>--%>
<%--    <div class="recipe_header">--%>
<%--        <h1>${recipeInfo.title}</h1>--%>
<%--    </div>--%>
<%--    <div class="recipe_img">--%>
<%--        <img src="${recipeInfo.image}" alt="${recipeInfo.title}">--%>
<%--    </div>--%>

<%--    <table class="recipe_table">--%>
<%--        <tr>--%>
<%--            <td class="recipe_ingredients">--%>
<%--                <ul class="ul_">--%>
<%--                    <h2>Ingredients:</h2>--%>
<%--                    <c:forEach items="${recipeInfo.ingredients}" var="ingredient">--%>
<%--                        <li>${ingredient}</li>--%>
<%--                    </c:forEach>--%>
<%--                </ul>--%>
<%--            </td>--%>
<%--            <td class="recipe_ingredients">--%>
<%--                <h2>Nutrition:</h2>--%>
<%--                <ul class="ul_">--%>
<%--                    <li>Calories : ${recipeInfo.calories}kcal</li>--%>
<%--                    <li>Carbohydrates : ${recipeInfo.carbs}</li>--%>
<%--                    <li>Fats : ${recipeInfo.fat}</li>--%>
<%--                    <li>Proteins : ${recipeInfo.protein}</li>--%>
<%--                </ul>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--    <div class="nutrition">--%>
<%--        <h2>Instructions:</h2>--%>
<%--        <p>${recipeInfo.instructions}</p>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Recipe</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/recipe.css"/>">
</head>
<body>
<div class="recipe_container">

    <div class="slider-container">
        <a href="/Home">
            <div class="slide">
                <img src="/images/image8.png" alt="Image 1">
            </div>
            <div class="slide">
                <img src="/images/image9.png" alt="Image 2">
            </div>
            <div class="slide">
                <img src="/images/image10.png" alt="Image 3">
            </div>
        </a>
    </div>

    <script>
        let slideIndex = 0;
        const slides = document.querySelectorAll('.slide');

        function showSlides() {
            slides.forEach(slide => {
                slide.style.display = 'none';
            });
            slideIndex++;
            if (slideIndex > slides.length) {
                slideIndex = 1;
            }
            slides[slideIndex - 1].style.display = 'block';
            setTimeout(showSlides, 3000); // 3초마다 이미지 전환
        }

        showSlides();
    </script>

    <div class="recipe_header">
        <h1>${recipeInfo.title}</h1>
    </div>

    <div class="recipe_content">
        <div class="recipe_img">
            <img src="${recipeInfo.image}" alt="${recipeInfo.title}">
        </div>
        <div class="recipe_ingredients">
            <h2>Ingredients:</h2>
                <ul class="ul_">
                    <c:forEach items="${recipeInfo.ingredients}" var="ingredient">
                        <li>${ingredient}</li>
                    </c:forEach>
                </ul>
        </div>
        <div class="recipe_new">
            <h2>Nutrition:</h2>
            <td>
                <ul class="ul_">
                    <li>Calories : ${recipeInfo.calories}kcal</li>
                    <li>Carbohydrates : ${recipeInfo.carbs}</li>
                    <li>Fats : ${recipeInfo.fat}</li>
                    <li>Proteins : ${recipeInfo.protein}</li>
                </ul>
           </td>
        </div>
    </div>

    <div class="recipe_instructions">
        <h2>Instructions:</h2>
        <p>${recipeInfo.instructions}</p>
    </div>

</div>
</body>
</html>