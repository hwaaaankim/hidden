package com.dev.HiddenBATH.service.product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.dev.HiddenBATH.dto.ProductDTO;
import com.dev.HiddenBATH.model.product.Product;
import com.dev.HiddenBATH.model.product.ProductColor;
import com.dev.HiddenBATH.model.product.ProductOption;
import com.dev.HiddenBATH.model.product.ProductSize;
import com.dev.HiddenBATH.model.product.ProductTag;
import com.dev.HiddenBATH.repository.product.ProductBigSortRepository;
import com.dev.HiddenBATH.repository.product.ProductColorRepository;
import com.dev.HiddenBATH.repository.product.ProductMiddleSortRepository;
import com.dev.HiddenBATH.repository.product.ProductOptionRepository;
import com.dev.HiddenBATH.repository.product.ProductRepository;
import com.dev.HiddenBATH.repository.product.ProductSizeRepository;
import com.dev.HiddenBATH.repository.product.ProductTagRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductMiddleSortRepository productMiddleSortRepository;
	
	@Autowired
	ProductBigSortRepository productBigSortRepository;
	
	@Autowired
	ProductTagRepository productTagRepository;
	
	@Autowired
	ProductOptionRepository productOptionRepository;
	
	@Autowired
	ProductOptionService productOptionService;
	
	@Autowired
	ProductTagService productTagService;
	
	@Autowired
	ProductSizeRepository productSizeRepository;
	
	@Autowired
	ProductSizeService productSizeService;
	
	@Autowired
	ProductColorRepository productColorRepository;
	
	@Autowired
	ProductColorService productColorService;
	
	@Value("${spring.upload.env}")
	private String env;

	@Value("${spring.upload.path}")
	private String commonPath;
	
	public Product productInsert(ProductDTO dto) throws IllegalStateException, IOException {

	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	        .limit(targetStringLength)
	        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	        .toString();

	    // 실제 파일 저장 위치 (절대 경로)
	    String path = commonPath 
	        + "/product/"
	        + productBigSortRepository.findById(dto.getBigSort()).get().getId()
	        + "/"
	        + dto.getProductCode()
	        + "/rep";

	    // 파일 resource 로드 url
	    String road = "/upload/product/"
    		+ productBigSortRepository.findById(dto.getBigSort()).get().getId()
	        + "/"	
	        + dto.getProductCode()
	        + "/rep";
	    
	    int index = 1;
	    if(productRepository.findFirstIndex().isPresent()) {
			index = productRepository.findFirstIndex().get() + 1;
		}
	    Product product = new Product();
	    product.setName(dto.getProductName());
	    product.setProductCode(dto.getProductCode());
	    product.setTitle(dto.getProductTitle());
	    product.setSubject(dto.getSubject());
	    product.setHandle(dto.getHandle());
	    product.setOrder(dto.getOrder());
	    product.setProductSign(true);
	    product.setUnit("EA");
	    product.setBigSort(productBigSortRepository.findById(dto.getBigSort()).get());
	    product.setMiddleSort(productMiddleSortRepository.findById(dto.getMiddleSort()).get());
	    product.setProductIndex(index);
	    
	    List<ProductColor> colors = new ArrayList<>();
	    if(dto.getColors() != null) {
	        for(Long id : dto.getColors()) {
	            colors.add(productColorRepository.findById(id).get());
	        }
	        product.setProductColors(colors);
	    }

	    List<ProductOption> options = new ArrayList<>();
	    if(dto.getOptions() != null) {
	        for(Long id : dto.getOptions()) {
	            options.add(productOptionRepository.findById(id).get());
	        }
	        product.setProductOptions(options);
	    }

	    List<ProductTag> tags = new ArrayList<>();
	    if(dto.getTags() != null) {
	        for(Long id : dto.getTags()) {
	            tags.add(productTagRepository.findById(id).get());
	        }
	        product.setProductTags(tags);
	    }

	    List<ProductSize> sizes = new ArrayList<>();
	    if(dto.getSizes() != null) {
	        for(Long id : dto.getSizes()) {
	            sizes.add(productSizeRepository.findById(id).get());
	        }
	        product.setProductSizes(sizes);
	    }

	    String contentType = dto.getProductImage().getContentType();
	    String originalFileExtension = "";

	    if (ObjectUtils.isEmpty(contentType)){
	        return null;
	    } else {
	        if (contentType.contains("image/jpeg")) {
	            originalFileExtension = ".jpg";
	        } else if (contentType.contains("image/png")) {
	            originalFileExtension = ".png";
	        }
	    }
	    String productImageName = generatedString + originalFileExtension;
	    String productImagePath = path + "representative/" + productImageName;
	    String productImageRoad = road + "representative/" + productImageName;

	    // Create directory if it does not exist
	    File directory = new File(path + "representative/");
	    if (!directory.exists()) {
	        boolean dirsCreated = directory.mkdirs();
	        System.out.println("Directories created: " + dirsCreated);
	    }

	    // Check if directory exists and is writable
	    if (directory.exists() && directory.isDirectory() && directory.canWrite()) {
	        File productImageSaveFile = new File(productImagePath);
	        dto.getProductImage().transferTo(productImageSaveFile);
	        
	        product.setProductRepImageOriginalName(dto.getProductImage().getOriginalFilename());
	        product.setProductRepImageName(productImageName);
	        product.setProductRepImageExtension(originalFileExtension);
	        product.setProductRepImageRoad(productImageRoad);
	        product.setProductRepImagePath(productImagePath);

	        Product savedProduct = productRepository.save(product);

	        return savedProduct;
	    } else {
	        throw new IOException("Cannot create directories or no write permission for the path: " + directory.getAbsolutePath());
	    }
	}
	
	
	public Page<Product> findAllByBigMiddleTagColor(
			Pageable pageable, Long id
			){
		Page<Product> products = productRepository.findAllByBigSortOrderByProductIndexAsc(pageable, productBigSortRepository.findById(id).get());
		
		return products;
	}
	
}












