<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="fragments/header.jsp">
    <jsp:param name="title" value="Cart"/>
</jsp:include>

<%--<jsp:include page="fragments/menu.jsp">--%>
    <%--<jsp:param name="action" value="/project/product-create" />--%>
<%--</jsp:include>--%>

<table class="viewTable" cellspacing="0" cellpadding="0" border="1" style="margin-bottom: 5px">
    <tr>
        <th width="60" nowrap="nowrap">N</th>
        <th width="300" align="left" nowrap="nowrap">ID</th>
        <th width="300" align="left" nowrap="nowrap">NAME</th>
        <th nowrap="nowrap"></th>
        <th nowrap="nowrap"></th>
    </tr>

    <c:forEach var="product" items="${products}" varStatus="status">
        <tr>
            <td align="center">
                <c:out value="${status.index + 1}."/>
            </td>
            <td align="left">
                <c:out value="${product.id}"/>
            </td>
            <td align="left">
                <c:out value="${product.name}"/>
            </td>
            <td align="center">
                <a href="product-edit?id=${product.id}">EDIT</a>
            </td>
            <td align="center">
                <a href="product-remove?id=${product.id}">REMOVE</a>
            </td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="fragments/footer.jsp"/>
