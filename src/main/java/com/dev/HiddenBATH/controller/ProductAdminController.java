package com.dev.HiddenBATH.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dev.HiddenBATH.dto.ProductDTO;
import com.dev.HiddenBATH.model.product.BigSort;
import com.dev.HiddenBATH.model.product.MiddleSort;
import com.dev.HiddenBATH.model.product.Product;
import com.dev.HiddenBATH.model.product.ProductColor;
import com.dev.HiddenBATH.model.product.ProductOption;
import com.dev.HiddenBATH.model.product.ProductSize;
import com.dev.HiddenBATH.model.product.ProductTag;
import com.dev.HiddenBATH.repository.product.ProductBigSortRepository;
import com.dev.HiddenBATH.repository.product.ProductColorRepository;
import com.dev.HiddenBATH.repository.product.ProductFileRepository;
import com.dev.HiddenBATH.repository.product.ProductImageRepository;
import com.dev.HiddenBATH.repository.product.ProductMiddleSortRepository;
import com.dev.HiddenBATH.repository.product.ProductOptionRepository;
import com.dev.HiddenBATH.repository.product.ProductRepository;
import com.dev.HiddenBATH.repository.product.ProductSizeRepository;
import com.dev.HiddenBATH.repository.product.ProductTagRepository;
import com.dev.HiddenBATH.service.ExcelUploadService;
import com.dev.HiddenBATH.service.RefactoringService;
import com.dev.HiddenBATH.service.ZipService;
import com.dev.HiddenBATH.service.product.ProductBigSortService;
import com.dev.HiddenBATH.service.product.ProductColorService;
import com.dev.HiddenBATH.service.product.ProductFileService;
import com.dev.HiddenBATH.service.product.ProductImageService;
import com.dev.HiddenBATH.service.product.ProductMiddleSortService;
import com.dev.HiddenBATH.service.product.ProductOptionService;
import com.dev.HiddenBATH.service.product.ProductService;
import com.dev.HiddenBATH.service.product.ProductSizeService;
import com.dev.HiddenBATH.service.product.ProductTagService;
import com.dev.HiddenBATH.utils.RotationUtils;

@Controller
@RequestMapping("/admin")
public class ProductAdminController {
	
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
	ProductOptionRepository productOptionRepository;
	
	@Autowired
	ProductOptionService productOptionService;
	
	@Autowired
	ProductTagService productTagService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductImageService productImageService;
	
	@Autowired
	ProductFileService productFileService;
	
	@Autowired
	ProductFileRepository productFileRepository;
	
	@Autowired
	ProductImageRepository productImageRepository;
	
	@Autowired
	ExcelUploadService excelUploadService;
	
	@Autowired
	ZipService zipService;
	
	@Autowired
	RotationUtils rotationUtils;
	
	@Autowired
	RefactoringService refactoringService;
	
//	@PostMapping("/resetZipUpload")
//	@ResponseBody
//	public List<String> resetZipUpload(
//			MultipartFile file,
//			Model model
//			) throws IOException {
//		return zipService.zipProductInsert(file);
//	}
	
	@PostMapping("/resetZipUpload")
	@ResponseBody
	public void resetZipUpload(
			MultipartFile file,
			Model model
			) throws IOException {
		rotationUtils.processUpload(file);
	}
	
	@PostMapping("/refactoringZipUpload")
    public ResponseEntity<Resource> refactoringZipUpload(@RequestParam("file") MultipartFile file) throws IOException {
        // 파일 처리
        String zipFilePath = refactoringService.refactorAndZip(file);

        // 처리 완료 후 파일 다운로드
        File zipFile = new File(zipFilePath);
        Resource resource = new FileSystemResource(zipFile);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + zipFile.getName());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(zipFile.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
	
//	@PostMapping("/refactoringZipUpload")
//    @ResponseBody
//    public ResponseEntity<Resource> refactoringZipUpload(MultipartFile file) {
//        try {
//            zipService.refactorAndPrepareDownload(file);
//
//            // 파일 경로 지정
//            File finalZipFile = new File("D:/Refactoring/PRODUCT.zip");
//
//            // 파일을 Resource로 변환
//            Resource resource = new FileSystemResource(finalZipFile);
//            if (!resource.exists()) {
//                return ResponseEntity.notFound().build();
//            }
//
//            // 파일 다운로드를 위한 헤더 설정
//            HttpHeaders headers = new HttpHeaders();
//            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + finalZipFile.getName());
//            headers.add(HttpHeaders.CONTENT_TYPE, "application/zip");
//
//            // 파일 다운로드
//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .body(resource);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
	
//	@PostMapping("/resetZipUpload")
//	@ResponseBody
//	public void resetZipUpload(
//			MultipartFile file,
//			Model model
//			) throws IOException {
//		zipService.directoryRefactoring(file);
//	}
	
	@PostMapping("/resetExcelUpload")
	@ResponseBody
	public List<String> addExcelUpload(MultipartFile file, Model model) throws IOException {
	    return excelUploadService.uploadExcel(file);
	}
	
//	@PostMapping("/resetExcelUpload")
//    public ResponseEntity<byte[]> addExcelUpload(@RequestParam("file") MultipartFile file) throws IOException {
//        byte[] modifiedFile = productService.processExcelFile(file);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentDispositionFormData("attachment", "modified_excel.xlsx");
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .body(modifiedFile);
//    }
    
	@PostMapping("/updateExcelUpload")
	@ResponseBody
    public void updateExcelUpload(@RequestParam("file") MultipartFile file) throws IOException {
		excelUploadService.updateProductsFromExcel(file);
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
			String msg = "대분류가 등록된 중분류, 제품이 존재하는 경우 대분류를 삭제할 수 없습니다.";
			
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
			String msg = " 중분류가 등록된 제품이 존재하는 경우 중분류를 삭제할 수 없습니다.";
			
			sb.append("alert('" + msg + "');");
			sb.append("location.href='/admin/productCategoryManager'");
			sb.append("</script>");
			sb.insert(0, "<script>");
			
			return sb.toString();
		}
	}
	
