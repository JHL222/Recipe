<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Fetch Recipes</title>
</head>
<body>
<h1>Fetch Recipes</h1>

<%-- 레시피 목록이 있을 경우에만 반복문 실행 --%>
<c:if test="${not empty recipes}">
    <%-- 레시피 목록을 반복하여 표시하는 반복문 --%>
    <c:forEach var="recipe" items="${recipes}">
        <div class="recipe">
            <h2>${recipe.title}</h2>
            <p>ID: ${recipe.id}</p>
        </div>
    </c:forEach>
</c:if>

<%-- 레시피 목록이 없는 경우에는 메시지 표시 --%>
<c:if test="${empty recipes}">
    <p>No recipes found.</p>
</c:if>

</body>
</html>
