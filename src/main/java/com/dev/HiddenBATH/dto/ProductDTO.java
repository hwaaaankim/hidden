package com.dev.HiddenBATH.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductDTO {

	private String productName;
	private String productCode;
	private String productTitle;
	private MultipartFile productImage;
	private List<MultipartFile> slideImages;
	private List<MultipartFile> files;
	private List<String> sizes;
	private List<String> colors;
	private Long bigSort;
	private Long middleSort;
	private List<String> tags;
	private String subject;
}
