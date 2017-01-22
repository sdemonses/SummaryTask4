<%--
  Created by IntelliJ IDEA.
  User: dmitry
  Date: 17.01.17
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Sign in"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>


<div class="page-title-container">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <i class="fa fa-envelope"></i>
                <h1><fmt:message key="header.signIn"/>/</h1>
                <p><fmt:message key="signIn.entered"/></p>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-offset-3  col-sm-6 contact-form">
        <form role="form" action="controller" method="post">
            <input type="hidden" name="command" value="login"/>

            <c:if test="${not empty requestScope.errorMessage}">
                <div class="alert alert-danger">
                    <strong><fmt:message key="error"/></strong> ${requestScope.errorMessage}
                </div>
            </c:if>

            <div class="input-group">
                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                <input type="text" name="login" placeholder="<fmt:message key="form.enterLogin"/>" class="form-control" id="contact-name"
                       required>
            </div>

            <div class="input-group">
                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                <input type="password" name="password" placeholder="<fmt:message key="form.enterPassword"/>" class="form-control"
                       id="contact-email" required>
            </div>
            <div class="col-xs-6 col-sm-6 col-md-6">
                <button type="submit" class="btn btn-labeled btn-success"> <fmt:message key="header.signIn"/>
                </button>

            </div>
        </form>

    </div>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
