<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
    <title>Recipe Search</title>
</head>
<body>

<div class="slider-container">
    <a href="/Home">
        <div class="slide">
            <img src="/images/image8.png" alt="Image 1">
        </div>
        <div class="slide">
            <img src="/images/image9.png"alt="Image 2">
        </div>
        <div class="slide">
            <img src="/images/image10.png" alt="Image 3">
        </div>
    </a>
</div>
<div id="category">
    <a href="/search?cuisine=korean">Korean</a>
    <a href="/search?cuisine=japanese">Japanese</a>
    <a href="/search?cuisine=italian">Italian</a>
    <a href="/search?cuisine=mexican">Mexican</a>
    <a href="/search?diet=vegan">Vegan</a>
    <a href="/search?intolerances=gluten">Gluten Free</a>
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

<main class="main">
    <form action="/search" method="GET">
        <div class="searchbar">
            <input type="text" name="query" placeholder="Search recipes...">
            <button type="submit">Search</button>
        </div>
    </form>

    <h2>Main</h2>
    <div id="main">
        <c:if test="${not empty recipes}">
            <div class="menuDiv">
                <c:forEach var="recipe" items="${recipes}">
                    <a href="recipe?recipeId=${recipe.id}">
                        <div class="recipe">
                            <c:if test="${not empty recipe.image}">
                                <img src="https://spoonacular.com/recipeImages/${recipe.id}-480x360.jpg" class="menuImg" />
                            </c:if>
                            <h3 class="menuTitle">${recipe.title}</h3>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </c:if>

        <c:if test="${totalPages > 1}">
            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="${pageContext.request.contextPath}/Home?page=${currentPage - 1}">Previous</a>
                </c:if>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="${pageContext.request.contextPath}/Home?page=${i}" class="${i == currentPage ? 'active' : ''}">${i}</a>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <a href="${pageContext.request.contextPath}/Home?page=${currentPage + 1}">Next</a>
                </c:if>
            </div>
        </c:if>
    </div>
</main>


</body>
</html>