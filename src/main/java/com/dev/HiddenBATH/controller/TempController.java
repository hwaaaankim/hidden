package com.dev.HiddenBATH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/temp")
public class TempController {

	@GetMapping("/index")
	public String home() {
		return "temp/index";
	}
	
	@GetMapping("/productList")
	public String productList() {
		
		return "temp/productList";
	}
	
	@GetMapping("/productDetail")
	public String productDetail() {
		
		return "temp/productDetail";
	}
	
	@GetMapping("/findAgency")
	public String findAgency() {
		
		return "temp/findAgency";
	}
	
}
