<!-- Hybris123SnippetStart TourDetails.jsp -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html>
<title>Tour Details</title>
<body>
    <h1>Tour Details</h1>
    Tour Details for ${tour.tourName}
    <p>${tour.description}</p>
    <p>Schedule:</p>
    <table>
        <tr><th>Venue</th><th>Type</th><th>Date</th></tr>
        <c:forEach var="concert" items="${tour.concerts}">
            <tr><td><li><a href="../concerts/${concert.id}">${concert.venue}</a></li></td><td>${concert.type}</td><td><fmt:formatDate pattern="dd MMM yyyy" value="${concert.date}" /></td></tr>
        </c:forEach>
    </table>
    <a href="../bands">Back to Band List</a>
</body>
</html>
<!-- Hybris123SnippetEnd -->