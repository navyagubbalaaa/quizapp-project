package com.quiz.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quiz.dbutil.DBUtil;
import com.quiz.model.UserModel;

@WebServlet("/register")
public class UserRegistrationController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		UserModel user = new UserModel();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);

		try (Connection con = DBUtil.getConnection()) {
			String sql = "INSERT INTO users (username, email, password_hash) VALUES (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			
			ps.executeUpdate();
			
			response.sendRedirect("userLogin.html");
		} catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().println("Registration failed: " + e.getMessage());
		}
	}
}
