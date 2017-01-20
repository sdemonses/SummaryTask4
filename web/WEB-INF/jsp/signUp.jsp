<%--
  Created by IntelliJ IDEA.
  User: dmitry
  Date: 17.01.17
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
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
                <h1>Sign Up/</h1>
                <p>Register new account </p>
            </div>
        </div>
    </div>
</div>


<div class="row">
    <div class="col-sm-offset-3  col-sm-6 contact-form">
        <form role="form" action="controller" method="post">
            <input type="hidden" name="command" value="signUp"/>
            <div class="form-group">
                <label for="login"><fmt:message key="form.login"/></label>
                <input type="text" name="login" placeholder="<fmt:message key="form.enterLogin"/>" class="contact-name"
                       id="login" required>
            </div>
            <div class="form-group">
                <label for="email"><fmt:message key="form.email"/>:</label>
                <input type="email" name="email" placeholder="<fmt:message key="form.enterEmail"/>" class="contact-name"
                       id="email" required>
            </div>

            <div class="form-group">
                <label for="firstName"><fmt:message key="form.firstName"/>:</label>
                <input type="text" name="firstName" placeholder="<fmt:message key="form.enterFirstName"/>"
                       class="contact-name" id="firstName">
            </div>

            <div class="form-group">
                <label for="lastName"><fmt:message key="form.lastName"/>:</label>
                <input type="text" name="lastName" placeholder="<fmt:message key="form.enterLastName"/>"
                       class="contact-name" id="lastName">
            </div>

            <div class="form-group">
                <label for="password"><fmt:message key="form.password"/>:</label>
                <input type="password" name="password" placeholder="<fmt:message key="form.enterPassword"/>" class="contact-email"
                       id="password" required>
            </div>

            <div class="form-group">
                <label for="repeatPassword"><fmt:message key="form.repeatPassword"/>:</label>
                <input type="password" name="repeatPassword" placeholder="<fmt:message key="form.enterPassword"/>" class="contact-email"
                       id="repeatPassword" required>
            </div>

            <button type="submit" class="btn"><fmt:message key="signUp"/></button>
        </form>

    </div>
</div>


</body>
</html>

