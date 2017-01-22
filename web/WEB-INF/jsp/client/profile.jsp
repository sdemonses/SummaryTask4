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
                <p><fmt:message key="profile.info"/></p>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-sm-offset-1 col-sm-4 contact-form">
        <c:if test="${not empty requestScope.errorMessage}">
            <div class="alert alert-danger">
                <strong><fmt:message key="error"/></strong> ${requestScope.errorMessage}
            </div>
        </c:if>

        <%@include file="/WEB-INF/jspf/account.jspf"%>
    </div>


    <div class="col-sm-offset-1 col-sm-5">
        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="cabinet.table.tourName"/></th>
                <th><fmt:message key="cabinet.table.person"/></th>
                <th><fmt:message key="cabinet.table.cost"/></th>
                <th><fmt:message key="cabinet.table.hotel"/></th>
                <th><fmt:message key="cabinet.table.duration"/></th>
                <th><fmt:message key="cabinet.table.status"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.orders}" var="order">
                <tr>
                    <td>${order.tour.name}</td>
                    <td>${order.tour.person}</td>
                    <td>${order.tour.cost}</td>
                    <td>${order.tour.hotel.name}</td>
                    <td>${order.tour.duration}</td>
                    <td>${order.status}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


    </div>
    <div class="col-sm-1"></div>

</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
