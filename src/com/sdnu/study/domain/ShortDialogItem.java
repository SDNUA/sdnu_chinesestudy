package com.sdnu.study.domain;

public class ShortDialogItem {
	
	
	public ShortDialogItem(String chinese,String pinyin) {
		// TODO Auto-generated constructor stub
		this.chinese=chinese;
		this.pinyin=pinyin;
	}
	public ShortDialogItem() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the chinese
	 */
	public String getChinese() {
		return chinese;
	}
	/**
	 * @param chinese the chinese to set
	 */
	public void setChinese(String chinese) {
		this.chinese = chinese;
	}
	/**
	 * @return the pinyin
	 */
	public String getPinyin() {
		return pinyin;
	}
	/**
	 * @param pinyin the pinyin to set
	 */
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	private  String chinese;
	private  String pinyin;

}
