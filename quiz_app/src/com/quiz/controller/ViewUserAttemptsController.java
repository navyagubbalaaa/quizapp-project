package com.quiz.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quiz.dbutil.DBUtil;
import com.quiz.model.QuizAttemptModel;

@WebServlet("/userAttempts")
public class ViewUserAttemptsController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userId") == null) {
			response.sendRedirect("userLogin.html");
			return;
		}

		int userId = (int) session.getAttribute("userId");
		List<QuizAttemptModel> attempts = new ArrayList<>();

		try (Connection con = DBUtil.getConnection()) {
			String sql = "SELECT id, attempt_time, total_score FROM quiz_attempts WHERE user_id = ? ORDER BY attempt_time DESC";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				QuizAttemptModel attempt = new QuizAttemptModel();
				attempt.setId(rs.getInt("id"));
				attempt.setAttemptTime(rs.getTimestamp("attempt_time"));
				attempt.setTotalScore(rs.getInt("total_score"));
				attempts.add(attempt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("attempts", attempts);
		request.getRequestDispatcher("userAttempts.jsp").forward(request, response);
	}
}
