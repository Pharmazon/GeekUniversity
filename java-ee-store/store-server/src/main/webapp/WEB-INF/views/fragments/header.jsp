<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
    <%@include file="../../../resources/styles/styles.css"%>
</style>

<title>${param.get("title")}</title>

<div class="header">
    <div class="headerChild">
        <a href="<c:url value="/"/>">Home</a>
    </div>
    <div class="headerChild">
        <a href="catalog">Catalog</a>
    </div>
    <div class="headerChild">
        <a href="product">Products</a>
    </div>
    <div class="headerChild">
        <a href="cart">Cart</a>
    </div>
    <div class="headerChild">
        <a href="order">Orders</a>
    </div>
    <div class="headerChild">
        <a href="about">About</a>
    </div>
    <div class="headerChild">
        <a href="${pageContext.request.contextPath}/login">Administrator</a>
    </div>
</div>

<div class="content">
    <h1>${param.get("title")}</h1>

