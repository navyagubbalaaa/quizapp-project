<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    if (session == null || session.getAttribute("username") == null) {
        response.sendRedirect("userLogin.html");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Quiz</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f1f8e9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            padding-top: 40px;
        }

        .container {
            background-color: #fff;
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 800px;
        }

        h2 {
            color: #2e7d32;
            text-align: center;
            margin-bottom: 20px;
        }

        .welcome {
            text-align: right;
            margin-bottom: 30px;
            font-size: 0.95em;
        }

        .welcome a {
            color: #c62828;
            text-decoration: none;
            font-weight: bold;
        }

        .question {
            margin-bottom: 25px;
        }

        .question p {
            font-weight: bold;
            margin-bottom: 8px;
        }

        .question input[type="radio"] {
            margin-right: 8px;
        }

        hr {
            border: none;
            height: 1px;
            background-color: #ccc;
            margin: 25px 0;
        }

        input[type="submit"] {
            display: block;
            margin: 30px auto 0;
            padding: 12px 25px;
            background-color: #388e3c;
            color: white;
            font-size: 1em;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #1b5e20;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Online Quiz</h2>
        <div class="welcome">
            Welcome, <%= session.getAttribute("username") %> | <a href="logout">Logout</a>
        </div>

        <form method="post" action="quiz">
            <c:forEach var="q" items="${questions}" varStatus="status">
                <div class="question">
                    <p>Q${status.index + 1}. ${q.questionText}</p>

                    <!-- True/False options -->
                    <c:if test="${q.type == 'TF'}">
                        <label><input type="radio" name="q${q.id}" value="True" required> True</label><br>
                        <label><input type="radio" name="q${q.id}" value="False"> False</label><br>
                    </c:if>

                    <!-- MCQ options -->
                    <c:if test="${q.type == 'MCQ'}">
                        <label><input type="radio" name="q${q.id}" value="A" required> ${q.optionA}</label><br>
                        <label><input type="radio" name="q${q.id}" value="B"> ${q.optionB}</label><br>
                        <label><input type="radio" name="q${q.id}" value="C"> ${q.optionC}</label><br>
                        <label><input type="radio" name="q${q.id}" value="D"> ${q.optionD}</label><br>
                    </c:if>
                </div>
                <hr>
            </c:forEach>

            <input type="submit" value="Submit Quiz">
        </form>
    </div>
</body>
</html>
