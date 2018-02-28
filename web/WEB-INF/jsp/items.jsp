<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 23.03.2017
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="translations"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Andrey Shop</title>
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/shop-homepage.css" rel="stylesheet">
    <script src="../../js/itemList.js" async></script>
    <script src="../../js/jquery.js"></script>
</head>
<body>
<c:forEach var="item" items="${requestScope.list}">
    <div style="display: block;" class="row block ">
        <div class="col-md-12">
            <div class="thumbnail">
                <div class="row">
                    <div class="col-md-3">
                        <img src="${item.image}" class="img-responsive" alt="">
                    </div>
                    <div class="col-md-9">
                        <div class="caption-full">
                            <h4 class="pull-right">$${item.price}</h4>
                            <h4><a href="/shop-item?id=${item.id}">${item.name}</a>
                            </h4>
                            <p>${item.description}</p>
                            <a class="btn btn-primary" href="/shop-item?id=${item.id}"><fmt:message
                                    key="index.more"/>
                                <span class="glyphicon glyphicon-chevron-right"></span></a>
                            <c:if test="${sessionScope.userData != null}">
                                <c:choose>
                                    <c:when test="${item.remainingAmount=='0'}">
                                        <p class="pull-right"><span>
                                                        <fmt:message key="index.NotInStok"/>
                                                    </span></p>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="btn btn-link pull-right add_in_bag"
                                           href="/addToBag?itemId=${item.id}"><fmt:message
                                                key="index.addToBag"/>
                                            <span class="glyphicon glyphicon-chevron-right"></span></a>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</c:forEach>
<script src="../../js/bootstrap.min.js"></script>

</body>
</html>