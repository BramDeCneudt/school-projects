<%-- 
    Document   : template
    Created on : 18-Feb-2017, 16:49:40
    Author     : Bram
--%>

<%@include file="../fragments/java.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>template</title>
        <jsp:include page="../fragments/links.jsp" >
            <jsp:param name="" value="" />
        </jsp:include>
    </head>
    <body>
        <div class="container">
            <jsp:include page="../fragments/header.jsp" />
        <main>
            <p>this is a template</p>
        </main>
            <jsp:include page="../fragments/footer.jsp" />
        </div>
    </body>
</html>
