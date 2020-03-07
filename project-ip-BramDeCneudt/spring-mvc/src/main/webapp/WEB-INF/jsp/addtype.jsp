<%-- 
    Document   : template
    Created on : 18-Feb-2017, 16:49:40
    Author     : Bram
--%>

<%@include file="../fragments/java.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Type</title>
        <jsp:include page="../fragments/links.jsp" >
            <jsp:param name="" value="" />
        </jsp:include>
    </head>
    <body>
        <div class="container">
            <jsp:include page="../fragments/header.jsp" >
                <jsp:param value="active" name="addtype"/>
            </jsp:include>
            <main>
                <h3>Add Type</h3>

                <div class="alert alert-info">
                    <strong>Info!</strong> <spring:message code="info.types" />
                </div>

                <p>types already existing:</p>
                <ul>
                    <c:forEach items="${types}" var="type">
                        <c:url value="/portfolio/admin/deletetype/" var="url" />
                        <li>${type.name} | ${type.abbreviation} <a href="${url}<c:out value="${type.name}"/>">delete</a></li>
                        </c:forEach>
                </ul>
                <c:url value="/portfolio/admin/addtype.htm" var="target"/>
                <form:form modelAttribute="type" action="${target}" method="post">
                    <p><form:errors path="name" cssClass="alert alert-danger"/></p>
                    <p>naam: <form:input path="name" id="name" /></p>
                    <p><form:errors path="abbreviation" cssClass="alert alert-danger"/></p>
                    <p>afkorting: <form:input path="abbreviation" id="abbreviation" /></p>
                    <p><input type="submit"></p>
                    </form:form>
            </main>
            <jsp:include page="../fragments/footer.jsp" />
        </div>
    </body>
</html>
