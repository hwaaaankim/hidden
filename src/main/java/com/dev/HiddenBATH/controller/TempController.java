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
	
	@GetMapping("/2022")
	public String catalog2022() {
		
		return "front/catalog/2022";
	}
	
	@GetMapping("/2023")
	public String catalog2023() {
		
		return "front/catalog/2023";
	}
	
	@GetMapping("/2023New")
	public String catalog2023New() {
		
		return "front/catalog/2023New";
	}
	
	@GetMapping("/2024")
	public String catalog2024() {
		
		return "front/catalog/2024";
	}
}
