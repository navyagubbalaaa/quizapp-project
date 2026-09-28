package com.quiz.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quiz.dbutil.DBUtil;

@WebServlet("/add-question")
public class AddQuestionController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String text = request.getParameter("questionText");
		String type = request.getParameter("type");
		String correct = request.getParameter("correctAnswer");

		String a = null, b = null, c = null, d = null;

		if ("MCQ".equalsIgnoreCase(type)) {
			a = request.getParameter("optionA");
			b = request.getParameter("optionB");
			c = request.getParameter("optionC");
			d = request.getParameter("optionD");
		} else if ("TF".equalsIgnoreCase(type)) {
			a = "True";
			b = "False";
			c = null;
			d = null;
		}

		try (Connection con = DBUtil.getConnection()) {
			String sql = "INSERT INTO questions (question_text, option_a, option_b, option_c, option_d, correct_answer, type) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, text);
			ps.setString(2, a);
			ps.setString(3, b);
			ps.setString(4, c); // Will be NULL if TF
			ps.setString(5, d); // Will be NULL if TF
			ps.setString(6, correct);
			ps.setString(7, type);

			ps.executeUpdate();
			response.sendRedirect("add-question.html"); // Optional: Redirect to success page
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Error: " + e.getMessage());
		}
	}
}
