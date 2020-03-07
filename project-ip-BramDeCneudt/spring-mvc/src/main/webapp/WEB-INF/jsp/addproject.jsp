<%-- 
    Document   : template
    Created on : 18-Feb-2017, 16:49:40
    Author     : Bram
--%>

<%@include file="../fragments/java.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Project</title>
        <jsp:include page="../fragments/links.jsp" >
            <jsp:param name="addproject" value="active" />
        </jsp:include>
    </head>
    <body>
        <div class="container">
            <jsp:include page="../fragments/header.jsp" />
            <main>
                <h3>Add Project</h3>
                <c:url value="/portfolio/admin/addproject.htm" var="target"/>
                <form:form modelAttribute="project" action="${target}" method="post">
                    <form:hidden path="id"  />
                    <p><form:errors path="name" cssClass="alert alert-danger"/></p>
                    <p>naam: <form:input path="name" id="name" /></p>
                    <p><form:errors path="description" cssClass="alert alert-danger"/></p>
                    <p>description: <form:input path="description" id="description" /></p>
                    <p><input type="submit"></p>
                    </form:form>
            </main>
            <jsp:include page="../fragments/footer.jsp" />
        </div>
    </body>
</html>
