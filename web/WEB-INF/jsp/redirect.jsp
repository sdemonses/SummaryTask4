<%--
  Created by IntelliJ IDEA.
  User: dmitry
  Date: 21.01.17
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Redirect"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="page-title-container">
    <div class="container">
        <div class="row">
            <div class="col-sm-offset-2 col-sm-10">
                <c:if test="${sessionScope.user.userStatus.name == 'ban'}">
                    <p><fmt:message key="banned"/></p>
                </c:if>
            </div>
        </div>
    </div>
</div>

<c:if test="${not empty sessionScope.userStatus && sessionScope.user.userStatus.name !='ban'}">

    <mt:error errorMessage="${requestScope.errorMessage}"/>

    <form method="POST" action="controller">
        <div class="row">
            <input type="hidden" name="command" value="code"/>
            <div class="form-group col-sm-offset-2 col-sm-4">
                <h2><fmt:message key="code.info"/></h2>
                <label for="code"><fmt:message key="code"/></label>
                <input class="form-control" type="number" name="code" id="code" min="0">
                <button class="btn btn-success"><fmt:message key="ok"/></button>
            </div>

        </div>

    </form>
</c:if>


<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
