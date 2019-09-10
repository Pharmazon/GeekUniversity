<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="fragments/header.jsp">
    <jsp:param name="title" value="Product" />
</jsp:include>

<form action="<c:url value="${pageContext.request.contextPath}/product-save"/>" method="post">
    <p>
        <button type="submit">SUBMIT</button>
    </p>

    <table cellspacing="0" cellpadding="0" border="0" style="margin-bottom: 5px">
        <tr>
            <th width="100" align="left" nowrap="nowrap">CAPTION</th>
            <th width="300" align="left" nowrap="nowrap">VALUE</th>
        </tr>
        <tr>
            <td align="left" style="padding-right: 10px">ID</td>
            <td>
                <c:out value="${product.id}" />
                <input type="hidden" name="id" value="${product.id}"/>
            </td>
        </tr>
        <tr>
            <td align="left" style="padding-right: 10px">NAME</td>
            <td>
                <input type="text" name="name" class="inputField" value="<c:out value="${product.name}"/>" />
            </td>
        </tr>
    </table>
</form>

<jsp:include page="fragments/footer.jsp" />
