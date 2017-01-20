<%--
  Created by IntelliJ IDEA.
  User: dmitry
  Date: 18.01.17
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Profile"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="page-title-container">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <i class="fa fa-envelope"></i>
                <h1><fmt:message key="profile"/>/</h1>
                <p><fmt:message key="profille.info"/></p>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-sm-offset-1 col-sm-4 contact-form">
        <form method="post" action="controller">
            <input type="hidden" name="command" value="profile">
            <div class="form-group">
                <label for="login"><fmt:message key="form.login"/></label>
                <input type="text" name="login" placeholder="<fmt:message key="form.enterLogin"/>"
                       value="${sessionScope.user.login}" class="contact-name" id="login">
            </div>

            <div class="form-group">
                <label for="email"><fmt:message key="form.email"/></label>
                <input type="email" name="email" placeholder="<fmt:message key="form.enterEmail"/>"
                       value="${sessionScope.user.email}" class="contact-name" id="email">
            </div>

            <div class="form-group">
                <label for="firstName"><fmt:message key="form.firstName"/>:</label>
                <input type="text" name="firstName" placeholder="<fmt:message key="form.enterFirstName"/>"
                       value="${sessionScope.user.firstName}" class="contact-name" id="firstName">
            </div>

            <div class="form-group">
                <label for="lastName"><fmt:message key="form.lastName"/>:</label>
                <input type="text" name="lastName" placeholder="<fmt:message key="form.enterLastName"/>"
                       value="${sessionScope.user.lastName}" class="contact-name" id="lastName">
            </div>
        </form>

        <form method="post" action="controller">
            <input type="hidden" name="command" value="password">

            <h1 align="center"> <fmt:message key="changePassword"/></h1>

            <div class="form-group">
                <label for="oldPassword"><fmt:message key="changePassword.oldPas"/>:</label>
                <input type="password" name="oldPassword" placeholder="<fmt:message key="changePassword.enterOldPas"/>"
                       value=""  class="contact-email" id="oldPassword">
            </div>

            <div class="form-group">
                <label for="password"><fmt:message key="changePassword.newPass"/>:</label>
                <input type="password" name="password" placeholder="<fmt:message key="changePassword.enterNewPassword"/>"
                       value=""  class="contact-email" id="password">
            </div>

            <div class="form-group">
                <label for="repeatPassword"><fmt:message key="changePassword.repeatNewPass"/>:</label>
                <input type="password" name="repeatPassword" placeholder="<fmt:message key="changePassword.enterNewPassword"/>" class="contact-email"
                       id="repeatPassword">
            </div>
            <button type="submit" class="btn"><fmt:message key="changePassword.btn"/></button>
        </form>
    </div>
    <div class="col-sm-offset-1 col-sm-5">
        <table class="table">
            <thead>
            <th><fmt:message key="cabinet.table.tourName"/></th>
            <th><fmt:message key="cabinet.table.person"/></th>
            <th><fmt:message key="cabinet.table.cost"/></th>
            <th><fmt:message key="cabinet.table.hotel"/></th>
            <th><fmt:message key="cabinet.table.duration"/></th>
            <th><fmt:message key="cabinet.table.status"/></th>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.tours}" var="tour">
                <tr>
                    <td>${tour.name}</td>
                    <td>${tour.person}</td>
                    <td>${tour.cost}</td>
                    <td>${tour.hotel.name}</td>
                    <td>${tour.duration}</td>
                    <td>${tour.status}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


    </div>
    <div class="col-sm-1"></div>

</div>


</body>
</html>
