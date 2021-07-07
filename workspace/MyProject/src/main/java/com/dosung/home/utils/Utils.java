package com.dosung.home.utils;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public class Utils {
	
	// 문자 인증번호 발생함수
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
	
	// ip 추출해주는 함수
	public static String getClientIpAddr(MultipartHttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");
	 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	 
	    return ip;
	}
	
}
