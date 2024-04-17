package com.dev.HiddenBATH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {

	@GetMapping("/loginForm")
	public String loginForm() {
		
		return "administration/login";
	}
	
	@PostMapping("/signinProcess")
	public String loginProcess() {
		
		return "administration/index";
	}
	
	@GetMapping({"/", "", "/index"})
	public String home(
			HttpServletRequest request,
			HttpSession session) {
		System.out.println(session.getAttribute("user"));
		return "front/index";
	}
	
	@PostMapping("/close")
	@ResponseBody
	public void close(
			@RequestBody String sessionValue,
			HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		System.out.println(sessionValue);
	}
	
	@GetMapping("/about")
	public String about() {
		
		return "front/about";
	}
	
	@GetMapping("/history")
	public String history() {
		
		return "front/history";
	}
	
	@GetMapping("/construction")
	public String construction() {
		
		return "front/construction";
	}
	
	@GetMapping("/constructionDetail")
	public String constructionDetail() {
		
		return "front/constructionDetail";
	}
	
	@GetMapping("/faq")
	public String faq() {
		
		return "front/faq";
	}
	
	@GetMapping("/notice")
	public String notice() {
		
		return "front/notice";
	}
	
	@GetMapping("/catalog")
	public String catalog() {
		
		return "front/catalog";
	}
	
	@GetMapping("/2022")
	public String catalog2022() {
		
		return "front/catalog/2022";
	}
	
	@GetMapping("/2023")
	public String catalog2023() {
		
		return "front/catalog/2023";
	}
	
	@GetMapping("/noticeDetail")
	public String noticeDetail() {
		
		return "front/noticeDetail";
	}
	
	@GetMapping("/contact")
	public String contact() {
		
		return "front/contact";
	}
	
	@GetMapping("/productList")
	public String productList() {
		
		return "front/productList";
	}
	
	@GetMapping("/productDetail")
	public String productDetail() {
		
		return "front/productDetail";
	}
	
	@GetMapping("/productDetailAdvanced")
	public String productDetailAdvanced() {
		
		return "front/productDetailAdvanced";
	}
	
	@GetMapping("/search")
	public String search() {
		
		return "front/search";
	}
	
	@GetMapping("/address")
	public String address() {
		
		return "front/address";
	}
}
