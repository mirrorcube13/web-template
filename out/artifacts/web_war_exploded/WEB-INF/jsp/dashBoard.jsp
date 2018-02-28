<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 19.01.2017
  Time: 2:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="translations" />
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dashboard</title>
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/dashboard.css" rel="stylesheet">
    <script src="../../js/jquery.js"></script>
    <script src="../../js/dashboard.js" ></script>
    <script src="../../js/orders.js" ></script>
    <script type="text/javascript">
        window.addEventListener("DOMContentLoaded", function() {
            var nodes = document.querySelectorAll(".parent");
            [].forEach.call(nodes, function(tr) {
                var box = [],
                        next = tr.nextElementSibling;
                while (next && next.classList.contains("box")) {
                    box.push(next);
                    next = next.nextElementSibling
                }
                tr.cells[0].addEventListener("click", function() {
                    box.forEach(function(item) {
                        if(item.style.display == 'table-row') {
                            item.style.display = 'none';
                        }
                        else {
                            item.style.display = 'table-row';
                        }
                    });
                    return false
                })
            })
        });
    </script>
</head>

<body>

<jsp:include page="navbar.jsp"/>

<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar ">
        <li><a id="customers" href="#customersBlock" class="tt open"><span
                class="glyphicon glyphicon glyphicon-user"></span>
            <fmt:message key="dashboard.users"/></a></li>
        <li><a id="orders" href="#ordersBlock" class="tt open"><span
                class="glyphicon glyphicon glyphicon-list-alt"></span> <fmt:message key="dashboard.Orders"/></a></li>
        <li><a id="items" href="#itemsBlock" class="tt open"><span
                class="glyphicon glyphicon glyphicon-upload"></span> <fmt:message key="dashboard.Products"/></a></li>
    </ul>
</div>

<div id="customersBlock" style="display: block" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <h1 class="page-header"><fmt:message key="dashboard.usersList"/></h1>

    <div class="table-responsive zzz">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th><fmt:message key="registration.name"/></th>
                <th><fmt:message key="registration.lastName"/></th>
                <th>Email</th>
                <th><fmt:message key="navbar.password"/></th>
                <th><fmt:message key="registration.phoneNumber"/></th>
                <th><fmt:message key="registration.address"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="customer" items="${requestScope.customerList}">
                <tr class="order parent">
                    <td><a href="#">${customer.id}</a></td>
                    <td>${customer.firstName}</td>
                    <td>${customer.lastName}</td>
                    <td>${customer.email}</td>
                    <td>${customer.password}</td>
                    <td>${customer.phoneNumber}</td>
                    <td>${customer.addres}</td>
                </tr>
                <tr class="box info" >
                    <th>ID</th>
                    <th colspan="2">Checkout date</th>
                    <th colspan="2">Closed date</th>
                    <th colspan="2">Order status</th>
                </tr>
                <c:forEach var="order" items="${customer.orderList}">
                    <tr class="box success" >
                    <td>${order.orderNumber}</td>
                    <td colspan="2"> ${order.checkoutDate.toLocalDate()} ${order.checkoutDate.toLocalTime()}</td>
                    <td colspan="2">${order.closedDate.toLocalDate()} ${order.closedDate.toLocalTime()}</td>
                    <td colspan="2">${order.orderStatus}</td>
                    </tr>
                </c:forEach>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div id="ordersBlock" style="display: none" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <h1 class="page-header"><fmt:message key="dashboard.orderList"/></h1>
    <div class="table-responsive zzz">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th><fmt:message key="dashboard.userId"/></th>
                <th><fmt:message key="dashboard.checkoutDate"/></th>
                <th><fmt:message key="dashboard.closedDate"/></th>
                <th><fmt:message key="dashboard.status"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${requestScope.orderList}">
                <tr class="order parent">
                    <td><a href="#">${order.orderNumber}</a></td>
                    <td>${order.customerID}</td>
                    <td>${order.checkoutDate.toLocalDate()} ${order.checkoutDate.toLocalTime()}</td>
                    <td>${order.closedDate.toLocalDate()} ${order.closedDate.toLocalTime()}</td>
                    <td>${order.orderStatus}</td>
                    <c:choose>
                        <c:when  test="${order.closedDate == null}">
                        <td><a class="order-id" data-id="${order.orderNumber}" onclick="takeOrder(event)"><span class="glyphicon glyphicon-ok"></span></a></td>
                        </c:when>
                        <c:otherwise><td></td></c:otherwise>
                    </c:choose>
                </tr>
                <tr class="box info" >
                    <th>ID</th>
                    <th colspan="2"><fmt:message key="dashboard.ItemName"/></th>
                    <th colspan="2"><fmt:message key="dashboard.ItemDescription"/></th>
                    <th><fmt:message key="dashboard.price"/></th>
                </tr>
                <c:forEach var="item" items="${order.items.keySet()}">
                    <tr class="box success" >
                        <td>${item.id}</td>
                        <td colspan="2"> ${item.name}</td>
                        <td colspan="2">${item.description}</td>
                        <td>${item.price}</td>
                    </tr>
                </c:forEach>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<div id="itemsBlock" style="display: none" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <h1 class="page-header"><fmt:message key="dashboard.Additem"/></h1>
    <form action="/dashboard" role="form" method="post">
        <div class="form-group">
        <select name="type">
            <option value="ВАННЫ" selected="selected"><fmt:message key="dashboard.baths"/></option>
                <option value="УНИТАЗЫ"><fmt:message key="dashboard.toilets"/></option>
                <option value="СМЕСИТЕЛИ"><fmt:message key="dashboard.taps"/></option>
                <option value="УМЫВАЛЬНИКИ"><fmt:message key="dashboard.basins"/></option>
        </select>
            </div>
        <div class="form-group">
            <label><fmt:message key="dashboard.productName"/></label>
            <input type="text" name="itemName" class="form-control" size="">
        </div>

        <div class="form-group">
            <label><fmt:message key="dashboard.productDescription"/></label>
            <textarea type="text" name="itemDescription" class="form-control" rows="3"></textarea>
        </div>

        <div class="form-group">
            <label><fmt:message key="dashboard.price"/></label>
            <input type="number" name="itemPrice" class="form-control">
        </div>

        <div class="form-group">
            <label><fmt:message key="dashboard.amount"/></label>
            <input type="number" name="itemAmount" class="form-control">
        </div>

        <div class="form-group">
            <label><fmt:message key="dashboard.insertPicture"/></label>
            <input name="itemImage"  type="file" accept="image/*">
        </div>

        <button type="submit" class="btn btn-default"><fmt:message key="dashboard.confirm"/></button>
    </form>

    <h1 class="page-header"><fmt:message key="dashboard.itemDtoList"/></h1>
    <div class="table-responsive zzz">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th><fmt:message key="dashboard.ItemName"/></th>
                <th><fmt:message key="dashboard.ItemDescription"/></th>
                <th><fmt:message key="dashboard.price"/></th>
                <th><fmt:message key="dashboard.BalanceStock"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${requestScope.itemList}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.description}</td>
                    <td>${item.price}</td>
                    <td>${item.remainingAmount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div class="container">

    <hr>

    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-2">
            </div>
            <div class="col-lg-9">
                <p>Copyright &copy; Andrey Shop 2017</p>
            </div>
            <div class="col-lg-1">
                <p><a class="btn btn-lin" href="/download"><fmt:message key="footer.report"/>
                    <span class="glyphicon glyphicon-file"></span></a></p>
            </div>

        </div>
    </footer>

</div>


    <script src="../../js/jquery.js"></script>
    <script src="../../js/bootstrap.min.js"></script>
</body>
</html>
