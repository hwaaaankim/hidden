package com.dev.HiddenBATH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping({"/","", "/inquiryManager"})
	public String adminIndex(){
	
		return "administration/index";
	}
	
	@GetMapping("/inquiryDetail")
	public String inquiryDetail() {
		
		return "administration/client/inquiryDetail";
	}
	
	@GetMapping("/orderManager")
	public String orderManeger() {
		
		return "administration/client/orderManager";
	}
	
	@GetMapping("/orderDetail")
	public String orderDetail() {
		
		return "administration/client/orderDetail";
	}
	
	@GetMapping("/agencyManager")
	public String agencyManager(){
	
		return "administration/client/agencyManager";
	}
	
	@GetMapping("/agencyInsertForm")
	public String agencyInsertForm(){
	
		return "administration/client/agencyInsertForm";
	}
	
	@GetMapping("/agencyDetail")
	public String agencyDetail(){
	
		return "administration/client/agencyDetail";
	}
	
	@GetMapping("/noticeManager")
	public String noticeManager() {
		
		return "administration/site/noticeManager";
	}
	
	@GetMapping("/noticeInsertForm")
	public String noticeInsertForm() {
		
		return "administration/site/noticeInsertForm";
	}
	
	@GetMapping("/noticeCategoryManager")
	public String noticeCategoryManager() {
		
		return "administration/site/noticeCategoryManager";
	}
	
	@GetMapping("/instagramManager")
	public String instagramManager() {
		
		return "administration/site/instagramManager";
	}
	
	@GetMapping("/instagramInsertForm")
	public String instagramInsertForm() {
		
		return "administration/site/instagramInsertForm";
	}
	
	@GetMapping("/faqManager")
	public String faqManager() {
		
		return "administration/site/faqManager";
	}
	
	@GetMapping("/faqInsertForm")
	public String faqInsertForm() {
		
		return "administration/site/faqInsertForm";
	}
	
	@GetMapping("/faqCategoryManager")
	public String faqCategoryManager() {
		
		return "administration/site/faqCategoryManager";
	}
	
	@GetMapping("/emailSendManager")
	public String emailSendManager() {
		
		return "administration/site/emailSendManager";
	}
	
	@GetMapping("/emailReceiveManager")
	public String emailReceiveManager() {
		
		return "administration/site/emailReceiveManager";
	}
	
	@GetMapping("/smsSendManager")
	public String smsSendManager() {
		
		return "administration/site/smsSendManager";
	}
	
	@GetMapping("/smsReceiveManager")
	public String smsReceiveManager() {
		
		return "administration/site/smsReceiveManager";
	}
	
	@GetMapping("/siteAccessManager")
	public String siteAccessManager() {
		
		return "administration/analytics/accessManager";
	}
	
	@GetMapping("/siteAccessDetail")
	public String siteAccessDetail() {
		
		return "administration/analytics/siteAccessDetail";
	}
	
	@GetMapping("/siteAnalytics")
	public String siteAnalytics() {
		
		return "administration/analytics/siteAnalytics";
	}
	
	
	
}
