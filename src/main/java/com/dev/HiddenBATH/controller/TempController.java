package com.dev.HiddenBATH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class TempController {

	@GetMapping({"/", "", "/index"})
	public String home(
			HttpServletRequest request,
			HttpSession session) {
		return "temp/index";
	}
	
	@GetMapping("/catalog")
	public String catalog() {
		
		return "temp/catalog";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		
		return "administration/login";
	}
	
	@PostMapping("/signinProcess")
	public String loginProcess() {
		
		return "administration/index";
	}
}
