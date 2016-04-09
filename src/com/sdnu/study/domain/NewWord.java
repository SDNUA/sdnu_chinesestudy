package com.sdnu.study.domain;

public class NewWord {
	
	private String hanChar;
	private String pinyinChar;
	private String englishChar;
	private String fayinChar;
	public NewWord() {
		// TODO Auto-generated constructor stub
	}
	public NewWord(String hanChar,String pinyinChar,String englishChar) {
		this.hanChar=hanChar;
		this.pinyinChar=pinyinChar;
		this.englishChar=englishChar;
	}
	
	/**
	 * @return the hanChar
	 */
	public String getHanChar() {
		return hanChar;
	}
	/**
	 * @param hanChar the hanChar to set
	 */
	public void setHanChar(String hanChar) {
		this.hanChar = hanChar;
	}
	/**
	 * @return the pinyinChar
	 */
	public String getPinyinChar() {
		return pinyinChar;
	}
	/**
	 * @param pinyinChar the pinyinChar to set
	 */
	public void setPinyinChar(String pinyinChar) {
		this.pinyinChar = pinyinChar;
	}
	/**
	 * @return the englishChar
	 */
	public String getEnglishChar() {
		return englishChar;
	}
	/**
	 * @param englishChar the englishChar to set
	 */
	public void setEnglishChar(String englishChar) {
		this.englishChar = englishChar;
	}
	/**
	 * @return the fayinChar
	 */
	public String getFayinChar() {
		return fayinChar;
	}
	/**
	 * @param fayinChar the fayinChar to set
	 */
	public void setFayinChar(String fayinChar) {
		this.fayinChar = fayinChar;
	}

}
