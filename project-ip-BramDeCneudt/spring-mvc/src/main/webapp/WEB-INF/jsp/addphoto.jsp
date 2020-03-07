<%-- 
    Document   : template
    Created on : 18-Feb-2017, 16:49:40
    Author     : Bram
--%>

<%@include file="../fragments/java.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Photo Edit</title>
        <jsp:include page="../fragments/links.jsp" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="../fragments/header.jsp" >
                <jsp:param name="addphoto" value="active"/>
            </jsp:include>

            <h2>New Photo</h2>
            <c:url value="/portfolio/admin/addphoto.htm" var="target"/>
            <form:form modelAttribute="photo" action="${target}" method="post" enctype="multipart/form-data">
                <form:hidden path="id" id="id" />
                <input type="hidden" value="${param.projectid}" name="oldproject">
                <p>
                    project: 
                    <select name="project" >
                        <c:forEach items="${projects}" var="project">
                            <c:if test="${project.id == param.projectid}" >
                                <c:set var="selected" value="selected" />
                            </c:if>
                            <option value="${project.id}" ${selected}><c:out value="${project.name}"/></option>
                            <c:set var="selected" value="" />
                        </c:forEach>
                    </select>
                </p>
                <p><form:errors path="name" cssClass="alert alert-danger"/></p>
                <p>naam: <form:input path="name" id="name" /></p>
                <p><form:errors path="description" cssClass="alert alert-danger"/></p>
                <p>description: <form:input path="description"/></p>
                <p><form:errors path="type" cssClass="alert alert-danger"/></p>
                <p>type: <form:select path="type" items="${types}"itemValue="name" itemLabel="name" /></p>
                <p><form:errors path="imagePath" cssClass="alert alert-danger"/></p>
                <p><input type="file" name="image" accept=".jpg" id="upload"></p>
                <p><input type="submit"></p>
                </form:form>
                <c:if test="${photo.id > 0}" >
                <a href="<c:url value="/portfolio/admin/deletephoto/${photo.id}/${param.projectid}.htm"/>">delete</a>
                <a href="<c:url value="/portfolio/admin/getcolors/${photo.id}.htm"/>">get colors</a>
            </c:if>

            <spring:hasBindErrors name="photo">
                <c:out value="${error}" />
                <c:if test="${errors.hasFieldErrors('error')}">
                    <c:set var="errorClass" value="error" />
                </c:if>
            </spring:hasBindErrors>
            <c:out value='${errorClass}' />
            <jsp:include page="../fragments/footer.jsp" />
        </div>
    </body>
</html>
