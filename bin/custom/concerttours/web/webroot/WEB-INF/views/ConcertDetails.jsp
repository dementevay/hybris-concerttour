<!-- Hybris123SnippetStart BandDetails.jsp -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<title>Concert Details</title>
<body>
    <h1>Concert Details</h1>
    Concert Details for ${concert.venue}
    <p>${band.description}</p>
    <p>Songs:</p>
    <ul>
        <c:forEach var="song" items="${songs}">
            <li><a href="../songs/${song.id}">${song.name}</a></li>
        </c:forEach>
    </ul>


    <p>Hits of concert:</p>
        <ul>
            <c:forEach var="song" items="${hits}">

                <li><a href="../songs/${song.id}">${song.name}</a></li></td><td>
                <ul>
                    <c:forEach var="concert" items="${song.concerts}">
                        <li><a href="../concerts/${concert.id}">${concert.venue}</a></li>
                    </c:forEach>
                </ul>
        </c:forEach>
        </ul>





    <p><b> ${countAlbums} </b> songs albums:</p>
    <ul>
        <c:forEach var="album" items="${albums}">
            <li><a href="../albums/${album.id}">${album.name}</a></li>
        </c:forEach>
    </ul>
<%--    <p>Number of albums:</p>--%>
<%--    <li>${countAlbums}</li>--%>

    <p>Top-3 authors:</p>
    <ul>
        <c:forEach var="author" items="${authors}">
            <li>${author.name}</li>
        </c:forEach>
    </ul>
    <p>Top-3 compositors:</p>
    <ul>
        <c:forEach var="compositor" items="${compositors}">
            <li>${compositor.name}</li>
        </c:forEach>
    </ul>

    <p>Quantity of untypical Songs: ${untypicalSongs}</p>
    <br/>

    <a href="../tours/201701">Back to Tour Details</a>
</body>
</html>
<!-- Hybris123SnippetEnd -->