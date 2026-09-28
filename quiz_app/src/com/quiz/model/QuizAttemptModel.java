package com.quiz.model;

public class QuizAttemptModel {
	private int id;
	private java.sql.Timestamp attemptTime;
	private int totalScore;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.sql.Timestamp getAttemptTime() {
		return attemptTime;
	}

	public void setAttemptTime(java.sql.Timestamp attemptTime) {
		this.attemptTime = attemptTime;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

}
