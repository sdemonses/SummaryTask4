<%--
  Created by IntelliJ IDEA.
  User: dmitry
  Date: 17.01.17
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Epam Tour" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="content-1">
    <h1><fmt:message key="travel"/></h1>
</div>
<div class="content-2">
    <h2>Welcome to Bon Voyage</h2>
    <div class="content-text">
        <div class="content-text-1">Bon Voyage is a progressive traveling agency that offers its clients a wide variety of tours and destinations to enjoy. With our traveling services you can get wherever you want to - from the American mountain tops to Australian planes, and discover the whole planet eventually.
        </div>
        <div class="content-text-2"> Moreover, our team of professional  travel guides and tour managers can offer you a tour designed according to your needs and wishes. We can handle all traveling issues without any extra cost.
        </div>
    </div>

</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
