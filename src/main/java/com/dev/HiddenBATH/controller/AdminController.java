package com.dev.HiddenBATH.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dev.HiddenBATH.model.product.BigSort;
import com.dev.HiddenBATH.model.product.MiddleSort;
import com.dev.HiddenBATH.model.product.ProductTag;
import com.dev.HiddenBATH.repository.ProductBigSortRepository;
import com.dev.HiddenBATH.repository.ProductColorRepository;
import com.dev.HiddenBATH.repository.ProductMiddleSortRepository;
import com.dev.HiddenBATH.repository.ProductSizeRepository;
import com.dev.HiddenBATH.repository.ProductTagRepository;
import com.dev.HiddenBATH.service.ProductBigSortService;
import com.dev.HiddenBATH.service.ProductColorService;
import com.dev.HiddenBATH.service.ProductMiddleSortService;
import com.dev.HiddenBATH.service.ProductSizeService;
import com.dev.HiddenBATH.service.ProductTagService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	ProductSizeRepository productSizeRepository;
	
	@Autowired
	ProductSizeService productSizeService;
	
	@Autowired
	ProductColorRepository productColorRepository;
	
	@Autowired
	ProductColorService productColorService;
	
	@Autowired
	ProductBigSortRepository productBigSortRepository;
	
	@Autowired
	ProductBigSortService productBigSortService;
	
	@Autowired
	ProductMiddleSortRepository productMiddleSortRepository;
	
	@Autowired
	ProductMiddleSortService productMiddleSortService;
	
	@Autowired
	ProductTagRepository productTagRepository;
	
	@Autowired
	ProductTagService productTagService;
	
	
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
	
	@GetMapping("/productCategoryManager")
	public String productCategoryManager(
			Model model
			) {
		model.addAttribute("middle", productMiddleSortRepository.findAll());
		model.addAttribute("big", productBigSortRepository.findAll());
		return "administration/product/productCategoryManager";
	}

	@PostMapping("/productBigSortInsert")
	@ResponseBody
	public String productBigSortInsert(
			String bigCategories
			) {
		String[] arr = bigCategories.split(",");
		
		for(String big : arr) {
			int index = 1;
			if(productBigSortRepository.findFirstIndex().isPresent()) {
				index = productBigSortRepository.findFirstIndex().get() + 1;
			}
			BigSort b = new BigSort();
			b.setBigSortIndex(index);
			b.setName(big);
			productBigSortRepository.save(b);
		}
		StringBuffer sb = new StringBuffer();
		String msg = "대분류가 등록 되었습니다.";
		
		sb.append("alert('" + msg + "');");
		sb.append("location.href='/admin/productCategoryManager'");
		sb.append("</script>");
		sb.insert(0, "<script>");
		
		return sb.toString();
	}
	
	@PostMapping("/productBigSortDelete")
	@ResponseBody
	public String productBigSortDelete(
			@RequestParam Long[] bigSortDeleteSelect
			) {
		try {
			productBigSortService.deleteProductBigSort(bigSortDeleteSelect);
			StringBuffer sb = new StringBuffer();
			String msg = "대분류가 삭제 되었습니다.";
			
			sb.append("alert('" + msg + "');");
			sb.append("location.href='/admin/productCategoryManager'");
			sb.append("</script>");
			sb.insert(0, "<script>");
			
			return sb.toString();
		}catch(Exception e) {
			StringBuffer sb = new StringBuffer();
			String msg = "대분류가 삭제 되었습니다.";
			
			sb.append("alert('" + msg + "');");
			sb.append("location.href='/admin/productCategoryManager'");
			sb.append("</script>");
			sb.insert(0, "<script>");
			
			return sb.toString();
		}
	}
	
	@PostMapping("/productMiddleSortInsert")
	@ResponseBody
	public String productMiddleSortInsert(
			Long bigSortId,
			String middleCategories
			) {
		String[] arr = middleCategories.split(",");
		
		for(String middle : arr) {
			int index = 1;
			if(productMiddleSortRepository.findFirstIndex().isPresent()) {
				index = productMiddleSortRepository.findFirstIndex().get() + 1;
			}
			MiddleSort m = new MiddleSort();
			m.setBigSort(productBigSortRepository.findById(bigSortId).get());
			m.setMiddleSortIndex(index);
			m.setName(middle);
			productMiddleSortRepository.save(m);
		}
		StringBuffer sb = new StringBuffer();
		String msg = "중분류가 등록 되었습니다.";
		
		sb.append("alert('" + msg + "');");
		sb.append("location.href='/admin/productCategoryManager'");
		sb.append("</script>");
		sb.insert(0, "<script>");
		
		return sb.toString();
	}
	
	@PostMapping("/productMiddleSortDelete")
	@ResponseBody
	public String productMiddleSortDelete(
			@RequestParam Long[] middleSortDeleteSelect
			) {
		try {
			productMiddleSortService.deleteProductMiddleSort(middleSortDeleteSelect);
			StringBuffer sb = new StringBuffer();
			String msg = "중분류가 삭제 되었습니다.";
			
			sb.append("alert('" + msg + "');");
			sb.append("location.href='/admin/productCategoryManager'");
			sb.append("</script>");
			sb.insert(0, "<script>");
			
			return sb.toString();
		}catch(Exception e) {
			StringBuffer sb = new StringBuffer();
			String msg = "중분류가 삭제 되었습니다.";
			
			sb.append("alert('" + msg + "');");
			sb.append("location.href='/admin/productCategoryManager'");
			sb.append("</script>");
			sb.insert(0, "<script>");
			
			return sb.toString();
		}
	}
	
	@GetMapping("/productManager")
	public String productManager() {
		
		return "administration/product/productManager";
	}
	
	@GetMapping("/productInsertForm")
	public String productInsertForm() {
		
		return "administration/product/productInsertForm";
	}
	
	@GetMapping("/productDetail")
	public String productDetail() {
	
		return "administration/product/productDetail";
	}
	
	@GetMapping("/productTagManager")
	public String productTagManager(
			Model model
			) {
		model.addAttribute("tag", productTagRepository.findAll());
		return "administration/product/productTagManager";
	}
	
	@PostMapping("/productTagInsert")
	@ResponseBody
	public String productTagInsert(
			String productTags
			) {
		String[] arr = productTags.split(",");
		
		for(String tag : arr) {
			if(tag.contains("&amp;")) {
				tag = tag.replace("&amp;", "&");
			}
			ProductTag t = new ProductTag();
			t.setProductTagText(tag);
			productTagRepository.save(t);
		}
		StringBuffer sb = new StringBuffer();
		String msg = "제품 태그가 등록 되었습니다.";
		
		sb.append("alert('" + msg + "');");
		sb.append("location.href='/admin/productTagManager'");
		sb.append("</script>");
		sb.insert(0, "<script>");
		
		return sb.toString();
	}
	
	@PostMapping("/productTagDelete")
	@ResponseBody
	public String productTagDelete(
			@RequestParam Long[] tagDeleteSelect
			) {
		try {
			productTagService.deleteProductTag(tagDeleteSelect);
			StringBuffer sb = new StringBuffer();
			String msg = "태그가 삭제 되었습니다.";
			
			sb.append("alert('" + msg + "');");
			sb.append("location.href='/admin/productTagManager'");
			sb.append("</script>");
			sb.insert(0, "<script>");
			
			return sb.toString();
		}catch(Exception e) {
			StringBuffer sb = new StringBuffer();
			String msg = "태그가 삭제 되었습니다.";
			
			sb.append("alert('" + msg + "');");
			sb.append("location.href='/admin/productTagManager'");
			sb.append("</script>");
			sb.insert(0, "<script>");
			
			return sb.toString();
		}
	}
	
	@GetMapping("/productIndexManager")
	public String productIndexManager() {
		
		return "administration/product/productIndexManager";
	}
	
	@GetMapping("/productOverallManager")
	public String productOverallManager() {
		
		return "administration/product/productOverallManager";
	}
	
	@GetMapping("/productAddManager")
	public String productAddManager() {
		
		return "administration/product/productAddManager";
	}
	
	@GetMapping("/productResetManager")
	public String productResetManager() {
		
		return "administration/product/productResetManager";
	}
	
	@GetMapping("/productSizeManager")
	public String productSizeManager(
			Model model
			) {
		model.addAttribute("sizes", productSizeRepository.findAll());
		return "administration/product/productSizeManager";
	}
	
	@PostMapping("/productSizeInsert")
	@ResponseBody
	public String productSizeInsert(
//			@RequestParam(value="size") String[] size
			String size
			) {
		String[] arr = size.split(",");
		productSizeService.insertProductSize(arr);
		StringBuffer sb = new StringBuffer();
		String msg = "사이즈가 등록 되었습니다.";
		
		sb.append("alert('" + msg + "');");
		sb.append("location.href='/admin/productSizeManager'");
		sb.append("</script>");
		sb.insert(0, "<script>");
		
		return sb.toString();
	}
	
	@PostMapping("/productSizeDelete")
	@ResponseBody
	public String productSizeDelete(
			@RequestParam Long[] text
			) {
		try {
			productSizeService.deleteProductSize(text);
			StringBuffer sb = new StringBuffer();
			String msg = "사이즈가 삭제 되었습니다.";
			
			sb.append("alert('" + msg + "');");
			sb.append("location.href='/admin/productSizeManager'");
			sb.append("</script>");
			sb.insert(0, "<script>");
			
			return sb.toString();
		}catch(Exception e) {
			StringBuffer sb = new StringBuffer();
			String msg = "사이즈가 삭제 되었습니다.";
			
			sb.append("alert('" + msg + "');");
			sb.append("location.href='/admin/productSizeManager'");
			sb.append("</script>");
			sb.insert(0, "<script>");
			
			return sb.toString();
		}
	}
	
	
	@GetMapping("/productColorManager")
	public String productColorManager(
			Model model
			) {
		
		model.addAttribute("colors", productColorRepository.findAll());
		return "administration/product/productColorManager";
	}
	
	@PostMapping("/productColorInsert")
	@ResponseBody
	public String productColorInsert(
			String colorName,
			MultipartFile colorFile
			) throws IOException {
		productColorService.insertProductColor(colorName, colorFile);
		StringBuffer sb = new StringBuffer();
		String msg = "색상이 등록 되었습니다.";
		
		sb.append("alert('" + msg + "');");
		sb.append("location.href='/admin/productColorManager'");
		sb.append("</script>");
		sb.insert(0, "<script>");
		
		return sb.toString();
	}
	
	@PostMapping("/productColorDelete")
	@ResponseBody
	public String productColorDelete(
			@RequestParam Long[] text
			) {
		try {
			productColorService.deleteProductColor(text);
			StringBuffer sb = new StringBuffer();
			String msg = "색상이 삭제 되었습니다.";
			
			sb.append("alert('" + msg + "');");
			sb.append("location.href='/admin/productColorManager'");
			sb.append("</script>");
			sb.insert(0, "<script>");
			
			return sb.toString();
		}catch(Exception e) {
			StringBuffer sb = new StringBuffer();
			String msg = "색상이 삭제 되었습니다.";
			
			sb.append("alert('" + msg + "');");
			sb.append("location.href='/admin/productColorManager'");
			sb.append("</script>");
			sb.insert(0, "<script>");
			
			return sb.toString();
		}
	}
	
	@GetMapping("/siteAccessManager")
	public String siteAccessManager() {
		
		return "administration/analytics/accessManager";
	}
	
	@GetMapping("/siteAnalytics")
	public String siteAnalytics() {
		
		return "administration/analytics/siteAnalytics";
	}
	
}
