package com.supermy.core.web.tags;

class Utils {
	
	public static String BLOCK = "_myweb_jsp_layout_";
	
	static String getOverrideVariableName(String name) {
		return BLOCK + name;
	}
	
	
}
