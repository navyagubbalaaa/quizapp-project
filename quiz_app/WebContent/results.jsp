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
  <title>Quiz Completed</title>
  <style>
    body {
      margin: 0;
      padding: 0;
      background-color: #fce4ec;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .result-box {
      background-color: #fff;
      padding: 40px 60px;
      border-radius: 12px;
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
      text-align: center;
    }

    h2 {
      color: #c2185b;
      font-size: 2em;
      margin-bottom: 20px;
    }

    p {
      font-size: 1.1em;
      margin: 15px 0;
    }

    strong {
      color: #ad1457;
    }

    a {
      display: inline-block;
      margin-top: 25px;
      text-decoration: none;
      background-color: #8e24aa;
      color: white;
      padding: 12px 25px;
      border-radius: 8px;
      font-size: 1em;
      transition: background-color 0.3s ease;
    }

    a:hover {
      background-color: #6a1b9a;
    }
  </style>
</head>
<body>
  <div class="result-box">
    <h2>Quiz Completed</h2>
    <p>Thank you, <strong><%= session.getAttribute("username") %></strong>.</p>
    <p>Your Score: <strong>${score}</strong></p>
    <a href="quiz">Try Again</a>
  </div>
</body>
</html>
