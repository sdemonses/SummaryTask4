<%--
  Created by IntelliJ IDEA.
  User: dmitry
  Date: 17.01.17
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Sign Up"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>


<div class="page-title-container">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <i class="fa fa-envelope"></i>
                <h1><fmt:message key="header.signUp"/>/</h1>
                <p><fmt:message key="register"/></p>
            </div>
        </div>
    </div>
</div>


<div class="row">
    <div class="col-sm-offset-3  col-sm-6 contact-form">
        <form role="form" action="controller" method="post">
            <input type="hidden" name="command" value="signUp"/>

            <c:if test="${not empty requestScope.errorMessage}">
                <div class="alert alert-danger">
                    <strong><fmt:message key="error"/></strong> ${requestScope.errorMessage}
                </div>
            </c:if>

            <div class="form-group">

                <label for="login"><fmt:message key="form.login"/></label>

                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                    <input type="text" name="login" placeholder="<fmt:message key="form.enterLogin"/>"
                           class="form-control"
                           id="login" required>
                </div>

            </div>
            <div class="form-group">
                <label for="email"><fmt:message key="form.email"/>:</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                    <input type="email" name="email" placeholder="<fmt:message key="form.enterEmail"/>"
                           class="form-control"
                           id="email" required>
                </div>
            </div>

            <div class="form-group">
                <label for="firstName"><fmt:message key="form.firstName"/>:</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                    <input type="text" name="firstName" placeholder="<fmt:message key="form.enterFirstName"/>"
                           class="form-control" id="firstName">
                </div>
            </div>

            <div class="form-group">
                <label for="lastName"><fmt:message key="form.lastName"/>:</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                    <input type="text" name="lastName" placeholder="<fmt:message key="form.enterLastName"/>"
                           class="form-control" id="lastName">
                </div>
            </div>

            <div class="form-group">
                <label for="password"><fmt:message key="form.password"/>:</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                    <input type="password" name="password" placeholder="<fmt:message key="form.enterPassword"/>"
                           class="form-control"
                           id="password" required>
                </div>
            </div>

            <div class="form-group">
                <label for="repeatPassword"><fmt:message key="form.repeatPassword"/>:</label>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                    <input type="password" name="repeatPassword" placeholder="<fmt:message key="form.enterPassword"/>"
                           class="form-control"
                           id="repeatPassword" required>
                </div>
            </div>

            <button type="submit" class="btn btn-success"><fmt:message key="signUp"/></button>
        </form>

    </div>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>

