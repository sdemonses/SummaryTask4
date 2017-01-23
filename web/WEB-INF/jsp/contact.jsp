<%--
  Created by IntelliJ IDEA.
  User: dmitry
  Date: 23.01.17
  Time: 2:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Sign in"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>


<div class="page-title-container">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <i class="fa fa-envelope"></i>
                <h1><fmt:message key="header.contact"/>/</h1>
                <p><fmt:message key="contact.info"/></p>
            </div>
        </div>
    </div>
</div>
<div class="contact-us-container">
    <div class="container">
        <div class="row">
            <div class=" col-sm-offset-3 col-sm-6">


                <form role="form" action="controller" method="post">
                    <input type="hidden" value="contact" name="command">
                    <div class="form-group">
                        <label for="name"><fmt:message key="name"/></label>
                        <input type="text" name="name" placeholder="<fmt:message key="enterName"/>" class="form-control"
                               id="name">
                    </div>
                    <div class="form-group">
                        <label for="email"><fmt:message key="form.email"/></label>
                        <input type="email" name="email" placeholder="<fmt:message key="form.enterEmail"/>"
                               class="form-control" id="email">
                    </div>
                    <div class="form-group">
                        <label for="subject"><fmt:message key="subject"/></label>
                        <input type="text" name="subject" placeholder="<fmt:message key="enterSubj"/>"
                               class="form-control" id="subject">
                    </div>
                    <div class="form-group">
                        <label for="message"><fmt:message key="message"/></label>
                        <textarea name="message" placeholder="<fmt:message key="enterMess"/>" class="form-control"
                                  id="message"></textarea>
                    </div>
                    <button type="submit" class="btn btn-success"><fmt:message key="success"/></button>
                </form>


            </div>
        </div>
    </div>
</div>
</body>
</html>
