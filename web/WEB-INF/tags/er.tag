<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="errorMessage" type="java.lang.String" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger">
        <strong>Error</strong> ${errorMessage}
    </div>
</c:if>