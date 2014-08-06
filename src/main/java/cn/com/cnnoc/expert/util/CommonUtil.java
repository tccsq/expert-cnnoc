package cn.com.cnnoc.expert.util;

public class CommonUtil {

	// ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ»ònull
	public static boolean isNullOrBlank(String str) {
		if (null == str) {
			return true;
		} else if (str.trim().equals("")) {
			return true;
		}
		return false;
	}
}
