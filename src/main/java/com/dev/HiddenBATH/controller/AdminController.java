package com.dev.HiddenBATH.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dev.HiddenBATH.service.ConstructionService;
import com.dev.HiddenBATH.service.GalleryService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	GalleryService galleryService;
	
	@Autowired
	ConstructionService constructionService;
	
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
	
	@GetMapping("/galleryManager")
	public String galleryManager() {
		
		return "administration/site/galleryManager";
	}
	
	@GetMapping("/galleryInsertForm")
	public String galleryInsertForm() {
		
		return "administration/site/galleryInsertForm";
	}
	
	@PostMapping("/galleryInsert")
	@ResponseBody
	public String galleryInsert(
			MultipartFile thumb,
			MultipartFile gallery
			) throws IOException {
		galleryService.insertGallery(thumb, gallery);
		StringBuffer sb = new StringBuffer();
		String msg = "갤러리 이미지가 등록 되었습니다.";
		
		sb.append("alert('" + msg + "');");
		sb.append("location.href='/admin/galleryInsertForm'");
		sb.append("</script>");
		sb.insert(0, "<script>");
		
		return sb.toString();
	}
	
	
	@GetMapping("/exampleManager")
	public String exampleManager() {
		
		return "administration/site/exampleManager";
	}
	
	@GetMapping("/exampleInsertForm")
	public String exampleInsertForm() {
		
		return "administration/site/exampleInsertForm";
	}
	
	@PostMapping("/exampleInsert")
	@ResponseBody
	public String exampleInsert(
			MultipartFile thumb,
			MultipartFile construction
			) throws IOException {
		constructionService.insertConstruction(thumb, construction);
		StringBuffer sb = new StringBuffer();
		String msg = "시공사례 이미지가 등록 되었습니다.";
		
		sb.append("alert('" + msg + "');");
		sb.append("location.href='/admin/exampleInsertForm'");
		sb.append("</script>");
		sb.insert(0, "<script>");
		
		return sb.toString();
	}
	
//	@GetMapping("/faqManager")
//	public String faqManager() {
//		
//		return "administration/site/faqManager";
//	}
//	
//	@GetMapping("/faqInsertForm")
//	public String faqInsertForm() {
//		
//		return "administration/site/faqInsertForm";
//	}
//	
//	@GetMapping("/faqCategoryManager")
//	public String faqCategoryManager() {
//		
//		return "administration/site/faqCategoryManager";
//	}
	
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
