<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>ChitChat - Home</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/chatbox.css" />
<link rel="stylesheet" href="css/chat.css">
</head>

<body>
	<div class="container">
		<header>
			<jsp:include page="fragments/header.jsp" />
		</header>
		<main>
		<aside>
			<section id="friends">
				<h2>Friends</h2>
				<table id="tableFriends">
					<tr>
						<th>name</th>
						<th>status</th>
					</tr>
					<p>click on a person to start a chat:</p>
					<c:forEach items="${persons}" var="person">
						<tr title="click to create a chatbox">
							<td class="username">${person.username}</td>
							<td>${person.status }</td>
						</tr>
					</c:forEach>
				</table>
				<p><a href="chitchat?action=chats">get chats</a></p>
			</section>
			<section class="" id="status">
				<h2>Status</h2>
				<h3 id="statusPerson">${person.status}</h3>
				<input type="text" name="status" id="statusInput">
				<button id="statusSubmit">submit</button>
			</section>

			<section>
				<h2>Add Friend</h2>
				<input type="text" id="addFriend">
				<button id="addFriendSubmit">submit</button>
			</section>
		</aside>
		<div id="home">
			<h2>welkom ${person.username}!</h2>
			<input type="hidden" value="${person.username}" id="usernamesession" />
			<p>login met admin en ww: root om alle aanwezige mensen te zien, deze hebben hetzelfde ww.</p>
			<h3>
				<a href="chitchat?action=logout">logout</a>
			</h3>
			<section id="loginSection">
				<h2>login</h2>
				<form action="chitchat?action=login" method="post">
					<input type="text" name="username" /> <input type="password"
						name="password" /> <input type="submit" />
				</form>
			</section>
			<div id="messages">
			
			</div>
			<section id="articles">
				

			</section>

		</div>
		</main>
		<footer> </footer>
	</div>
	<script type="text/javascript" src="js/1-persons.js"></script>
	<script type="text/javascript" src="js/2-websocket.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/3-chatbox.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
</body>

</html>