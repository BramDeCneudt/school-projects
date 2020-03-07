<%-- 
    Document   : template
    Created on : 18-Feb-2017, 16:49:40
    Author     : Bram
--%>

<%@include file="../fragments/java.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Get Colors</title>
        <jsp:include page="../fragments/links.jsp" >
            <jsp:param name="" value="" />
        </jsp:include>
    </head>
    <body>
        <div class="container">
            <jsp:include page="../fragments/header.jsp" />
        <main>
            <h3>Raw Data</h3>
            <c:out value="${colors}"/>
        </main>
            <jsp:include page="../fragments/footer.jsp" />
        </div>
    </body>
</html>
