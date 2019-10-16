<!-- Hybris123SnippetStart BandDetails.jsp -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<title>Song Details</title>
<body>
    <h1>Song Details</h1>
    Details for song ${song.name}
    <p>Album:</p>
<%--            <li><p>${album.name}</p></li>--%>
        <p>Lyrics:</p>
    <ul>
        <li><p>${song.lyrics}</p></li>
    </ul>
    <p>Duration:</p>
    <ul>
        <li><p>${song.songDuration} seconds</p></li>
    </ul>
    <a href="../concerts/${concert.id}">Back to Concert List</a>
</body>
</html>
<!-- Hybris123SnippetEnd -->