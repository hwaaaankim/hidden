package com.dev.HiddenBATH.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.HiddenBATH.dto.MenuDTO;
import com.dev.HiddenBATH.model.product.MiddleSort;
import com.dev.HiddenBATH.model.product.Product;
import com.dev.HiddenBATH.repository.product.ProductBigSortRepository;
import com.dev.HiddenBATH.repository.product.ProductColorRepository;
import com.dev.HiddenBATH.repository.product.ProductMiddleSortRepository;
import com.dev.HiddenBATH.repository.product.ProductRepository;
import com.dev.HiddenBATH.repository.product.ProductTagRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductBigSortRepository productBigSortRepository;
	
	@Autowired
	ProductMiddleSortRepository productMiddleSortRepository;
	
	@Autowired
	ProductColorRepository productColorRepository;
	
	@Autowired
	ProductTagRepository productTagRepository;
	
	@ModelAttribute("menuList")
	public MenuDTO menuList(MenuDTO menuDto) {
		
		menuDto.setBigSortList(productBigSortRepository.findAllByOrderByBigSortIndexAsc());

		return menuDto;
	}
	
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
	
	@GetMapping("/imageGallery")
	public String imageGallery() {
		
		return "front/imageGallery";
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
	
	@GetMapping("/2023New")
	public String catalog2023New() {
		
		return "front/catalog/2023New";
	}
	
	@GetMapping("/2024")
	public String catalog2024() {
		
		return "front/catalog/2024";
	}
	
	@GetMapping("/noticeDetail")
	public String noticeDetail() {
		
		return "front/noticeDetail";
	}
	
	@GetMapping("/contact")
	public String contact() {
		
		return "front/contact";
	}
	
	@PostMapping("/searchMiddleSort")
	@ResponseBody
	public List<MiddleSort> searchMiddleSort(
			Model model, 
			Long bigId
			) {

		return productMiddleSortRepository.findAllByBigSort(productBigSortRepository.findById(bigId).get());
	}
	
	@RequestMapping(value = "/productList/{id}",
			method = {RequestMethod.GET, RequestMethod.POST})
	public String productList(
			Model model,
			@PathVariable Long id,
			@RequestParam(required = false, defaultValue = "0") Long middleId,
			@RequestParam(required = false, defaultValue = "0") Long tagId,
			@RequestParam(required = false, defaultValue = "0") Long colorId,
			@PageableDefault(size=10) Pageable pageable
			) {
		
		Page<Product> products = null;
		if(id == 7l) {
			products = productRepository.findAllByBigSortOrderByProductIndexAsc(pageable, productBigSortRepository.findById(id).get());
			
		}else {
			if(middleId != 0l) {
				if(tagId != 0l) {
					if(colorId != 0l) {
						products = productRepository.findByTagColorAndSorts(tagId, colorId, middleId, id, pageable);
					}else {
						products = productRepository.findByTagAndSorts(tagId, middleId, id, pageable);
					}
				}else {
					if(colorId != 0l) {
						products = productRepository.findByColorAndSorts(colorId, middleId, id, pageable);
					}else {
						products = productRepository.findBySorts(middleId, id, pageable);
					}
				}
				model.addAttribute("middleSortName", productMiddleSortRepository.findById(middleId).get().getName());
			}else {
				if(tagId != 0l) {
					if(colorId != 0l) {
						products = productRepository.findByTagColorAndBig(tagId, colorId, id, pageable);
					}else {
						products = productRepository.findByTagAndBig(tagId, id, pageable);
					}
				}else {
					if(colorId != 0l) {
						products = productRepository.findByColorAndBig(colorId, id, pageable);
					}else {
						products = productRepository.findAllByBigSortOrderByProductIndexAsc(pageable, productBigSortRepository.findById(id).get());
					}
				}
			}
		}
		model.addAttribute("bigSortName", productBigSortRepository.findById(id).get().getName());
		model.addAttribute("middleSorts", productMiddleSortRepository.findAllByBigSort(productBigSortRepository.findById(id).get()));
		model.addAttribute("products", products);
		model.addAttribute("tagId", tagId);
		model.addAttribute("colorId", colorId);
		model.addAttribute("middleSortId", middleId);
		model.addAttribute("bigSortId", id);
		model.addAttribute("bigSorts", productBigSortRepository.findAll());
		model.addAttribute("tags", productTagRepository.findAll());
		model.addAttribute("colors", productColorRepository.findAll());
		
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
	
//	@GetMapping("/productDetailAdvanced01")
//	public String productDetailAdvanced01() {
//		
//		return "front/productDetailAdvanced01";
//	}
	
	@GetMapping("/search")
	public String search() {
		
		return "front/search";
	}
	
	@GetMapping("/address")
	public String address() {
		
		return "front/address";
	}
}
