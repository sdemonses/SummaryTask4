<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dmitry
  Date: 21.01.17
  Time: 21:22
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
                <h1><fmt:message key="users"/>/</h1>
                <p><fmt:message key="users.info"/></p>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-offset-1 col-sm-10">
        <table class="table table-hover">
            <thead>
            <tr>
                <th><fmt:message key="users.login"/></th>
                <th><fmt:message key="users.email"/></th>
                <th><fmt:message key="users.firstName"/></th>
                <th><fmt:message key="users.lastName"/></th>
                <th><fmt:message key="ban"/></th>
            </tr>
            </thead>
            <tbody>
            <C:forEach items="${requestScope.users}" var="userL">
                <tr>
                    <td>${userL.login}</td>
                    <td>${userL.email}</td>
                    <td>${userL.firstName}</td>
                    <td>${userL.lastName}</td>
                    <c:if test="${userL.userStatus.name == 'ban'}">
                        <td>
                            <form method="post" action="controller">
                                <input type="hidden" value="users" name="command">
                                <input type="hidden" value="${userL.id}" name="id">
                                <input type="hidden" value="empty" name="com">
                                <button type="submit" class="btn btn-success">
                                    <fmt:message key="users.unban"/></button>
                            </form>
                        </td>
                    </c:if>
                    <c:if test="${userL.userStatus.name != 'ban'}">
                        <td>
                            <form method="post" action="controller">
                                <input type="hidden" value="users" name="command">
                                <input type="hidden" value="${userL.id}" name="id">
                                <input type="hidden" value="ban" name="com">
                                <button type="submit" class="btn btn-danger"><fmt:message key="users.ban"/></button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </C:forEach>
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
