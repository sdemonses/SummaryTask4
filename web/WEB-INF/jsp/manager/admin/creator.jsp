<%--
  Created by IntelliJ IDEA.
  User: Dimasyk
  Date: 21.01.2017
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Creator Tour"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>


<div class="page-title-container">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <i class="fa fa-envelope"></i>
                <h1><fmt:message key="creator"/>/</h1>
                <p><fmt:message key="creator.info"/></p>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-offset-3 col-sm-6">
        <form method="post" action="controller">
            <input type="hidden" name="command" value="creator">
            <input type="hidden" name="id" value="${requestScope.tour.id}">
            <br>
            <div class="form-group">
                <label for="name"><fmt:message key="creator.tourName"/></label>
                <input type="text" name="name" placeholder="<fmt:message key="creator.enterName"/>"
                       class="form-control" id="name" value="${requestScope.tour.name}">
            </div>

            <div class="form-group">
                <label for="duration"><fmt:message key="creator.duration"/></label>
                <input type="number" name="duration" placeholder="<fmt:message key="creator.enterDuration"/>"
                       class="form-control" id="duration" min="0" value="${requestScope.tour.duration}">
            </div>

            <div class="form-group">
                <label for="type"><fmt:message key="tours.filter.type"/></label>
                <select id="type" name="type" class="form-control">
                    <option value="${requestScope.tour.type.name}" selected>${requestScope.tour.type.name}</option>

                    <c:forEach items="${requestScope.types}" var="type">
                        <c:if test="${requestScope.tour.type.name != type.name}">
                            <option value="${type.name}">${type.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="cost"><fmt:message key="cabinet.table.cost"/></label>
                <input type="number" name="cost" placeholder="<fmt:message key="creator.enterCost"/>"
                       class="form-control" id="cost" min="0" value="${requestScope.tour.cost}">
            </div>

            <div class="form-group">
                <label for="person"><fmt:message key="cabinet.table.person"/></label>
                <input type="number" name="person" placeholder="<fmt:message key="creator.enterPerson"/>"
                       class="form-control" id="person" min="0" value="${requestScope.tour.person}">
            </div>

            <div class="form-group">
                <label for="hotel"><fmt:message key="cabinet.table.hotel"/></label>
                <select id="hotel" name="hotel" class="form-control">
                    <option value="${requestScope.tour.hotel.id}" selected>${requestScope.tour.hotel.name}</option>
                    <c:forEach var="hotel" items="${requestScope.hotels}">
                        <c:if test="${requestScope.tour.hotel.id != hotel.id}">
                            <option value="${hotel.id}">${hotel.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="btn btn-success"><fmt:message key="creator.create"/></button>
        </form>
    </div>

</div>
</body>
</html>
