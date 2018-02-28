<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 21.01.2017
  Time: 3:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="translations" />
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/login.css" rel="stylesheet">
    <title>Login</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container" style="padding: 70px ">
    <h1 class="text-center" style="text-shadow: 2px 4px 3px rgba(0,0,0,0.3);">Andrey Shop</h1>
    <form action="/login" class="form-signin" role="form" method="post">
        <input type="hidden" name="command" value="login" />
        <div class="form-group">
            <input type="email" name="email" class="form-control" placeholder="Email" required="" autofocus="">
        </div>
        <div class="form-group">
            <input type="password" name="password" class="form-control" placeholder="<fmt:message key="navbar.password"/>" required="">
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="navbar.login"/></button>
    </form>

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


<script src="../../js/jquery.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>
</html>
