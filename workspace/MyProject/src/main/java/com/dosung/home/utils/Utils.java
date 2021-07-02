package com.dosung.home.utils;

public class Utils {
	
	public static String getAuthCode(int length) {
		String code = "";	// 30 ~ 122까지의 숫자 발생시켜서 char로 변환 (아스키코드표)
		char[] characters = {
				'A', 'B', 'C', 'D', 'E', 'F', 
				'G', 'H', 'I', 'J', 'K', 'L',
				'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 
				'Y', 'Z', '0', '1', '2', '3',
				'4', '5', '6', '7', '8', '9',
				'!', '@', '#', '$'
		};
		for(int i = 0; i < length; i++) {
			code += characters[(int)(Math.random() * characters.length)];
		}
		
		return code;
	}
	
}
