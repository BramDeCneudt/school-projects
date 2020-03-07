<%@include file="fragments/java.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>Welcome!</title>
<%@include file="fragments/links.jsp"%>
<link rel="stylesheet" href="css/chat.css" />
</head>

<body>
	<div class="container">
		<header>
			<jsp:include page="fragments/header.jsp" />
		</header>
		<main>
		<section>
			<h2>This is a chat app</h2>
			<p>
				<input type="hidden" id="chatid" value=${param.id } />
			</p>
			<p>
				<input type="hidden" id="name" value=${username} />
			</p>
			<div class="chat">
				<div class="chat-history">
					<ul class="chat-ul">
						
					</ul>
				</div>
			<p>
				<input id="textfield" type="text" placeholder="text here..." /> <input
					type="submit" id="sendmessage" />
			</p>
			</div>
		</section>



		</main>
		<footer>
			<jsp:include page="fragments/footer.jsp" />
		</footer>
	</div>
</body>
<script src="js/jquery.js"></script>
<script src="js/3-chat.js"></script>

</html>
