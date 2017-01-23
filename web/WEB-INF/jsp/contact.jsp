<%--
  Created by IntelliJ IDEA.
  User: dmitry
  Date: 23.01.17
  Time: 2:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
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
            <div class="col-sm-7 contact-form wow fadeInLeft">


                <form role="form" action="controller" method="post">
                    <input type="hidden" value="contact" name="command">
                    <div class="form-group">
                        <label for="contact-name"><fmt:message key="name"/></label>
                        <input type="text" name="name" placeholder="<fmt:message key="enterName"/>" class="contact-email" id="contact-name">
                    </div>
                    <div class="form-group">
                        <label for="contact-email"><fmt:message key="form.email"/></label>
                        <input type="text" name="email" placeholder="<fmt:message key="form.enterEmail"/>" class="contact-email" id="contact-email">
                    </div>
                    <div class="form-group">
                        <label for="contact-subject"><fmt:message key="subject"/></label>
                        <input type="text" name="subject" placeholder="<fmt:message key="enterSubj"/>" class="contact-subject" id="contact-subject">
                    </div>
                    <div class="form-group">
                        <label for="contact-message"><fmt:message key="message"/></label>
                        <textarea name="message" placeholder="Your message..." class="contact-message" id="contact-message"></textarea>
                    </div>
                    <button type="submit" class="btn">Send</button>
                </form>


            </div>
            <div class="col-sm-5 contact-address wow fadeInUp">
                <h3>We Are Here</h3>
                <div class="map"></div>
                <h3>Address</h3>
                <p>Via Principe Amedeo 9 <br> 10100, Torino, TO, Italy</p>
                <p>Phone: 0039 333 12 68 347</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
