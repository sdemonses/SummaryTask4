<%--
  Created by IntelliJ IDEA.
  User: Dimasyk
  Date: 19.01.2017
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Tours"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="page-title-container">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <i class="fa fa-globe"></i>
                <h1><fmt:message key="tours"/>/</h1>
                <p><fmt:message key="tours.info"/></p>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-sm-offset-1 col-sm-2">
        <form action="controller" method="get">
            <input type="hidden" value="filter" name="command">
            <h4 align="center"><fmt:message key="tours.filter"/></h4>
            <div class="form-group">
                <label for="type"><fmt:message key="tours.filter.type"/></label>
                <select name="type" id="type">
                    <option value="all"><fmt:message key="tours.filter.all"/></option>
                    <option value="rest"><fmt:message key="tours.filter.rest"/></option>
                    <option value="shopping"><fmt:message key="tours.filter.shopping"/></option>
                    <option value="excursion"><fmt:message key="tours.filter.excursion"/></option>
                </select>
            </div>
            <div class="form-group">

                <label for="costFrom"><fmt:message key="tours.filter.from"/></label>
                <input type="number" name="costFrom" id="costFrom">
                <p></p>
                <label for="costTo"><fmt:message key="tours.filter.to"/></label>
                <input type="number" name="costTo" id="costTo">
            </div>
            <div class="form-group">
                <label for="countPerson"><fmt:message key="tours.filter.countPerson"/></label>
                <select name="countPerson" id="countPerson">
                    <option value="all"><fmt:message key="tours.filter.all"/></option>
                    <c:forEach var="i" begin="1" end="4">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="stars"><fmt:message key="tours.filter.hotelStar"/></label>
                <select name="stars" id="stars">
                    <option value="all"><fmt:message key="tours.filter.all"/></option>
                    <c:forEach var="i" begin="1" end="5">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" value="<fmt:message key="tours.filter"/>">
        </form>
    </div>
    <div class="col-sm-8">
        <h2 align="center"><fmt:message key="tours"/></h2>
        <c:forEach var="tour" items="${requestScope.tours}">
            <div class="active-tour">
                <div class="row">
                    <div class="col-sm-4">
                        <h2>${tour.hotel.city.name}</h2>
                        <h4>${tour.hotel.city.country.name}</h4>
                    </div>
                    <div class="col-sm-4">
                        <h2>${tour.name}</h2>
                        <br>
                        <c:forEach begin="1" end="${tour.hotel.countOfStars}">
                            <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                        </c:forEach>
                        <br>
                        <h4><fmt:message key="tours.filter.countPerson"/> : ${tour.person}</h4>
                        <br>
                        <h4><fmt:message key="cabinet.table.duration"/> : ${tour.duration} </h4>
                    </div>
                    <div class="col-sm-4">
                        <h3><fmt:message key="cabinet.table.cost"/> : ${tour.cost}</h3>
                        <br>
                        <h4><fmt:message key="tours.filter.type"/> : ${tour.type}</h4>

                        <c:if test="${sessionScope.userRole.name == 'admin'||sessionScope.userRole.name == 'manager'}">
                            <form action="controller" method="post">
                                <input type="hidden" value="changeStatus" name="command">
                                <input type="hidden" value="${tour.id}" name="id">
                                <c:if test="${tour.status.name == 'hot'}">
                                    <input type="hidden" value="empty" name="com">
                                    <button type="submit" class="btn-block"
                                            ><fmt:message key="tours.doEmpty"/></button>
                                </c:if>
                                <c:if test="${tour.status.name == 'empty'}">
                                    <input type="hidden" value="hot" name="com">
                                    <button type="submit" class="btn-block"
                                            ><fmt:message key="tours.doHot"/></button>
                                </c:if>
                            </form>
                        </c:if>
                        <c:if test="${sessionScope.userRole.name == 'client'}">
                            <form action="controller" method="post">
                                <input type="hidden" value="changeStatus" name="command">
                                <input type="hidden" value="${tour.id}" name="id">
                                <input type="hidden" value="register" name="com">
                                <button type="submit" class="btn-block"
                                       ><fmt:message key="tours.register"/></button>
                            </form>
                        </c:if>
                    </div>
                </div>
                <hr class="border-tour">
            </div>
        </c:forEach>

    </div>
    <div class="col-sm-1"></div>
</div>

</body>
</html>