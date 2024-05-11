<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <c:if test="${not empty searchResults}">
        <h2>Search Results</h2>
        <ul>
                <%-- 각 검색 결과를 반복하여 표시 --%>
            <c:forEach var="result" items="${searchResults}">
                <li>${result.title}</li>
            </c:forEach>
        </ul>
    </c:if>
</div>
</body>
</html>
