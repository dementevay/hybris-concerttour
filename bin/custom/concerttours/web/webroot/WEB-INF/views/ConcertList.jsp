<!-- Hybris123SnippetStart BandList.jsp -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
    <title>Concert List</title>
    <body>
        <h1>Concert List</h1>
     <ul>
     <c:forEach var="concerts" items="${concerts}">
        <li><a href="../concerts/${concerts.id}">${concerts.venue}</a></li>
      </c:forEach>
      </ul>
    </body>
</html>
<!-- Hybris123SnippetEnd -->