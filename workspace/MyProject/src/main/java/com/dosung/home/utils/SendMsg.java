package com.dosung.home.utils;

import java.util.HashMap;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;

/**
 * @class ExampleSend
 * @brief This sample code demonstrate how to send sms through CoolSMS Rest API PHP
 */

// MSG를 보내기 위한 클래스
public class SendMsg {
	
  public static String send(String phone) {
	// 나의 coolsms 계정에 해당하는 key 값들
    String api_key = "NCSRXBDFPM2RBFQJ";		
    String api_secret = "ANOHJB7OHY8G9XVMYILO90HVK4XT7BTL";
    Message coolsms = new Message(api_key, api_secret);

    // 4 params(to, from, type, text) are mandatory. must be filled
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("to", phone);			// 받는 번호(가입시 입력한 번호)
    params.put("from", "01047206445");	// 메세지 발신자
    params.put("type", "SMS");			
    String key = Utils.getAuthCode(6);	// 6자리 인증번호 발급
    params.put("text", "인증번호 6자리는 " + key + " 입니다.");		
    params.put("app_version", "test app 1.2"); // application name and version

    try {
      JSONObject obj = (JSONObject) coolsms.send(params);
      System.out.println(obj.toString());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
	return key;
  }
}