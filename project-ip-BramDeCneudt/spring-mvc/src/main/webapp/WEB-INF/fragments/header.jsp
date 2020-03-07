    <%-- 
    Document   : header
    Created on : 18-Feb-2017, 16:54:39
    Author     : Bram
--%>

        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<header>
            <nav>
                <h1><spring:message code="title.welcome" /></h1>
                <ul class="nav nav-tabs">
                    <li role="presentation" class="${param.home}" ><a href="<c:url value="/index.htm"/>">Home</a></li>
                    <li role="presentation" class="${param.editportfolio}"><a href="<c:url value="/portfolio/admin/editportfolio.htm"/>">Edit Portfolio</a></li>
                    <li role="presentation" class="${param.addphoto}"><a href="<c:url value="/portfolio/admin/addphoto.htm"/>">Add Photo</a></li>
                    <li role="presentation" class="${param.addproject}"><a href="<c:url value="/portfolio/admin/addproject.htm"/>">Add Project</a></li>
                    <li role="presentation" class="${param.addtype}"><a href="<c:url value="/portfolio/admin/addtype.htm"/>">Add Type</a></li> 
                    <li role="presentation" class="${param.megaupload}"><a href="<c:url value="/portfolio/admin/megaupload.htm"/>">Mega Upload</a></li>
                    <li role="presentation"><a href="<c:url value="/login?logout"/>">logout</a></li>
                </ul>
            </nav>
        </header>
