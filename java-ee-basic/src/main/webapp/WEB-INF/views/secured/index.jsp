<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Super app!</title>
    </head>

    <body>
        <div>
            <h1>Super app!</h1>
        </div>

        <p>Hello, ${username}!</p>

        <button onclick="location.href='<c:url value="/logout"/>'">Logout</button>

        <div>
            <div>
                <button onclick="location.href='<c:url value="/secured/list"/>'">List users</button>
                <button onclick="location.href='<c:url value="/secured/add"/>'">Add user</button>
            </div>
        </div>
    </body>
</html>
