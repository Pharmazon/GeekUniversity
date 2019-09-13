<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Authorization</title>
    </head>
    <body>

        <c:if test="${request.getRemoteUser() != null}">
            <button onclick="location.href='<c:url value="/logout"/>'">Logout</button>
        </c:if>

        <form method="post" action="j_security_check">
            <label>Username:
                <input type="text" name="j_username" value="user"><br />
            </label>

            <label>Password:
                <input type="password" name="j_password" value="1"><br />
            </label>
            <button type="submit">Войти</button>
        </form>
    </body>
</html>
