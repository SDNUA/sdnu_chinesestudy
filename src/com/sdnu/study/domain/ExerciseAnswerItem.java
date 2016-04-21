package com.sdnu.study.domain;

public class ExerciseAnswerItem {
	private String answerFlag;
	private String answer;
	private String answerA="A";
	private String answerB="B";
	private String answerC="C";
	/**
	 * @return the answerA
	 */
	public String getAnswerA() {
		return answerA;
	}
	/**
	 * @return the answerB
	 */
	public String getAnswerB() {
		return answerB;
	}
	/**
	 * @return the answerC
	 */
	public String getAnswerC() {
		return answerC;
	}
	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}


	/**
	 * @return the answerFlag
	 */
	public String getAnswerFlag() {
		return answerFlag;
	}
	/**
	 * @param answerFlag the answerFlag to set
	 */
	public void setAnswerFlag(String answerFlag) {
		this.answerFlag = answerFlag;
	}




}
