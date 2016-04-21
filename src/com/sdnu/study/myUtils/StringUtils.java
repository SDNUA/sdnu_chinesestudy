package com.sdnu.study.myUtils;

public class StringUtils {

	public static boolean isEmpty(String extraInfo) {
		if (extraInfo != null && !extraInfo.trim().equals(null)) {
			return true;
		}
		return false;
	}

}
