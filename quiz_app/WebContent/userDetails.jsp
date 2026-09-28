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
<title>User Dashboard</title>
<style>
  body {
    margin: 0;
    padding: 0;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #e3f2fd;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
  }

  .dashboard {
    background-color: #ffffff;
    padding: 40px 60px;
    border-radius: 12px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
    text-align: center;
  }

  .dashboard h2 {
    color: #0d47a1;
    margin-bottom: 30px;
  }

  .dashboard p {
    margin: 20px 0;
  }

  a.button {
    display: inline-block;
    text-decoration: none;
    background-color: #1976d2;
    color: white;
    padding: 12px 25px;
    border-radius: 8px;
    font-size: 1em;
    transition: background-color 0.3s ease;
  }

  a.button:hover {
    background-color: #0d47a1;
  }
</style>
</head>
<body>
  <div class="dashboard">
    <h2>Welcome, <%= session.getAttribute("username") %>!</h2>

    <p>
      <a href="quiz" class="button">Take Quiz</a>
    </p>
    <p>
      <a href="userAttempts" class="button">View My Attempts</a>
    </p>
  </div>
</body>
</html>
