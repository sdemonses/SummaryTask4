<%--
  Created by IntelliJ IDEA.
  User: dmitry
  Date: 17.01.17
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Sign Up"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>


<div class="page-title-container">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <i class="fa fa-envelope"></i>
                <h1>Sign Up/</h1>
                <p>Register new account </p>
            </div>
        </div>
    </div>
</div>


<div class="row">
    <div class="col-sm-offset-3  col-sm-6 contact-form">
        <form role="form" action="controller" method="post">
            <input type="hidden" name="command" value="signUp"/>

            <div class="form-group">
                <label for="contact-name">Login:</label>
                <input type="text" name="login" placeholder="Enter your login..." class="contact-name" id="contact-name">
            </div>

            <div class="form-group">
                <label for="contact-name">Login:</label>
                <input type="text" name="login" placeholder="Enter your login..." class="contact-name" id="contact-name">
            </div>

            <div class="form-group">
                <label for="contact-email">Password:</label>
                <input type="password" name="password" placeholder="Enter your password..." class="contact-email"
                       id="contact-email">
            </div>

            <button type="submit" class="btn">Sign In</button>
        </form>

    </div>
</div>


</body>
</html>

