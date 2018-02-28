<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="translations"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Shopping Bag</title>
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/shop-homepage.css" rel="stylesheet">

    <script src="../../js/jquery.js"></script>
    <script src="../../js/bag.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body>
<jsp:include page="navbar.jsp"/>

<!-- Page Content -->
<div class="container">

    <div class="row">
        <div class="col-md-12">
            <c:forEach var="item" items="${pageContext.session.getAttribute('list')}">
                <div class="thumbnail">
                    <div class="row">
                        <div class="col-md-3">
                            <img src="${item.image}" class="img-responsive" alt="">
                        </div>
                        <div class="col-md-9">
                            <div class="caption ">
                                <h4 class="pull-right">$${item.price}</h4>
                                <h4>${item.name}</h4>
                                <p>${item.description}</p>
                                <p class="pull-right"><fmt:message key="bag.amount"/> <input type="number" min="1"
                                                                        max="${item.remainingAmount}"
                                                                        class="counter" value="1"
                                                                        data-price="${item.price}"
                                                                        data-id="${item.id}"> </p>
                            </div>
                        </div>
                    </div>
                    <a onclick="deleteItem(event)" href="#" style="position: absolute; right: 0px; top: 0"
                       class="glyphicon glyphicon-remove"></a>
                </div>
            </c:forEach>
        </div>
    </div>
    <c:if test="${pageContext.session.getAttribute('list') != null}">
        <div class="col-md-12 ">
            <button class="btn btn-danger pull-right" onclick="sendItems()"><fmt:message key="bag.checkout"/></button>
            <h4 class="pull-right"><span class="sum"><script>sum()</script>
        </span> $
            </h4>
        </div>
    </c:if>
</div>


<div class="container">

    <hr>

    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-11">
                <p>Copyright &copy; Andrey Shop 2017</p>
            </div>
            <div class="col-lg-1">
                <p><a class="btn btn-lin" href="/download"><fmt:message key="footer.report"/>
                    <span class="glyphicon glyphicon-file"></span></a></p>
            </div>

        </div>
    </footer>

</div>
</body>
</html>
