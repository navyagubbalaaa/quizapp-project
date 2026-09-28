package com.quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.quiz.dbutil.DBUtil;
import com.quiz.model.Question;

public class QuizDAO {
	public static List<Question> getAllQuestions() {
		List<Question> list = new ArrayList<>();
		
		try (Connection con = DBUtil.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM questions");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) { //6
				Question q = new Question(rs.getInt("id"), rs.getString("question_text"), rs.getString("option_a"),
						rs.getString("option_b"), rs.getString("option_c"), rs.getString("option_d"),
						rs.getString("correct_answer"), rs.getString("type"));
				
				list.add(q);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static int evaluateAnswers(HttpServletRequest request) {
		int score = 0;
		List<Question> questions = getAllQuestions(); // or retrieve only submitted ones

		for (Question q : questions) {
			String userAnswer = request.getParameter("q" + q.getId());
			String correctAnswer = q.getCorrectAnswer();

			if (userAnswer != null) {
				if (userAnswer.equalsIgnoreCase(correctAnswer)) {
					score++;
				}
			}
		}
		return score;
	}
}
