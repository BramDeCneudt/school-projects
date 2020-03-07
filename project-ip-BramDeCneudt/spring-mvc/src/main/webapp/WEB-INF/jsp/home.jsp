<%-- 
    Document   : template
    Created on : 18-Feb-2017, 16:49:40
    Author     : Bram
--%>

<%@include file="../fragments/java.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Home</title>
        <jsp:include page="../fragments/links.jsp" />
        <link rel="stylesheet" href="<c:url value="/css/home.css"/>">
        <link  href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css" rel="stylesheet">


    </head>
    <body>
        <div class="container">
            <nav>
                <h1><spring:message code="title.welcome" /></h1>
                <c:url value="/portfolio/admin/editportfolio.htm" var="login"/>
                <a href="${login}">inlog</a>
            </nav>
            <main>
                <h2>${project.name}</h2>
                <div id="portfolio">
                    <aside>
                        <h3>projects</h3>
                        <ol>
                            <c:forEach items="${projects}" var="project" >
                                <c:url value="/portfolio/all/getproject/" var="url"/>
                                <li><a href="<c:out value='${url}${project.id}'/>"><c:out value="${project.name}"/></a></li>
                            </c:forEach>
                            
                        </ol>
                    </aside>
                    <div id="wrapper">
                        <div class="fotorama" data-nav="thumbs" data-allowfullscreen="native" data-hash="true" data-loop="true">
                            <c:if test="${not empty project.photos}">
                                <c:url value="${prefix}${project.photos[0].imagePath}" var="imagepath" />
                                <div data-img="${imagepath}" data-caption="<c:out value="${project.description}"/>"><c:out value="${project.name}"/></div>
                            </c:if>
                            
                            <c:forEach items="${project.photos}" var="photo" >
                                <c:url value="${prefix}${photo.imagePath}" var="imagepath" />
                                <a href="${imagepath}" data-caption="${photo.description}" ></a>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </main>
            <jsp:include page="../fragments/footer.jsp" />
        </div>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.js"></script> 
    </body>
</html>
