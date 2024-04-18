package com.dev.HiddenBATH.controller;

import org.apache.commons.codec.EncoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.HiddenBATH.service.EmailService;
import com.dev.HiddenBATH.service.SMSService;

@Controller
public class TestController {

	@Autowired
	EmailService emailService;
	
	@Autowired
	SMSService smsService;
	
	@GetMapping("/test")
	public String test() {
		
		return "front/test";
	}
	
	@GetMapping("/emailTest")
	@ResponseBody
	public String emailTest() throws MailSendException, InterruptedException {
		String[] address = {"teriwoo48@gmail.com"};
		String message = "안녕하세요 히든바스 입니다." + 
				"\r\n" + 
				"오늘도 저희 히든바스에 방문해 주셔 진심으로 감사드립니다. 고객님의 문의가 접수 되었으며, 빠른 시간 내에 확인하여 담당자가 연락 드리도록 하겠습니다." + 
				"\r\n" +
				"궁금하신 점이 있으신 경우 본 이메일 혹은 하단의 고객센터를 통해 문의 해 주시기 바랍니다." +
				"\r\n" +
				"=========================\r\n" +
				"[히든바스 SMS 방문하기]\r\n" + 
				"▼ INSTAGRAM\r\n" + 
				"https://www.instagram.com\r\n" + 
				"▼ FACEBOOK\r\n" + 
				"https://www.facebook.com\r\n" + 
				"=========================\r\n" + 
				"고객센터 : \r\n" + 
				"주소 : \r\n" + 
				"FAX : \r\n";
		emailService.sendEmails(address, "이메일 테스트", message);
		return "success";
	}
	
	@GetMapping("/smsTest")
	@ResponseBody
	public String smsTest() throws EncoderException {
		smsService.sendMessage("01038943849", "고객 문의가 발생하였습니다.");
		
		return "success";
	}
	
}
