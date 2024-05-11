<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
</head>
<body>
<header>
    <div class='topbar'>
        <div class='logo'>

        </div>
        <div class='button'>
            <button class='login'>Login</button>
        </div>
    </div>
</header>

<main>
    <form action="/search" method="GET">
        <div class="searchbar">
            <input type="text" name="query" placeholder="Search recipes..." required>
            <button type="submit">Search</button>
        </div>
    </form>

    <div id="main">
        <c:if test="${not empty recipes}">
            <div class="menuDiv">
            <h2>Main</h2>
                <c:forEach var="recipe" items="${recipes}">
                    <div class="recipe">
                        <c:if test="${not empty recipe.image}">
                            <img src="https://spoonacular.com/recipeImages/${recipe.id}-480x360.jpg" class="menuImg" />
                        </c:if>
                        <h3 class="menuTitle">${recipe.title}</h3>
                    </div>
                </c:forEach>
            </div>
        </c:if>
    </div>
</main>
</body>
</html>