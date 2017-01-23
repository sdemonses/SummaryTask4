<%--
  Created by IntelliJ IDEA.
  User: Dimasyk
  Date: 22.01.2017
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Sale"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="page-title-container">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <i class="fa fa-envelope"></i>
                <h1><fmt:message key="tour.sale"/>/</h1>
                <p><fmt:message key="tour.sale.info"/></p>
            </div>
        </div>
    </div>
</div>
<mt:error errorMessage="${requestScope.errorMessage}"/>

<div class="row">
    <div class="col-sm-offset-3 col-sm-6">
        <form role="form" action="controller" method="post">
            <input type="hidden" name="command" value="sale"/>
            <input type="hidden" name="id" value="${requestScope.order.id}"/>

            <div class="form-group">
                <label for="maxValue"><fmt:message key="tour.sale.maxValue"/></label>
                <input type="number" min="1" name="maxValue" value="${requestScope.order.saleMax}" placeholder="<fmt:message key="tour.sale.enterMaxValue"/>"
                       class="form-control" id="maxValue"
                       required>
            </div>

            <div class="form-group">
                <label for="step"><fmt:message key="tour.sale.step"/></label>
                <input type="number" name="step" value="${requestScope.order.saleStep}" placeholder="<fmt:message key="tour.sale.enterStep"/>"
                       class="form-control"
                       id="step" required>
            </div>
            <div class="col-xs-6 col-sm-6 col-md-6">
                <button type="submit" class="btn btn-labeled btn-success"><fmt:message key="ok"/>
                </button>

            </div>
        </form>

    </div>

</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>