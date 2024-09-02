package com.quizcard;

public class QuizCard {
	private String question;
	private String answer;
	private String falseAnswer_1;
	private String falseAnswer_2;
	
	public QuizCard(String question, String answer, String falseAnswer_1, String falseAnswer_2) {
		this.question = question;
		this.answer = answer;
		this.falseAnswer_1 = falseAnswer_1;
		this.falseAnswer_2 = falseAnswer_2;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getFalseAnswer_1() {
		return falseAnswer_1;
	}
	public void setFalseAnswer_1(String falseAnswer_1) {
		this.falseAnswer_1 = falseAnswer_1;
	}
	public String getFalseAnswer_2() {
		return falseAnswer_2;
	}
	public void setFalseAnswer_2(String falseAnswer_2) {
		this.falseAnswer_2 = falseAnswer_2;
	}
}