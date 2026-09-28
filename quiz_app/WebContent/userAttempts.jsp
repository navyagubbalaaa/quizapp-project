<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
	if (session == null || session.getAttribute("username") == null) {
		response.sendRedirect("userLogin.html");
		return;
	}
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>My Quiz Attempts</title>
<style>
body {
	margin: 0;
	padding: 40px;
	background-color: #f3f6f9;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.container {
	max-width: 900px;
	margin: 0 auto;
	background: #ffffff;
	padding: 30px 40px;
	border-radius: 12px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

h1 {
	color: #1565c0;
	text-align: center;
	margin-bottom: 25px;
}

p {
	font-size: 1em;
	margin: 10px 0;
	color: #333;
}

.links {
	margin: 15px 0;
	text-align: center;
}

.links a {
	text-decoration: none;
	background-color: #1976d2;
	color: white;
	padding: 10px 18px;
	border-radius: 6px;
	margin: 0 10px;
	font-size: 0.95em;
	transition: background-color 0.3s;
}

.links a:hover {
	background-color: #0d47a1;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

table, th, td {
	border: 1px solid #ccc;
}

th {
	background-color: #e3f2fd;
	padding: 12px;
	text-align: left;
	color: #0d47a1;
}

td {
	padding: 10px;
	font-size: 0.95em;
}

.empty-msg {
	text-align: center;
	margin-top: 25px;
	font-style: italic;
	color: #999;
}

.total {
	margin-top: 15px;
	font-weight: bold;
	color: #444;
}
</style>
</head>
<body>
	<div class="container">
		<h1>My Quiz Attempts</h1>
		<c:set var="username" value="${sessionScope.username}" />
		<h2>
			Welcome, <span style="color: green;">   ${fn:toUpperCase(username)} </span>
		</h2>


		<div class="links">
			<a href="quiz">Take Quiz</a> <a href="logout">Logout</a>
		</div>

		<p class="total">Total Attempts: ${fn:length(attempts)}</p>

		<c:if test="${not empty attempts}">
			<table>
				<tr>
					<th>Date & Time</th>
					<th>Score</th>
				</tr>
				<c:forEach var="a" items="${attempts}">
					<tr>
						<td>${a.attemptTime}</td>
						<td>${a.totalScore}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

		<c:if test="${empty attempts}">
			<p class="empty-msg">No quiz attempts yet.</p>
		</c:if>
	</div>
</body>
</html>
