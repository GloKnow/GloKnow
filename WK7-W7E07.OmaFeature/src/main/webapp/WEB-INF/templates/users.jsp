<head><title>GoNow</title>></head>
<body>
    <p>And the users are:</p>
    <ol>
        <c:forEach var="user" items="${list}">
            <li>${person.firstName}</li>
        </c:forEach>
    </ol>
</body>
