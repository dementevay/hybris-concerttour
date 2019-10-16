<!-- Hybris123SnippetStart BandListWithMedia.jsp -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
    <title>Song List</title>
    <body>
        <h1>Song List</h1>
     <ul>
     <c:forEach var="song" items="${songs}">
        <li><a href="./songs/${song.id}"><img src="${song.imageURL}" />${song.authors}</a></li>
      </c:forEach>
      </ul>
    </body>
</html>
<!-- Hybris123SnippetEnd -->