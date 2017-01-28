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

            <mt:error errorMessage="${requestScope.errorMessage}"/>


            <h4 align="center"><fmt:message key="tours.filter"/></h4>
            <div class="form-group">
                <label for="type"><fmt:message key="tours.filter.type"/></label>
                <select name="type" id="type" class="form-control">
                    <option value="all"><fmt:message key="tours.filter.all"/></option>
                    <option value="rest"><fmt:message key="tours.filter.rest"/></option>
                    <option value="shopping"><fmt:message key="tours.filter.shopping"/></option>
                    <option value="excursion"><fmt:message key="tours.filter.excursion"/></option>
                </select>
            </div>
            <div class="form-group">

                <label for="costFrom"><fmt:message key="tours.filter.from"/></label>
                <input class="form-control" type="number" name="costFrom" id="costFrom" min="0">
                <p></p>
                <label for="costTo"><fmt:message key="tours.filter.to"/></label>
                <input class="form-control" type="number" name="costTo" id="costTo" min="0">
            </div>
            <div class="form-group">
                <label for="countPerson"><fmt:message key="tours.filter.countPerson"/></label>
                <select name="countPerson" id="countPerson" class="form-control">
                    <option value="all"><fmt:message key="tours.filter.all"/></option>
                    <c:forEach var="i" begin="1" end="4">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="stars"><fmt:message key="tours.filter.hotelStar"/></label>
                <select name="stars" id="stars" class="form-control">
                    <option value="all"><fmt:message key="tours.filter.all"/></option>
                    <c:forEach var="i" begin="1" end="5">
                        <option value="${i}">${i}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="hot"><fmt:message key="tours.filter.status"/></label>
                <select name="hot" id="hot" class="form-control">
                    <option value="all"><fmt:message key="tours.filter.all"/></option>
                    <option value="hot"><fmt:message key="tours.filter.hot"/></option>
                    <option value="empty"><fmt:message key="tours.filter.empty"/></option>
                </select>
            </div>
            <button type="submit" class="btn btn-success"><fmt:message key="tours.filter"/></button>
        </form>
    </div>
    <div class="col-sm-8">
        <h2 align="center"><fmt:message key="tours"/></h2>
        <c:forEach var="order" items="${requestScope.tours}">
            <div class="active-tour">
                <div class="row">
                    <div class="col-sm-4">
                        <h2>${order.hotel.city.name}</h2>
                        <h4>${order.hotel.city.country.name}</h4>
                        <c:if test="${order.status.name == 'hot'}">
                            <h3>!HOT!</h3>
                        </c:if>
                    </div>
                    <div class="col-sm-4">
                        <h2>${order.name}</h2>
                        <br>
                        <c:forEach begin="1" end="${order.hotel.countOfStars}">
                            <i class="fa fa-star fa-2x" aria-hidden="true"></i>
                        </c:forEach>
                        <br>
                        <h4><fmt:message key="tours.filter.countPerson"/> : ${order.person}</h4>
                        <br>
                        <h4><fmt:message key="cabinet.table.duration"/> : ${order.duration} </h4>
                    </div>
                    <div class="col-sm-4">
                        <h3><fmt:message key="cabinet.table.cost"/> : ${order.cost}</h3>
                        <br>
                        <h4><fmt:message key="tours.filter.type"/> : ${order.type}</h4>

                        <c:if test="${sessionScope.userRole.name == 'admin'||sessionScope.userRole.name == 'manager'}">
                            <form action="controller" method="post">
                                <input type="hidden" value="changeStatus" name="command">
                                <input type="hidden" value="${order.id}" name="id">
                                <input type="hidden" value="tour" name="type">
                                <c:if test="${order.status.name == 'hot'}">
                                    <input type="hidden" value="empty" name="com">
                                    <button type="submit" class="btn btn-group"
                                    ><fmt:message key="tours.doEmpty"/></button>
                                </c:if>
                                <c:if test="${order.status.name == 'empty'}">
                                    <input type="hidden" value="hot" name="com">
                                    <button type="submit" class="btn btn-group"
                                    ><fmt:message key="tours.doHot"/></button>
                                </c:if>
                            </form>
                        </c:if>

                        <c:if test="${sessionScope.userRole.name == 'admin'}">
                            <form action="controller" method="get">
                                <input type="hidden" value="creator" name="command">
                                <input type="hidden" value="${order.id}" name="id">
                                <button type="submit" class="btn btn-success"
                                ><fmt:message key="tours.edit"/></button>
                            </form>
                            <form action="controller" method="post">
                                <input type="hidden" value="delete" name="command">
                                <input type="hidden" value="${order.id}" name="id">
                                <button type="submit" class="btn btn-danger"
                                ><fmt:message key="tours.delete"/></button>
                            </form>
                        </c:if>

                        <c:if test="${sessionScope.userRole.name == 'client'}">
                            <form action="controller" method="post">
                                <input type="hidden" value="order" name="command">
                                <input type="hidden" value="${order.id}" name="id">
                                <button type="submit" class=" btn btn-success"
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
<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
