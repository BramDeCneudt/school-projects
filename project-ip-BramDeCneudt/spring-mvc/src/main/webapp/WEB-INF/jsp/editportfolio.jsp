<%-- 
    Document   : template
    Created on : 18-Feb-2017, 16:49:40
    Author     : Bram
--%>

<%@include file="../fragments/java.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Portfolio</title>
        <jsp:include page="../fragments/links.jsp" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="../fragments/header.jsp" >
                <jsp:param name="editportfolio" value="active"/>
            </jsp:include>
        <main>
            
            <c:forEach items="${projects}" var="project">
                <section>
                    <c:url value="/portfolio/admin/editproject/" var="editproject" />
                    <c:url value="/portfolio/admin/deleteproject/" var="deleteproject"/>
                    <h2><c:out value="${project.name}"/> </h2> 
                    <p><a href="${editproject}<c:out value="${project.id}"/>">edit</a></p>
                    <p><a href="${deleteproject}<c:out value="${project.id}"/>">delete</a></p>
                    <div class="grid"> 
                        <c:forEach items="${project.photos}" var="photo" >
                            <c:url value="${prefix}${photo.imagePath}" var="path" />
                            <a href="<c:url value="/portfolio/getphoto/${photo.id}.htm?projectid=${project.id}"/>"><img class="grid-item photo" src="<c:out value="${path}" />" alt="<c:out value='${photo.name}'/>" /></a>
                        </c:forEach>
                    </div>
                    
                </section>
            </c:forEach>
        </main>
            <jsp:include page="../fragments/footer.jsp" />
        </div>
     <jsp:include page="../fragments/scripts.jsp" />
    </body>
</html>
