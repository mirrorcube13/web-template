<%-- Created by IntelliJ IDEA. --%>
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
<jsp:include page="navbar.jsp"/>

<div class="row banner-wrap">
    <div class="col-md-12">

        <img class="slide-image" src="../../img/1.jpg" alt="">

    </div>
</div>


<!-- Page Content -->
<div class="container">

    <div class="row">

        <div class="col-md-3">
            <p class="lead">Andrey Shop</p>
            <form class="form-inline" method="post" action="/andrey-shop">
                <div class="form-group">
                    <input class="form-control input-sm" type="text" name="like">
                </div>
                <button type="submit" class="btn btn-default">Поиск</button>
            </form>
            <div class="list-group">
                <a id="toggler" href="#" class="list-group-item"><fmt:message key="index.itemList"/></a>
                <a href="#" data-type="Ванны" onclick="onClickType(this)" class="list-group-item"><fmt:message
                        key="index.baths"/></a>
                <a href="#" data-type="Унитазы" onclick="onClickType(this)"
                   class="list-group-item"><fmt:message key="index.toilets"/></a>
                <a href="#" data-type="Смесители" onclick="onClickType(this)"
                   class="list-group-item"><fmt:message key="index.taps"/></a>
                <a href="#" data-type="Умывальники" onclick="onClickType(this)"
                   class="list-group-item"><fmt:message key="index.basins"/></a>
            </div>
        </div>


        <div class="col-md-9">

            <div class="btn-group">
                <a href="#" data-sort="name" type="button" onclick="onClickSort(this)"
                   class="btn btn-default"><fmt:message key="index.byName"/></a>
                <a href="#" data-sort="price" type="button" onclick="onClickSort(this)"
                   class="btn btn-default"><fmt:message key="index.byPrice"/></a>
                <a href="#" data-sort="id" type="button" onclick="onClickSort(this)"
                   class="btn btn-default"><fmt:message key="index.byDate"/></a>
            </div>

            <div class="b-items">
                <jsp:include page="items.jsp"/>
            </div>


        </div>
        <!-- /.container -->

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
        <!-- /.container -->
    </div>
</div>
<script src="../../js/bootstrap.min.js"></script>
<script type="text/javascript">
    function onClickSort(_this) {
        var value = _this.dataset.sort;
        $.ajax({
            method: "POST",
            url: "/andrey-shop",
            data: {'sort': value},
            success: function (result) {
                $(".b-items").html(result);
            }
        });
    }
    function onClickType(_this) {
        var value = _this.dataset.type;
        $.ajax({
            method: "POST",
            url: "/andrey-shop",
            data: {'productType': value},
            success: function (result) {
                $(".b-items").html(result);
            }
        });
    }
</script>
</body>
</html>