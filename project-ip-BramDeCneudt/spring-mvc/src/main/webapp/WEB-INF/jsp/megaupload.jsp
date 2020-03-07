<%-- 
    Document   : template
    Created on : 18-Feb-2017, 16:49:40
    Author     : Bram
--%>

<%@include file="../fragments/java.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Megaupload</title>
        <jsp:include page="../fragments/links.jsp" >
            <jsp:param name="" value="" />
        </jsp:include>
    </head>
    <body>
        <div class="container">

            <jsp:include page="../fragments/header.jsp" >
                <jsp:param name="megaupload" value="active"/>
            </jsp:include>

            <div class="alert alert-info">
                <strong>Info!</strong> <spring:message code="info.megaupload" />
            </div>

            <div id="uploadzone">
                <p>upload here</p>
            </div>
            <input type="submit" id="uploadbutton">

            <div class="alert alert-info" id="messages">
                <strong>Messages:</strong>
            </div>

            <div class="dropzone-previews" id="previewzone">
                <p>
                    Select Project:
                    <select name="project" id="project">
                        <c:forEach var="project" items="${projects}">
                            <option value="${project.id}">${project.name}</option>
                        </c:forEach>
                    </select>
                </p>
            </div>

            <div class="preview-template" id="preview-template" style="display: none">
                <div class="dz-preview dz-file-preview">
                    <img data-dz-thumbnail />
                    <div class="dz-details">
                        <div class="dz-filename"><span data-dz-name></span></div>
                        <div class="dz-size" data-dz-size></div>
                    </div>
                    <div class="dz-error-message"><span data-dz-errormessage></span></div>
                    <p>naam:<input type="text" placeholder="name" name="name" class="name"></p>
                    <p>description: <input type="text" placeholder="description" name="description" class="description"></p>

                    <p> 
                        type:
                        <select name="type" class="type">
                            <c:forEach items="${types}" var="type">
                                <option value="<c:out value='${type.name}'/>" data-abbreviation="<c:out value='${type.abbreviation}'/>"><c:out value='${type.name}'/></option>
                            </c:forEach>
                        </select>
                    </p>
                </div>
            </div>


            <jsp:include page="../fragments/footer.jsp" />
        </div>
        <jsp:include page="../fragments/scripts.jsp" />
        <script src="<c:url value="/js/dropzone.js" />"></script>
        <script src="<c:url value="/js/fileupload.js" />"></script>
    </body>
</html>
