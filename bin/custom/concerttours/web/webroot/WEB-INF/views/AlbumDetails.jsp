<!-- Hybris123SnippetStart BandDetails.jsp -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<title>Album Details</title>
<body>
    <h1>Album Details</h1>
    Album Details for ${album.name}


    <p>Songs:</p>
    <ul>
        <c:forEach var="song" items="${songs}">
            <li><a href="../songs/${song.id}">${song.name}</a>
                    (on ${fn:length(song.concerts)} concerts)
                <ul>
            <c:forEach var="concert" items="${song.concerts}">
                    <li>${concert.venue}</li>
            </c:forEach>
                </ul>
                <br/>
            </li>
        </c:forEach>
    </ul>
    <a href="../concerts/${song.id}">Back to Concert List</a>
</body>
</html>
<!-- Hybris123SnippetEnd -->