	@GetMapping("/productManager")
	public String productManager(
			
			) {
		
		return "administration/product/productManager";
	}
	
	@GetMapping("/productInsertForm")
	public String productInsertForm(
			Model model
			) {
		List<BigSort> b = productBigSortRepository.findAll();
		if(b.size()<1) {
			BigSort bs = new BigSort();
			bs.setName("분류를 등록 해 주세요");
			bs.setId(0L);
			b.add(bs);
		}
		
		List<ProductOption> o = productOptionRepository.findAll();
		if(o.size()<1) {
			ProductOption ot = new ProductOption();
			ot.setProductOptionText("옵션을 등록 해 주세요");
			ot.setId(0L);
			o.add(ot);
		}
		
		List<ProductColor> c = productColorRepository.findAll();
		if(c.size()<1) {
			ProductColor cr = new ProductColor();
			cr.setProductColorSubject("색상을 등록 해 주세요");
			cr.setId(0L);
			c.add(cr);
		}
		
		List<ProductSize> s = productSizeRepository.findAll();
		if(s.size()<1) {
			ProductSize ps = new ProductSize();
			ps.setProductSizeText("사이즈를 입력 해 주세요");
			ps.setId(0L);
			s.add(ps);
		}
		
		List<ProductTag> t = productTagRepository.findAll();
		if(t.size()<1) {
			ProductTag pt = new ProductTag();
			pt.setProductTagText("태그를 등록 해 주세요");
			pt.setId(0L);
			t.add(pt);
		}
		
		model.addAttribute("options", o);
		model.addAttribute("tags", t);
		model.addAttribute("sizes", s);
		model.addAttribute("colors", c);
		model.addAttribute("bigsorts", b);
		return "administration/product/productInsertForm";
	}
	
	@PostMapping("/searchMiddleSort")
	@ResponseBody
	public List<MiddleSort> searchMiddleSort(
			Model model, 
			Long bigId
			) {

		return productMiddleSortRepository.findAllByBigSort(productBigSortRepository.findById(bigId).get());
	}
	
	@PostMapping("/productInsert")
	@ResponseBody
	public String productInsert(
			ProductDTO dto
			) throws IllegalStateException, IOException {
		
		
		Product savedProduct = productService.productInsert(dto);
		
		if(dto.getSlideImages() != null 
				&& !dto.getSlideImages().isEmpty() 
				&& !dto.getSlideImages().get(0).isEmpty()) {
			productImageService.imageUpload(savedProduct, dto.getSlideImages());
		}
		
		if(dto.getFiles() != null
				&& !dto.getFiles().isEmpty()
				&& !dto.getFiles().get(0).isEmpty()) {
			productFileService.fileUpload(savedProduct, dto.getFiles());
		}
		StringBuffer sb = new StringBuffer();
		String msg = "제품이 등록 되었습니다.";
		
		sb.append("alert('" + msg + "');");
		sb.append("location.href='/admin/productManager'");
		sb.append("</script>");
		sb.insert(0, "<script>");
		
		return sb.toString();
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
			String msg = "에러가 발생하였습니다. 다시 시도해주세요.";
			
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
			String msg = "에러가 발생하였습니다. 다시 시도해주세요.";
			
			sb.append("alert('" + msg + "');");
			sb.append("location.href='/admin/productSizeManager'");
			sb.append("</script>");
			sb.insert(0, "<script>");
			
			return sb.toString();
		}
	}
	
	@GetMapping("/productOptionManager")
	public String productOptionManager(
			Model model
			) {
		
		model.addAttribute("options", productOptionRepository.findAll());
		return "administration/product/productOptionManager";
	}
	
	@PostMapping("/productOptionInsert")
	@ResponseBody
	public String productOptionInsert(
			String productOptions
			) {
		String[] arr = productOptions.split(",");
		
		for(String tag : arr) {
			if(tag.contains("&amp;")) {
				tag = tag.replace("&amp;", "&");
			}
			ProductOption o = new ProductOption();
			o.setProductOptionText(tag);
			productOptionRepository.save(o);
		}
		StringBuffer sb = new StringBuffer();
		String msg = "제품 옵션이 등록 되었습니다.";
		
		sb.append("alert('" + msg + "');");
		sb.append("location.href='/admin/productOptionManager'");
		sb.append("</script>");
		sb.insert(0, "<script>");
		
		return sb.toString();
	}
	
	@PostMapping("/productOptionDelete")
	@ResponseBody
	public String productOptionDelete(
			@RequestParam Long[] optionDeleteSelect
			) {
		try {
			productOptionService.deleteProductOption(optionDeleteSelect);
			StringBuffer sb = new StringBuffer();
			String msg = "옵션이 삭제 되었습니다.";
			
			sb.append("alert('" + msg + "');");
			sb.append("location.href='/admin/productOptionManager'");
			sb.append("</script>");
			sb.insert(0, "<script>");
			
			return sb.toString();
		}catch(Exception e) {
			StringBuffer sb = new StringBuffer();
			String msg = "에러가 발생하였습니다. 다시 시도해주세요.";
			
			sb.append("alert('" + msg + "');");
			sb.append("location.href='/admin/productOptionManager'");
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
}
