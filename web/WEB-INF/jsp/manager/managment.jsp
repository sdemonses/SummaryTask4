<%--
  Created by IntelliJ IDEA.
  User: dmitry
  Date: 16.01.17
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Managment"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>


<div class="page-title-container">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <i class="fa fa-envelope"></i>
                <h1><fmt:message key="managment"/>/</h1>
                <p><fmt:message key="managment.info"/></p>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-sm-offset-1 col-sm-4 contact-form">
        <%@include file="/WEB-INF/jspf/account.jspf" %>
    </div>
    <div class="col-sm-offset-1 col-sm-5">
        <table class="table">
            <thead>
            <th><fmt:message key="cabinet.table.tourName"/></th>
            <th><fmt:message key="cabinet.table.cost"/></th>
            <th><fmt:message key="cabinet.table.hotel"/></th>
            <th><fmt:message key="cabinet.table.status"/></th>
            <th><fmt:message key="cabinet.table.changeStatus"/></th>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.tours}" var="tour">
                <tr>
                    <td>${tour.name}</td>
                    <td>${tour.cost}</td>
                    <td>${tour.hotel.name}</td>
                    <td>${tour.status}</td>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" value="changeStatus" name="command">
                            <input type="hidden" value="${tour.id}" name="id">
                            <input type="hidden" value="paid" name="com">
                            <button type="submit" class="btn-block"
                            ><fmt:message key="managment.paid"/></button>
                        </form>

                        <form action="controller" method="post">
                            <input type="hidden" value="changeStatus" name="command">
                            <input type="hidden" value="${tour.id}" name="id">
                            <input type="hidden" value="canceled" name="com">
                            <button type="submit" class="btn-block"
                            ><fmt:message key="managment.canceled"/></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


    </div>
    <div class="col-sm-1"></div>

</div>


</body>
</html>
