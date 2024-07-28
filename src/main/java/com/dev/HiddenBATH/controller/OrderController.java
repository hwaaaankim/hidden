package com.dev.HiddenBATH.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.HiddenBATH.model.order.MirrorOrder;

@Controller
@RequestMapping("/order")
public class OrderController {

	
	@GetMapping({"", "/"})
	public String mirrorHome() {
		
		return "front/order/mirror/index";
	}
	
	@GetMapping("/information")
	public String mirrorInformation() {
		
		return "front/order/mirror/information";
	}
	
	@PostMapping("/mirrorOrderInsert")
	@ResponseBody
	public String mirrorOrderInsert(
			MirrorOrder order
			) {
		
		StringBuffer sb = new StringBuffer();
		String msg = "문의 주셔서 감사합니다. 빠르게 연락드리도록 하겠습니다.";
		
		sb.append("alert('" + msg + "');");
		sb.append("location.href='/index'");
		sb.append("</script>");
		sb.insert(0, "<script>");
		
		return sb.toString();
	}
	
	@GetMapping("/top")
	public String topHome() {
		
		return "front/order/top/index";
	}
	
	@GetMapping("/topInformation")
	public String topInformation() {
		
		return "front/order/top/information";
	}
	
	@GetMapping("/low")
	public String lowHome() {
		
		return "front/order/low/index";
	}
	
	@GetMapping("/lowInformation")
	public String lowInformation() {
		
		return "front/order/low/information";
	}
	
	@GetMapping("/flap")
	public String flapHome() {
		
		return "front/order/flap/index";
	}
	
	@GetMapping("/flapInformation")
	public String flapInformation() {
		
		return "front/order/flap/information";
	}
	
	@GetMapping("/slide")
	public String slideHome() {
		
		return "front/order/slide/index";
	}
	
	@GetMapping("/slideInformation")
	public String slideInformation() {
		
		return "front/order/slide/information";
	}
}
