package com.quiz.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quiz.dao.QuizDAO;
import com.quiz.dbutil.DBUtil;
import com.quiz.model.Question;

@WebServlet("/quiz")
public class QuizController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Question> questions = QuizDAO.getAllQuestions();
		
		for (Question q : questions) {
			System.out.println(q.getQuestionText() + "\t" + q.getCorrectAnswer());
		}
		
		request.setAttribute("questions", questions);
		request.getRequestDispatcher("quiz.jsp").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Session
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userId") == null) {
			response.sendRedirect("userLogin.html");
			return;
		}

		int userId = (int) session.getAttribute("userId");
		int score = QuizDAO.evaluateAnswers(request); // 6
//		int score = QuizDAO.evaluateAnswers(request);

		// Save quiz attempt
		try (Connection con = DBUtil.getConnection()) {
			String sql = "INSERT INTO quiz_attempts (user_id, total_score) VALUES (?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, score);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("score", score);
		request.getRequestDispatcher("results.jsp").forward(request, response);

//		request.getRequestDispatcher("userDetails.jsp").forward(request, response);
	}
}