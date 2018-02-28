<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="translations"/>
<html>
<head>
    <link href="../../css/flags.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#responsive-menu">

                <span class="sr-only"><fmt:message key="navbar.openNavigation"/> </span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" rel="home" href="/andrey-shop">
                <img style="max-width:100px; margin-top: -7px;"
                     src="../../img/logo-primary1.png">
            </a>
        </div>
        <div class="collapse navbar-collapse" id="responsive-menu">
            <ul class="nav navbar-nav">
                <li><a href="/andrey-shop"><fmt:message key="navbar.main"/></a></li>
                <c:if test="${sessionScope.userData.role eq 'admin'}">
                    <li><a href="/dashboard"><fmt:message key="navbar.dashboard"/></a></li>
                </c:if>

            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="/shoppingBag"><span class="glyphicon glyphicon-shopping-cart"></span><fmt:message
                            key="navbar.shoppingBag"/></a>


                    </button>
                </li>
                <c:choose>
                    <c:when test="${empty sessionScope.userData}">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="navbar.login"/>
                                <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <form action="/login" style="max-width: 120px; margin-left: 20px" role="form"
                                      method="post">
                                    <li><a href="/registration"><fmt:message key="registration.signUp"/>
                                    </a></li>
                                    <input type="hidden" name="command" value="login"/>
                                    <li class="divider"></li>
                                    <li>
                                        <div class="form-group">
                                            <input type="email" name="email" class="form-control" placeholder="Email"
                                                   required="">
                                        </div>
                                    </li>

                                    <li>
                                        <div class="form-group">
                                            <input type="password" name="password" class="form-control"
                                                   placeholder="<fmt:message key="navbar.password"/>" required="">
                                        </div>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <button class="btn btn-lg btn-primary btn-block" type="submit">
                                            <span class="glyphicon glyphicon-user"></span> <fmt:message
                                                key="navbar.signIn"/></button>
                                    </li>
                                </form>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <form action="/login" method="post">
                                <input type="hidden" name="command" value="logout"/>
                                <button type="submit" class="btn btn-link navbar-btn">
                                    <fmt:message key="navbar.logout"/>
                                </button>
                            </form>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li>
                    <form>
                        <input type="submit" style="border: 0px; margin-left: 10px; margin-top: 20px"
                               src="/img/flag.png"
                               class="flag flag-us" name="language" value="en_US">
                        <input type="submit" style="border: 0px; margin-top: 20px" src="/img/flag.png"
                               class="flag flag-ru" name="language" value="ru_RU">
                    </form>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
</body>