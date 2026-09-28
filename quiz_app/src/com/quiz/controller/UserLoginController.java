package com.quiz.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quiz.dbutil.DBUtil;

@WebServlet("/login")
public class UserLoginController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try (Connection con = DBUtil.getConnection()) {
			String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password); 

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// Set session attribute
				HttpSession session = request.getSession();
				// session.setAttribute("username", username);

				session.setAttribute("userId", rs.getInt("id")); // store user ID from DB
				session.setAttribute("username", username);

				// Redirect to user details
				response.sendRedirect("userDetails.jsp");

			} else {
				response.getWriter().println("<h3>Invalid username or password</h3>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Login error: " + e.getMessage());
		}
	}
}
