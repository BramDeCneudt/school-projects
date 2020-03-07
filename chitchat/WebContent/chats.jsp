<%@include file="fragments/java.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Welcome!</title>
<%@include file="fragments/links.jsp"%>
</head>
<body>
	<div class="container">
		<header>
			<jsp:include page="fragments/header.jsp" />
		</header>
		<main>
		<section>
			<h2>This is a chats app</h2>
			<div class="row">
				<c:forEach items="${chats}" var="chat">
					<div class="col-md-2 chatdiv">
						<p>Chit {</p>
						<a class=""
							href='<c:url value="/chitchat?action=chat&id=${chat.id}" />'>
							<p>${chat.name}</p>
							<p>textMessage</p>
						</a>
						<p>} Chat</p>
					</div>
				</c:forEach>
			</div>
		</section>
		</main>
		<footer>
			<jsp:include page="fragments/footer.jsp" />
		</footer>
	</div>
</body>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/1-addperson.js"></script>
</html>