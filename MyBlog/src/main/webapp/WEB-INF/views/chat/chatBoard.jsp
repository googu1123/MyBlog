<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="../common/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>WebSockets Demo</title>

	<link rel="stylesheet" href="chat" />
	<link rel="stylesheet" href="assets/chat/style.css" />
</head>
<body>
	<div id="page-wrapper">
		<h1>WebSockets Demo</h1>

		<div id="status">Connecting...</div>

		<ul id="messages"></ul>

		<form id="message-form" action="#" method="post">
			<textarea id="message" placeholder="Write your message here..." required wrap="hard"></textarea>
			<button type="submit">Send Message</button>
			<button type="button" id="close">Close Connection</button>
		</form>
	</div>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/chat/app.js"></script>
</body>
</html>
