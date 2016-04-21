package com.sdnu.study.domain;

public class Version {
	private int versionCode=1;
	private String versionMsg;
	private String name;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the versionCode
	 */
	public int getVersionCode() {
		return versionCode;
	}
	/**
	 * @param versionCode the versionCode to set
	 */
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	/**
	 * @return the versionMsg
	 */
	public String getVersionMsg() {
		return versionMsg;
	}
	/**
	 * @param versionMsg the versionMsg to set
	 */
	public void setVersionMsg(String versionMsg) {
		this.versionMsg = versionMsg;
	}
	
	@Override
	public String toString() {
		
		return "Name:"+name+"Code:"+versionCode+"  MSG:"+versionMsg;
	}


}
