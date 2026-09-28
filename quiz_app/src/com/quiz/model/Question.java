package com.quiz.model;

public class Question {
	private int id;
	private String questionText;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String correctAnswer;
	private String type; // "MCQ" or "TF"

	// Constructor
	public Question(int id, String questionText, String optionA, String optionB, String optionC, String optionD,
			String correctAnswer, String type) {
		this.id = id;
		this.questionText = questionText;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.correctAnswer = correctAnswer;
		this.type = type;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public String getOptionA() {
		return optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public String getType() {
		return type;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public void setType(String type) {
		this.type = type;
	}
}