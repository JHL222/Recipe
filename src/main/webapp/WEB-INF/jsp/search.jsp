<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Recipe Search</title>
</head>
<body>
<h1>Recipe Search</h1>
<form action="/search" method="GET">
    <label for="query">Search Recipes:</label>
    <input type="text" id="query" name="query" required>
    <button type="submit">Search</button>
</form>

<%-- 검색 결과를 표시할 부분 --%>
<div id="searchResults">
    <%-- 검색 결과가 있을 경우에만 반복문 실행 --%>
    <c:if test="${not empty recipes}">
        <h2>Search Results</h2>
        <ul>
                <%-- 각 검색 결과를 반복하여 표시 --%>
            <c:forEach var="recipe" items="${recipes}">
                <li>
                    <h3>${recipe.title}</h3>
                    <c:if test="${not empty recipe.image}">
                        <img width="300px" src="https://spoonacular.com/recipeImages/${recipe.image}" alt="${recipe.title}">
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </c:if>
</div>
</body>
</html>