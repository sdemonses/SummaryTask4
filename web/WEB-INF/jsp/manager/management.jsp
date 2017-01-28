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
                <h1><fmt:message key="management"/>/</h1>
                <p><fmt:message key="management.info"/></p>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-sm-offset-1 col-sm-4 contact-form">
        <mt:error errorMessage="${requestScope.errorMessage}"/>

        <%@include file="/WEB-INF/jspf/account.jspf" %>



        <c:if test="${sessionScope.userRole.name == 'admin'}">


            <a href="controller?command=creator">
                <br><fmt:message key="tour.createTour"/></a>
            <a href="controller?command=users">
                <br><fmt:message key="tour.userSetting"/></a>


        </c:if>

    </div>
    <div class="col-sm-offset-1 col-sm-5">
        <table class="table">
            <thead>
            <th><fmt:message key="cabinet.table.tourName"/></th>
            <th><fmt:message key="cabinet.table.cost"/></th>
            <th><fmt:message key="cabinet.table.hotel"/></th>
            <th><fmt:message key="cabinet.table.sale"/></th>
            <th><fmt:message key="cabinet.table.status"/></th>
            <th><fmt:message key="cabinet.table.changeStatus"/></th>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.orders}" var="order">
                <tr>
                    <td>${order.tour.name}</td>
                    <td>${order.tour.cost}</td>
                    <td>${order.tour.hotel.name}</td>
                    <td>${order.sale}</td>
                    <td>${order.status}</td>

                    <c:if test="${order.status.name == 'register'}">
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" value="changeStatus" name="command">
                                <input type="hidden" value="${order.id}" name="id">
                                <input type="hidden" value="paid" name="com">
                                <input type="hidden" value="order" name="type">
                                <button type="submit" class="btn btn-success"
                                ><fmt:message key="management.paid"/></button>
                            </form>

                            <form action="controller" method="post">
                                <input type="hidden" value="changeStatus" name="command">
                                <input type="hidden" value="${order.id}" name="id">
                                <input type="hidden" value="canceled" name="com">
                                <input type="hidden" value="order" name="type">
                                <button type="submit" class="btn btn-danger"
                                ><fmt:message key="management.canceled"/></button>
                            </form>
                        </td>
                    </c:if>
                    <c:if test="${order.status.name != 'register'}">
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" value="changeStatus" name="command">
                                <input type="hidden" value="${order.id}" name="id">
                                <input type="hidden" value="register" name="com">
                                <input type="hidden" value="order" name="type">
                                <button type="submit" class="btn btn-default"
                                ><fmt:message key="tours.register"/></button>
                            </form>
                        </td>
                    </c:if>
                    <td>
                        <form action="controller" method="get">
                            <input type="hidden" value="sale" name="command">
                            <input type="hidden" value="${order.id}" name="id">
                            <button type="submit" class="btn btn-default"
                            ><fmt:message key="tours.sale"/></button>
                        </form>
                    </td>


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
